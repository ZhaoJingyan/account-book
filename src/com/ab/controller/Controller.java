package com.ab.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.xml.sax.SAXException;

import com.ab.resources.AccountBookConfiguration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet控制器，接收来自前端的命令请求，并定位Model。
 * 
 * @author Zhao Jinyan
 *
 */
@SuppressWarnings("serial")
public class Controller extends HttpServlet {

    // ---------------------------------------------- Static Properties

    public static Log log = LogFactory.getLog(Controller.class);
    public static final short GET = 0;
    public static final short POST = 1;
    public static final String MODELS = "_MODELS_";

    // ---------------------------------------------- Private Properties

    private ControllerConfiguration configuration;
    private Gson gson;

    // ---------------------------------------------- Servlet Method

    /**
     * 初始化Servlet。
     */
    @Override
    public void init() throws ServletException {

	gson = new GsonBuilder().setDateFormat(
		AccountBookConfiguration.getDateFormate()).create();

	try {

	    /* 获取配置信息 */
	    configuration = XmlConfigBuilder.getXmlConfigBuilder().parse();

	} catch (ParserConfigurationException | SAXException | IOException e) {
	    log.warn("初始化Configuration失败", e);
	}

    }

    /**
     * GET方法，处理前端GET请求。
     */
    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	run(request, response, GET);
    }

    /**
     * POST方法，处理前端POST请求。
     */
    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	run(request, response, POST);
    }

    // ---------------------------------------------- Control Method

    /**
     * 执行客户端请求。
     * 
     * @param request 客户对请求数据
     * @param response 服务器返回数据
     * @param action 请求类型
     * @throws IOException IO错误
     * @throws ServletException Servlet错误
     */
    private final void run(HttpServletRequest request,
	    HttpServletResponse response, short action)
	    throws ServletException, IOException {

	Model model;

	try {

	    // 获取model，并进行相应的处理
	    model = getModel(request, response);
	    if (model == null) {
		/* 404 处理 */
		request.getRequestDispatcher("/errer.jsp").forward(request,
			response);
	    } else if (action == GET) {
		model.get();
	    } else if (action == POST) {
		model.post();
	    }

	} catch (IllegalAccessException | InvocationTargetException e) {
	    log.error("", e);
	}

    }

    /**
     * 获取一个Model，如果找不到，返回null。
     * 
     * @param request 客户对请求数据
     * @param response 服务器返回数据
     * @return Model Model
     * @throws IOException IO错误
     * @throws InvocationTargetException 反射错误
     * @throws IllegalAccessException 反射错误
     */
    private final Model getModel(HttpServletRequest request,
	    HttpServletResponse response) throws IOException,
	    IllegalAccessException, InvocationTargetException {

	HttpSession session = request.getSession(); // 获取session
	String uri = request.getRequestURI(); // 获取uri
	String url = uri.substring(0, uri.lastIndexOf(".")); // 去后缀，的到url
	Map<String, Model> models = getModels(session); // 从session中获取说有的model
	Model model = models.get(url); // 更具对应的url，得到model

	// 如果没有对应的model，尝试创建model
	if (model == null)
	    model = createModel(url);

	// 重新配置model
	if (model != null) {
	    setModelProperties(model, request, response);
	    model._setGson(this.gson);
	    model._setRequest(request);
	    model._setResponse(response);
	    model.init();
	}

	return model;

    }

    /**
     * 从session中获取model映射关系表。
     * 
     * @param session session
     * @return model映射关系表
     */
    private final Map<String, Model> getModels(HttpSession session) {

	// 获取model映射关系表，如果没有，则创建新的
	ModelsTable modelsTable = (ModelsTable) session.getAttribute(MODELS);
	if (modelsTable == null) {
	    modelsTable = new ModelsTable();
	    modelsTable.setModels(new HashMap<String, Model>());
	}

	return modelsTable.getModels();

    }

    /**
     * 创建一个model，若果无法创建，返回null。
     * 
     * @param url Model对应的URL
     * @return
     */
    private final Model createModel(String url) {

	// 获取model的class name
	String className = configuration.getModelClassName(url);
	Model model = null;

	try {

	    // 通过反射得到Model的实现
	    if (className != null) {
		Class<?> clazz = Class.forName(className);
		Object object = clazz.newInstance();
		if (object instanceof Model) {
		    model = (Model) object;
		} else {
		    model = null;
		}
	    }
	} catch (ClassNotFoundException | IllegalAccessException
		| InstantiationException e) {
	    e.printStackTrace();
	    model = null;
	}

	return model;

    }

    /**
     * 为Model装填bean属性。
     * 
     * @param object Model
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IllegalAccessException 反射异常
     * @throws InvocationTargetException 反射异常
     */
    private final void setModelProperties(Object object,
	    HttpServletRequest request, HttpServletResponse response)
	    throws IllegalAccessException, InvocationTargetException {

	Enumeration<String> names = request.getParameterNames();
	Map<String, Object> values = new HashMap<String, Object>();
	
	while (names.hasMoreElements()) {
	    String name = names.nextElement();
	    values.put(name, request.getParameterValues(name));
	}

	BeanUtils.populate(object, values);
    }

    // ---------------------------------------------- Inner Classes

    private class ModelsTable {

	private Map<String, Model> models;

	public Map<String, Model> getModels() {
	    return this.models;
	}

	public void setModels(Map<String, Model> models) {
	    this.models = models;
	}

    }
}

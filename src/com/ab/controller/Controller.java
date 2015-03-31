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
 * Servlet����������������ǰ�˵��������󣬲���λModel��
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
     * ��ʼ��Servlet��
     */
    @Override
    public void init() throws ServletException {

	gson = new GsonBuilder().setDateFormat(
		AccountBookConfiguration.getDateFormate()).create();

	try {

	    /* ��ȡ������Ϣ */
	    configuration = XmlConfigBuilder.getXmlConfigBuilder().parse();

	} catch (ParserConfigurationException | SAXException | IOException e) {
	    log.warn("��ʼ��Configurationʧ��", e);
	}

    }

    /**
     * GET����������ǰ��GET����
     */
    @Override
    protected void doGet(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	run(request, response, GET);
    }

    /**
     * POST����������ǰ��POST����
     */
    @Override
    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	run(request, response, POST);
    }

    // ---------------------------------------------- Control Method

    /**
     * ִ�пͻ�������
     * 
     * @param request �ͻ�����������
     * @param response ��������������
     * @param action ��������
     * @throws IOException IO����
     * @throws ServletException Servlet����
     */
    private final void run(HttpServletRequest request,
	    HttpServletResponse response, short action)
	    throws ServletException, IOException {

	Model model;

	try {

	    // ��ȡmodel����������Ӧ�Ĵ���
	    model = getModel(request, response);
	    if (model == null) {
		/* 404 ���� */
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
     * ��ȡһ��Model������Ҳ���������null��
     * 
     * @param request �ͻ�����������
     * @param response ��������������
     * @return Model Model
     * @throws IOException IO����
     * @throws InvocationTargetException �������
     * @throws IllegalAccessException �������
     */
    private final Model getModel(HttpServletRequest request,
	    HttpServletResponse response) throws IOException,
	    IllegalAccessException, InvocationTargetException {

	HttpSession session = request.getSession(); // ��ȡsession
	String uri = request.getRequestURI(); // ��ȡuri
	String url = uri.substring(0, uri.lastIndexOf(".")); // ȥ��׺���ĵ�url
	Map<String, Model> models = getModels(session); // ��session�л�ȡ˵�е�model
	Model model = models.get(url); // ���߶�Ӧ��url���õ�model

	// ���û�ж�Ӧ��model�����Դ���model
	if (model == null)
	    model = createModel(url);

	// ��������model
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
     * ��session�л�ȡmodelӳ���ϵ��
     * 
     * @param session session
     * @return modelӳ���ϵ��
     */
    private final Map<String, Model> getModels(HttpSession session) {

	// ��ȡmodelӳ���ϵ�����û�У��򴴽��µ�
	ModelsTable modelsTable = (ModelsTable) session.getAttribute(MODELS);
	if (modelsTable == null) {
	    modelsTable = new ModelsTable();
	    modelsTable.setModels(new HashMap<String, Model>());
	}

	return modelsTable.getModels();

    }

    /**
     * ����һ��model�������޷�����������null��
     * 
     * @param url Model��Ӧ��URL
     * @return
     */
    private final Model createModel(String url) {

	// ��ȡmodel��class name
	String className = configuration.getModelClassName(url);
	Model model = null;

	try {

	    // ͨ������õ�Model��ʵ��
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
     * ΪModelװ��bean���ԡ�
     * 
     * @param object Model
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IllegalAccessException �����쳣
     * @throws InvocationTargetException �����쳣
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

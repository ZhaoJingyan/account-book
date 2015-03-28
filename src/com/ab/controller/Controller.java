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

/**
 * Servlet����������������ǰ�˵��������󣬲���λModel��
 * 
 * @author Zhao Jinyan
 *
 */
@SuppressWarnings("serial")
public class Controller extends HttpServlet {

    public static Log log = LogFactory.getLog(Controller.class);
    public static final short GET = 0;
    public static final short POST = 1;
    public static final String MODELS = "_MODELS_";

    private ControllerConfiguration configuration;

    /**
     * ��ʼ��Servlet��
     */
    @Override
    public void init() throws ServletException {
	log.debug("DEBUG - ��ʼ��Controller");
	log.info("INFO - ��ʼ��Controller");
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
	/* ��ȡ��Ӧmodel */
	Model model;
	try {
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
	    throw new ServletException(e);
	}
    }

    /**
     * ��ȡһ��Model������Ҳ���������null��
     * 
     * @param request �ͻ�����������
     * @param response ��������������
     * @return Model
     * @throws IOException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private final Model getModel(HttpServletRequest request,
	    HttpServletResponse response) throws IOException,
	    IllegalAccessException, InvocationTargetException {
	HttpSession session = request.getSession(); // ��ȡsession
	String uri = request.getRequestURI(); // ��ȡuri
	String url = uri.substring(0, uri.lastIndexOf(".")); // ȥ��׺
	Map<String, Model> models = getModels(session); // ��session�л�ȡmodels
	Model model = models.get(url);

	if (model == null)
	    model = createModel(url);

	if (model != null) {
	    setModelProperties(model, request, response);
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
     * @param url
     * @return
     */
    private final Model createModel(String url) {
	String className = configuration.getModelClassName(url);
	Model model = null;
	try {
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
     * @param object
     * @param request
     * @param response
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private final void setModelProperties(Object object,
	    HttpServletRequest request, HttpServletResponse response)
	    throws IllegalAccessException, InvocationTargetException {

	Enumeration<String> names = request.getParameterNames();
	Map<String, Object> values = new HashMap<String, Object>();
	values.put("request", request);
	values.put("response", response);

	while (names.hasMoreElements()) {
	    String name = names.nextElement();
	    values.put(name, request.getParameterValues(name));
	}

	BeanUtils.populate(object, values);
    }

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

package com.ab.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

/**
 * Servlet控制器，接收来自前端的命令请求，并定位Model。
 * 
 * @author Zhao Jinyan
 *
 */
@SuppressWarnings("serial")
public class Controller extends HttpServlet {

	public static final short GET = 0;
	public static final short POST = 1;
	public static final String MODELS = "_MODELS_";

	private ControllerConfiguration configuration;

	/**
	 * 初始化Servlet。
	 */
	@Override
	public void init() throws ServletException {

		try {

			/* 获取配置信息 */
			configuration = XmlConfigBuilder.getXmlConfigBuilder().parse();

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		run(request, response, GET);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		run(request, response, POST);
	}

	/**
	 * 执行客户端请求。
	 * 
	 * @param request
	 *            客户对请求数据
	 * @param response
	 *            服务器返回数据
	 * @param action
	 *            请求类型
	 * @throws IOException
	 * @throws ServletException
	 */
	private final void run(HttpServletRequest request,
			HttpServletResponse response, short action)
			throws ServletException, IOException {
		/* 获取对应model */
		Model model = getModel(request, response);
		if (model == null) {
			/* 404 处理 */
			request.getRequestDispatcher("/errer.jsp").forward(request,
					response);
		} else if (action == GET) {
			model.get();
		} else if (action == POST) {
			model.post();
		}
	}

	/**
	 * 获取一个Model，如果找不到，返回null。
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	private final Model getModel(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(); // 获取session
		String uri = request.getRequestURI(); // 获取uri
		String url = uri.substring(0, uri.lastIndexOf(".")); // 去后缀
		Map<String, Model> models = getModels(session); // 从session中获取models
		Model model = models.get(url);

		if (model == null)
			model = createModel(url);

		if(model != null){
		model.setRequest(request);
		model.setResponse(response);
		model.init();
		}

		return model;
	}

	private final Map<String, Model> getModels(HttpSession session) {
		ModelsTable modelsTable = (ModelsTable) session.getAttribute(MODELS);

		if (modelsTable == null) {
			modelsTable = new ModelsTable();
			modelsTable.setModels(new HashMap<String, Model>());
		}

		return modelsTable.getModels();
	}

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

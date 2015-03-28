package com.ab.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ab.db.DBAccess;
import com.ab.resources.AccountBookConfiguration;

/**
 * Dao层管理者。
 * 
 * @author Zhao
 *
 */
public class DaoManager {

	/**
	 * 构造函数。
	 * 
	 * @throws IOException
	 *             配置文件载入异常
	 */
	public DaoManager() throws IOException {
		this.access = new DBAccess(
				AccountBookConfiguration.getDBConfiguration());
		this.session = access.getSqlSession();
		this.daos = new HashMap<String, Dao>();
	}

	/**
	 * 获取一个Dao
	 * 
	 * @param clazz
	 *            一个Dao的Class类
	 * @return clazz表示的Dao的实现
	 */
	public Dao getDao(Class<? extends Dao> clazz) {
		Dao dao = null;
		dao = daos.get(clazz.getName());
		if (dao == null) {
			try {
				dao = (Dao) clazz.newInstance();
				dao.setSession(session);
				daos.put(clazz.getName(), dao);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return dao;
	}

	private Map<String, Dao> daos;
	private SqlSession session;
	private DBAccess access;

}

package com.ab.dao;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.ab.db.DBAccess;
import com.ab.resources.AccountBookConfiguration;

/**
 * Dao������ߡ�
 * 
 * @author Zhao
 *
 */
public class DaoManager {

	/**
	 * ���캯����
	 * 
	 * @throws IOException
	 *             �����ļ������쳣
	 */
	public DaoManager() throws IOException {
		this.access = new DBAccess(
				AccountBookConfiguration.getDBConfiguration());
		this.session = access.getSqlSession();
		this.daos = new HashMap<String, Dao>();
	}

	/**
	 * ��ȡһ��Dao
	 * 
	 * @param clazz
	 *            һ��Dao��Class��
	 * @return clazz��ʾ��Dao��ʵ��
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

package com.ab.dao;

import org.apache.ibatis.session.SqlSession;

/**
 * ����Daoģ�鶼����ʵ�ֵĽӿڡ�
 * 
 * @author Zhao Jingyan
 *
 */
public abstract class Dao {

    protected SqlSession session;

    /**
     * �������ݿ����Ӷ���
     * 
     * @param sqlSession ���ݿ����Ӷ���
     */
    public void setSession(SqlSession session) {
	this.session = session;
    }

    /**
     * �ر����ݿ����Ӷ���
     */
    public void closeSession() {
	this.session.close();
    }
}

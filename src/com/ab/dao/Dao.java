package com.ab.dao;

import org.apache.ibatis.session.SqlSession;

/**
 * 所有Dao模块都必须实现的接口。
 * 
 * @author Zhao Jingyan
 *
 */
public abstract class Dao {

    protected SqlSession session;

    /**
     * 设置数据库连接对象。
     * 
     * @param sqlSession 数据库连接对象
     */
    public void setSession(SqlSession session) {
	this.session = session;
    }

    /**
     * 关闭数据库连接对象。
     */
    public void closeSession() {
	this.session.close();
    }
}

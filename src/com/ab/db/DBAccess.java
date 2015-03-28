package com.ab.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 管理数据库连接。
 * 
 * @author Zhao Jinyan
 *
 */
public class DBAccess {

    /**
     * 构造方法。
     * 
     * @param xmlPath mybatis配置文件
     * @throws IOException 配置文件载入异常
     */
    public DBAccess(String xmlPath) throws IOException {
	Reader reader = Resources.getResourceAsReader(xmlPath);
	sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     * 数据库连接工厂
     */
    private SqlSessionFactory sqlSessionFactory = null;

    /**
     * 获取一个打开的数据库连接。
     * 
     * @return 数据库连接
     */
    public SqlSession getSqlSession() {
	return sqlSessionFactory.openSession();
    }

}

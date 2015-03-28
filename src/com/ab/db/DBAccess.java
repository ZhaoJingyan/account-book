package com.ab.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * �������ݿ����ӡ�
 * 
 * @author Zhao Jinyan
 *
 */
public class DBAccess {

    /**
     * ���췽����
     * 
     * @param xmlPath mybatis�����ļ�
     * @throws IOException �����ļ������쳣
     */
    public DBAccess(String xmlPath) throws IOException {
	Reader reader = Resources.getResourceAsReader(xmlPath);
	sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     * ���ݿ����ӹ���
     */
    private SqlSessionFactory sqlSessionFactory = null;

    /**
     * ��ȡһ���򿪵����ݿ����ӡ�
     * 
     * @return ���ݿ�����
     */
    public SqlSession getSqlSession() {
	return sqlSessionFactory.openSession();
    }

}

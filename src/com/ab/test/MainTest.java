package com.ab.test;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import com.ab.db.DBAccess;
import com.ab.resources.AccountBookConfiguration;
import com.ab.resources.AccountBookConfigurationKey;

/**
 * ������Է�����
 * 
 * @author Zhao Jinyan
 *
 */
public class MainTest {
	
	@Ignore("����ͨ��")
	@Test
	public void testAccountBookConfiguration(){
		System.out.println("## λ���ļ���д����");
		System.out.printf("%s = %s\n", AccountBookConfigurationKey.DBConfiguration, AccountBookConfiguration.getDBConfiguration());
	}
	
	@Ignore("����ͨ��")
	@Test
	public void testDBAccess() throws IOException{
		System.out.println("## �������Ӳ���");
		DBAccess access = new DBAccess(AccountBookConfiguration.getDBConfiguration());
		SqlSession session = access.getSqlSession();
		assertNotNull(session);
	}
}

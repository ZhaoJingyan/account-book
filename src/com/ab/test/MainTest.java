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
 * 总体测试方法。
 * 
 * @author Zhao Jinyan
 *
 */
public class MainTest {
	
	@Ignore("测试通过")
	@Test
	public void testAccountBookConfiguration(){
		System.out.println("## 位置文件读写测试");
		System.out.printf("%s = %s\n", AccountBookConfigurationKey.DBConfiguration, AccountBookConfiguration.getDBConfiguration());
	}
	
	@Ignore("测试通过")
	@Test
	public void testDBAccess() throws IOException{
		System.out.println("## 数据连接测试");
		DBAccess access = new DBAccess(AccountBookConfiguration.getDBConfiguration());
		SqlSession session = access.getSqlSession();
		assertNotNull(session);
	}
}

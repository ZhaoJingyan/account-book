package com.ab.test;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.ab.bean.Account;
import com.ab.dao.AccountDao;
import com.ab.dao.DaoManager;

public class AccountDaoTest {

	private AccountDao dao;
	private DaoManager manager;

	@Before
	public void init() throws IOException {
		manager = new DaoManager();
		dao = (AccountDao) manager.getDao(AccountDao.class);
	}

	@Test
	public void testGetAllAccounts() {
		List<Account> list = dao.getAllAccounts();
		assertNotNull(list);
		assertNotEquals(0, list.size());
	}
}

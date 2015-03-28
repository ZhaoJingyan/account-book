package com.ab.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.ab.bean.Account;

public class AccountDao extends Dao implements AccountMessage {

	public AccountDao() {
	}

	public void setSession(SqlSession session) {
		super.setSession(session);
		this.accountMessage = this.session.getMapper(AccountMessage.class);
	};

	private AccountMessage accountMessage;

	@Override
	public List<Account> getAllAccounts() {
		return this.accountMessage.getAllAccounts();
	}

}

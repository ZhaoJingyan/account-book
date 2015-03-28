package com.ab.dao;

import java.util.List;

import com.ab.bean.Account;

/**
 * Account动态代理接口。
 * 
 * @author Zhao
 *
 */
public interface AccountMessage {
    public List<Account> getAllAccounts();
}

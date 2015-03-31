package com.ab.model;

import java.io.IOException;

import javax.servlet.ServletException;

import com.ab.bean.Account;
import com.ab.controller.Model;

/**
 * AccountÄ£ÐÍ¡£
 * 
 * @author Zhao Jinyan
 *
 */
public class AccountModel extends Model {
    
    public AccountModel() {
	account = new Account();
    }

    @Override
    public void post() throws ServletException, IOException {
	
	String result = gson.toJson(account);
	
	System.out.println(result);
    }

    @Override
    public void get() throws ServletException, IOException {
	response.setContentType("text/html");
	response.setCharacterEncoding("utf-8");
	writer.println("<h1>Test AccountModel<GET> !!! </h1>");
    }

    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
}

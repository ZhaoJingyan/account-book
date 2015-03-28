package com.ab.bean;

import java.util.Date;

/**
 * Bean�Ĺ������ԡ�
 * 
 * @author Zhao Jinyan
 *
 */
public abstract class Bean {
	protected String text;
	protected Date createTime;

	/**
	 * ��ȡ���ı���
	 * 
	 * @return ���ı�
	 */
	public String getText() {
		return text;
	}

	/**
	 * ���ó��ı���
	 * 
	 * @param text
	 *            ���ı�
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * ��ȡ����ʱ��
	 * 
	 * @return ����ʱ��
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * ���ô���ʱ�䡣
	 * 
	 * @param createTime
	 *            ����ʱ��
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

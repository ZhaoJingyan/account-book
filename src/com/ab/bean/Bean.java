package com.ab.bean;

import java.util.Date;

/**
 * Bean的公共属性。
 * 
 * @author Zhao Jinyan
 *
 */
public abstract class Bean {
	protected String text;
	protected Date createTime;

	/**
	 * 获取长文本。
	 * 
	 * @return 长文本
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置长文本。
	 * 
	 * @param text
	 *            长文本
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间。
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}

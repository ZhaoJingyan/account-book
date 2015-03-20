package com.ab.bean;

/**
 * 科目Bean。
 * 
 * @author Zhao Jinyan
 *
 */
public class Account extends Bean {

	public Account() {
	}

	private int id;
	private String name;
	private boolean accounting;
	private int father;
	private int depth;

	/**
	 * 获取主键。
	 * 
	 * @return 主键
	 */
	public int getId() {
		return id;
	}

	/**
	 * 设置主键。
	 * 
	 * @param id
	 *            主键
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 获取科目名称。
	 * 
	 * @return 科目名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置科目名称。
	 * 
	 * @param name
	 *            科目名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取可记账标记。
	 * 
	 * @return 可记账标记
	 */
	public boolean isAccounting() {
		return accounting;
	}

	/**
	 * 设置可记账标记。
	 * 
	 * @param accounting 可记账标记
	 */
	public void setAccounting(boolean accounting) {
		this.accounting = accounting;
	}

	/**
	 * 获取父级科目ID。
	 * @return 父级科目ID
	 */
	public int getFather() {
		return father;
	}

	/**
	 * 设置父级科目ID
	 * 
	 * @param father 父级科目ID
	 */
	public void setFather(int father) {
		this.father = father;
	}

	/**
	 * 获取科目深度。
	 * 
	 * @return 深度
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * 设置科目深度
	 * 
	 * @param depth 深度
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
}

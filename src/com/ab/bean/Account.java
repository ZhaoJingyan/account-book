package com.ab.bean;

/**
 * ��ĿBean��
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
	 * ��ȡ������
	 * 
	 * @return ����
	 */
	public int getId() {
		return id;
	}

	/**
	 * ����������
	 * 
	 * @param id
	 *            ����
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * ��ȡ��Ŀ���ơ�
	 * 
	 * @return ��Ŀ����
	 */
	public String getName() {
		return name;
	}

	/**
	 * ���ÿ�Ŀ���ơ�
	 * 
	 * @param name
	 *            ��Ŀ����
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ��ȡ�ɼ��˱�ǡ�
	 * 
	 * @return �ɼ��˱��
	 */
	public boolean isAccounting() {
		return accounting;
	}

	/**
	 * ���ÿɼ��˱�ǡ�
	 * 
	 * @param accounting �ɼ��˱��
	 */
	public void setAccounting(boolean accounting) {
		this.accounting = accounting;
	}

	/**
	 * ��ȡ������ĿID��
	 * @return ������ĿID
	 */
	public int getFather() {
		return father;
	}

	/**
	 * ���ø�����ĿID
	 * 
	 * @param father ������ĿID
	 */
	public void setFather(int father) {
		this.father = father;
	}

	/**
	 * ��ȡ��Ŀ��ȡ�
	 * 
	 * @return ���
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * ���ÿ�Ŀ���
	 * 
	 * @param depth ���
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
}

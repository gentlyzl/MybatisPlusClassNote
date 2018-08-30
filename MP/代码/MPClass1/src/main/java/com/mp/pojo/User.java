package com.mp.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * @author:Li
 * @mender:Li
 * @version:1.0.0
 * @time:2018年7月31日上午11:41:28
 * @docs:UserDo.java
 */

/**
 * 设置表名
 */
/*---------------------------------------------ActiveRecord---------------------------------------------*/
/*ActiveRecord活动记录*/
@TableName("user")
public class User extends Model<User> {

	private static final long serialVersionUID = 1L;

	/**
	 * 设置主键
	 */
	@TableId(type = IdType.AUTO)
	Integer id;

	@TableField(value = "account")
	String account;

	String password;

	Integer salt;
	/**
	 * 进行数据库操作时忽略此字段
	 */
	@TableField(exist = false)
	String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSalt() {
		return salt;
	}

	public void setSalt(int salt) {
		this.salt = salt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", password=" + password + ", salt=" + salt + "]";
	}

	/**
	 * 指定实体类的主键
	 */

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return id;
	}

}

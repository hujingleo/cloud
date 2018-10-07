package com.suyou.eurekaclient.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-28 15:45:56
 */
@Data
@TableName("tb_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private int userId;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String mobile;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 
	 */
	private Date createdDate;
	/**
	 * 
	 */
	private String playerId;
	/**
	 * 
	 */
	private String openId;
	/**
	 * 
	 */
	private String officialAccountsOpenId;
	/**
	 * 
	 */
	private String unionId;
	/**
	 * 
	 */
	private String nickName;
	/**
	 * 
	 */
	private String avatarUrl;
	/**
	 * 
	 */
	private String gender;
	/**
	 * 
	 */
	private String userLevel;
	/**
	 * 
	 */
	private String formId;
	/**
	 * 
	 */
	private String prepayId;
	/**
	 * 
	 */
	private Date formidDate;
	/**
	 * 
	 */
	private Date prepayidDate;


}

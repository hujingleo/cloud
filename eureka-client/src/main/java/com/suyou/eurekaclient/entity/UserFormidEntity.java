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
 * @date 2018-08-31 10:52:33
 */
@Data
@TableName("tb_user_formid")
public class UserFormidEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String openId;
	/**
	 * 
	 */
	private String formId;
	/**
	 * 
	 */
	private Date createdDate;

	private String formidType;
}

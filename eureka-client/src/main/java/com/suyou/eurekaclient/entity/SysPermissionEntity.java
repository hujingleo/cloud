package com.suyou.eurekaclient.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author leo
 * @email hujingleo01@163.com
 * @date 2018-09-28 14:28:32
 */
@Data
@TableName("sys_permission")
public class SysPermissionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long oid;
	/**
	 * 
	 */
	private String permissionName;
	/**
	 * 
	 */
	private String resourceType;
	/**
	 * 
	 */
	private String url;
	/**
	 * 
	 */
	private Date createdDate;
	/**
	 * 
	 */
	private Date updatedDate;

}

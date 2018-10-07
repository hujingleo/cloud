package com.suyou.cloud.entity;

//import com.baomidou.mybatisplus.annotations.TableId;
//import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-09-28 14:28:32
 */
@Data
//@TableName("sys_role")
public class SysRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
//	@TableId
	private int oid;
	/**
	 * 
	 */
	private String rolename;
	/**
	 * 
	 */
	private String description;
	/**
	 * 
	 */
	private Date createdDate;
	/**
	 * 
	 */
	private Date updatedDate;


}

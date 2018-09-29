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
@TableName("tb_page")
public class PageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String style;
	/**
	 * 
	 */
	private String url;
	/**
	 * 
	 */
	private String title;
	private String content;
	/**
	 * 
	 */
	private String state;
	/**
	 * 
	 */
	private Date createdDate;
	/**
	 * 
	 */
	private Date updatedDate;


}

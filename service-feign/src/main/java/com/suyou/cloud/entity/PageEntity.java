package com.suyou.cloud.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
//@TableName("tb_page")
public class PageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
//	@TableId
	private int id;
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
	private String imageUrl;
	/**
	 *
	 */
	private String redirectUrl;
	/**
	 * 
	 */
	private String title;
	private String content;
	/**
	 * 
	 */
	private int state;
	/**
	 *
	 */
	private String createdBy;
	/**
	 * 
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date createdDate;
	/**
	 * 
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date updatedDate;


}

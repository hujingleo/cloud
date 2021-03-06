package com.suyou.cloud.entity;

//import com.baomidou.mybatisplus.annotations.TableId;
//import com.baomidou.mybatisplus.annotations.TableName;
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
//@TableName("tb_poster_style")
public class PosterStyleEntity implements Serializable {
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
	private String content;
	/**
	 * 
	 */
	private String structure;
	/**
	 *
	 */
	private String imageUrl;
	/**
	 *
	 */
	private String type;
	/**
	 *
	 */
	private int state;
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

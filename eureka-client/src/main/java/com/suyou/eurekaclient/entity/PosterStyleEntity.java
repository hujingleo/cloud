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
@TableName("tb_poster_style")
public class PosterStyleEntity implements Serializable {
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
	private String content;
	/**
	 * 
	 */
	private String structure;
	/**
	 * 
	 */
	private Date createdDate;
	/**
	 * 
	 */
	private Date updatedDate;

}

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
@TableName("tb_poster")
public class PosterEntity implements Serializable {
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
	private String type;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Integer style;
	/**
	 * 
	 */
	private String state;
	/**
	 * 
	 */
	private Date startDate;
	/**
	 *
	 */
	private Date endDate;
	/**
	 *
	 */
	private Date createdDate;
	/**
	 * 
	 */
	private Date updatedDate;


}

package com.suyou.eurekaclient.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

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
	private int id;
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
	private String title;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Integer styleId;
	/**
	 * 
	 */
	private String state;
	/**
	 *
	 */
	private String imageUrl;
	/**
	 *
	 */
	private String previewImage;
	/**
	 *
	 */
	private String createdBy;
	/**
	 *
	 */
	private String birthdayPerson;
	/**
	 *
	 */
	private String awardTo;
	/**
	 *
	 */
	@TableField(exist = false)
	private List<String> pictureList;
	/**
	 *
	 */
	private String pictures;
	/**
	 *
	 */
	private int remind;
	/**
	 *
	 */
	private BigInteger remindBefore;
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

	@TableField(exist = false)
	private List<String> readerAvatars;

}

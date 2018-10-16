package com.suyou.cloud.entity;

//import com.baomidou.mybatisplus.annotations.TableId;
//import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
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
//@TableName("tb_poster")
public class PosterEntity implements Serializable {
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
	private int hasRemind;
	/**
	 *
	 */
	private int remindBefore;
	/**
	 * 
	 */
	private String address;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	/**
	 *
	 */
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	/**
	 *
	 */
	private Date createdDate;
	/**
	 * 
	 */
	private Date updatedDate;
	/**
	 *
	 */
	private boolean hasReserved;

}

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
 * @date 2018-09-28 15:58:46
 */
@Data
//@TableName("tb_poster_participant")
public class PosterParticipantEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
//	@TableId
	private int id;
	/**
	 * 
	 */
	private String openId;
	/**
	 * 
	 */
	private Integer posterId;
	/**
	 *
	 */
	private int type;
	/**
	 * 
	 */
	private Date createdDate;
	/**
	 * 
	 */
	private Date updatedDate;


}

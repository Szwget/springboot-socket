/**
 * 
 */
package com.szw.springbootsocket.bean;

import lombok.Data;

import java.io.Serializable;


@Data
public class RoomBean implements Serializable {

	private static final long serialVersionUID = 1L;

	//房间的标识
	private String roomName;
	//用户标识
	private String userId;

	private String userName;
	//动作类型 用户进入/退出房间
	private String option;

}

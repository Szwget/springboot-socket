/**
 * 
 */
package com.szw.springbootsocket.bean;

import lombok.Data;

import java.io.Serializable;


@Data
public class MessageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	//房间的标识
	private String roomName;
	//用户标识
	public String userName;

	public String token;
	//消息内容
	public String message;
	//操作类型
	public String option;

}

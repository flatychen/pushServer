package cn.flaty.pushAdmin.views;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * 基础数据包装器
 * 
 * @author flatychen
 *
 */
public class BaseDataWrapper {
	
	/**
	 * 调用是否成功
	 */
	private boolean success;
	
	/**
	 * 调用返回消息，如为成功可为空
	 */
	private String message;
	

	/**
	 * 调用返回数据
	 */
	private Map<String,Object> data;
	
	public Map<String, Object> getData() {
		return data;
	}


	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	/**
	 * 初始化数据包装器大小
	 * @author flatychen
	 * @date 2014-7-11
	 * @param mapSize
	 * @version 
	 */
	public BaseDataWrapper buildWrapper(int mapSize){
		if(this.data != null){
			throw new IllegalArgumentException("----> map 重复初始化! ");
		}
		this.data = new HashMap<String, Object>(mapSize);
		return this;
	}
	

	/**
	 * 初始化数据包装器
	 * @author flatychen
	 * @date 2014-7-11
	 * @param mapSize
	 * @version 
	 */
	public BaseDataWrapper buildWrapper(){
		return this.buildWrapper(5);
	}

	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		// 默认添加message
		if( StringUtils.isBlank(message)){
			if(this.success){
				this.message = "成功！";
			}else{
				this.message = "失败！";
			}
		}
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	
	
}
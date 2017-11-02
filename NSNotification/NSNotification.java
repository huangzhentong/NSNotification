package com.NSNotification;
import java.util.Map;


public class NSNotification {

	private String name;
	private Map<String,Object> userInfo;

	public NSNotification(String name,Map<String, Object> userInfo)
	{
		this.name = name;
		this.userInfo = userInfo;
	}
	public String name()
	{
		return this.name;
	}
	public Map<String,Object> userInfo()
	{
		return this.userInfo;
	}
}

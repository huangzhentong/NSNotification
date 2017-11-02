package com.NSNotification;
import java.lang.reflect.Method; 
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class NSNotificationCenter {

	
	private static NSNotificationCenter signle = null;
	
	private Map<String,ArrayList<NSNotificationObject>> nameMap;			//��������

	private HashMap<Object,ArrayList<NSNotificationObject>> objectMap;		//��������

	private NSNotificationCenter() {
		this.nameMap = new HashMap<String, ArrayList<NSNotificationObject>>();
		this.objectMap = new HashMap<Object, ArrayList<NSNotificationObject>>();
	}
	public static NSNotificationCenter defaultCenter()
	{
		if (signle == null)
		{
			signle = new NSNotificationCenter();
		}
		return signle;
	}
	public void addObserver(Object object,Method method,String name)
	{
		if(object == null || method == null || name == null)
		{
			System.out.print("�����ֵ����Ϊ��");
			return;
		}
		ArrayList<NSNotificationObject> nameValue = nameMap.get(name);
		
		if(nameValue == null)
		{
			nameValue = new ArrayList<NSNotificationObject>();
		}
		//�洢���鵽�ֵ���
		NSNotificationObject notificationObject = new NSNotificationObject(object, method, name);
		nameValue.add(notificationObject);
		nameMap.put(name, nameValue);
		//�洢object ����
		ArrayList<NSNotificationObject> objectList = objectMap.get(object);
		if (objectList == null) {
			objectList = new ArrayList<NSNotificationObject>();
		}
		objectList.add(notificationObject);
		objectMap.put(object, objectList);
		
	}
	//
	public void postNotificationName(String name) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		ArrayList<NSNotificationObject> nameValue = this.nameMap.get(name);
		if (nameValue != null) {
			for (int i = 0; i < nameValue.size(); i++) {
				NSNotificationObject object = nameValue.get(i);
				object.method.invoke(object.object);
			}
		}
	}
	
	public void postNotificationName(String name,Map<String,Object>map) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		ArrayList<NSNotificationObject> nameValue = this.nameMap.get(name);
		if (nameValue != null) {
			for (int i = 0; i < nameValue.size(); i++) {
				
				NSNotificationObject object = nameValue.get(i);
				NSNotification notification = new NSNotification(name, map);
				System.out.print(object.method);
				object.method.invoke(object.object,(Object)notification);
			}
		}
	}
	//�Ƴ��������м���
	public void removeObserver(Object observer)
	{
		ArrayList<NSNotificationObject> objectList = objectMap.get(observer);
		if (objectList != null) {
			for (int i = 0; i < objectList.size(); i++) {
				NSNotificationObject notificationObject = objectList.get(i);
				ArrayList<NSNotificationObject> nameValue = nameMap.get(notificationObject.nameString);
				if (nameValue != null) {
					if(nameValue.contains(notificationObject))
					{
						nameValue.remove(notificationObject);
					}
				}
			}
			objectMap.remove(observer);
		}
	}
	//�Ƴ�����ĳһ������
	public void removeObserver(Object observer,String name)
	{
		ArrayList<NSNotificationObject> objectList = objectMap.get(observer);
		if (objectList != null) {
			for (int i = 0; i < objectList.size(); i++) {
				NSNotificationObject notificationObject = objectList.get(i);
				ArrayList<NSNotificationObject> nameValue = nameMap.get(name);
				if (nameValue != null && notificationObject.nameString.equals(name)) {
					if(nameValue.contains(notificationObject))
					{
						nameValue.remove(notificationObject);
						objectList.remove(notificationObject);
					}
				}
			}
		}
	}

	
	private class NSNotificationObject {
		public Object object;
		public String nameString;
		public Method method;
		public NSNotificationObject(Object object , Method method, String name)
		{
			this.object = object;
			this.method = method;
			this.nameString = name;
		}
	}

}

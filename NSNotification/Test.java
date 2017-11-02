package com.NSNotification;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;


public class Test {
//	 @SuppressWarnings("rawtypes")
	    public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {

		 Test textTest = new Test();
		 Class<Test> clazz = (Class<Test>) textTest.getClass(); 
	     Method method = clazz.getMethod("objectText1");
		 NSNotificationCenter.defaultCenter().addObserver(textTest, method, "huang");
		 
		 NSNotificationCenter.defaultCenter().postNotificationName("huang");
		 
		 
		 try {          
		        // parameter type is null
			 Method method2 = clazz.getDeclaredMethod("objectText2",NSNotification.class);
			 NSNotificationCenter.defaultCenter().addObserver(textTest, method2, "huang2");
			 Map<String, Object> map = new HashMap<String, Object>();
			 map.put("1111", "2222");
			 NSNotificationCenter.defaultCenter().postNotificationName("huang2",map);
		     }
		     catch(NoSuchMethodException e) {
		        System.out.println(e.toString());
		     }
		 
	    
	    }
	 
	 
	 public void objectText2(NSNotification notification) {
		 System.out.print("测试成功2");
		 System.err.print(notification.userInfo());
		 System.out.print("测试成功3");
	}
	 public void objectText1() {
		 System.out.print("测试成功1");
	}
	 protected void finalize() {
	        System.out.println("~A()");
	 }
}

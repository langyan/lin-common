package com.lin.common.reflect;

public class ClassLoaderTest {
	
	public static void main(String[] args) throws ClassNotFoundException { 
		
		 ClassLoader loader = Thread.currentThread().getContextClassLoader();
	        System.out.println(loader);
	        System.out.println(loader.getParent());
	        System.out.println(loader.getParent().getParent());
	        
        ClassLoader loaderME = ClassLoaderTest.class.getClassLoader(); 
        System.out.println(loaderME); 
        //使用ClassLoader.loadClass()来加载类，不会执行初始化块 
//        loader.loadClass("com.lin.common.reflect.Test2"); 
        //使用Class.forName()来加载类，默认会执行初始化块 
        Class.forName("com.lin.common.reflect.Test2"); 
        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块 
        //Class.forName("Test2", false, loader); 
} 

}

package com.lin.common.lang;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface HelloWorld {
    void sayHello();
}

class HelloWorldImpl implements HelloWorld {
    public void sayHello() {
        System.out.println("Hello world!");
    }
}

public class ProxyApplication {
    public static void main(String[] args) {
        HelloWorldImpl realObject = new HelloWorldImpl();
        HelloWorld proxyInstance = (HelloWorld) Proxy.newProxyInstance(
                HelloWorldImpl.class.getClassLoader(), // 使用目标类的类加载器
                new Class[]{HelloWorld.class}, // 代理类需要实现的接口列表
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 在调用目标方法前可以插入自定义逻辑
                        System.out.println("Before method call");
                        // 调用目标对象的方法
                        Object result = method.invoke(realObject, args);
                        // 在调用目标方法后可以插入自定义逻辑
                        System.out.println("After method call");
                        return result;
                    }
                });

        proxyInstance.sayHello();
    }
}
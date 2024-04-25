package com.lin.common.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
class HelloWorld {
    public void sayHello() {
        System.out.println("Hello world!");
    }
}

public class DemoApplication {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        // 设置需要代理的类
        enhancer.setSuperclass(HelloWorld.class);

        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, java.lang.reflect.Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("Before method call");
                Object result = proxy.invokeSuper(obj, args); // 调用父类的方法
                System.out.println("After method call");
                return result;
            }
        });

        HelloWorld proxy = (HelloWorld) enhancer.create(); // 创建代理对象
        proxy.sayHello(); // 通过代理对象调用方法
    }
}

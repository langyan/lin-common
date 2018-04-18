package com.lin.common.vm.gc;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 借助CGLib使方法区出现内存溢出异常
 *
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args1, MethodProxy proxy) throws Throwable {
                    return proxy.invoke(obj, args1);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject{

    }
}

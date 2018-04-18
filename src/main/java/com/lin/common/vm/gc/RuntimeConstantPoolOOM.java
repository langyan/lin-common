package com.lin.common.vm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池导致的内存溢出异常
 *
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        //使用list保持常量池引用，避免Full Gc 回收常量池行为
        List<String> list = new ArrayList<String>();
        //10m 的PermSize 在integer范围内足够产生OOM
        int i= 0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }
}

package com.lin.common.vm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * Java 堆溢出
 *
 * VM Args : -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * 将堆的最小值和最大值设置成一样即可避免自动扩展，-XX:+HeapDumpOnOutOfMemoryError虚拟机出现内存异常时Dump出当前的内存堆转存储快照
 *
 * @since 1.0
 */
public class HeapOOM {

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        while (true) {
            list.add(new OOMObject());
        }
    }

    static class OOMObject {
    }
}

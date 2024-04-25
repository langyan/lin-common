package com.lin.common.lang;

import java.lang.invoke.*;

public class Main {
    public static void main(String[] args) throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType typeOfTarget = MethodType.methodType(void.class);
        MethodHandle targetMh = lookup.findStatic(Main.class, "target", typeOfTarget);

        targetMh.invoke(); // prints 'invoking target'
    }

    public static void target() {
        System.out.println("invoking target");
    }
}
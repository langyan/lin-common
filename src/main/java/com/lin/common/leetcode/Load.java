/**
 * 
 */
package com.lin.common.leetcode;

/**
 * @author michaellin
 *
 */
public class Load extends AbstractLoad {

    private static Load instance = new Load();

    private Load() {

        System.out.println("Load");

    }

    public void test() {

    }

    public static void main(String[] args) {
        instance.test();
    }

}

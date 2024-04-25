/**
 * 
 */
package com.lin.common.binary;

import java.util.BitSet;

import org.junit.jupiter.api.Test;

/**
 * @author michaellin
 *
 */
public class BitSetTest {

    @Test
    public void test() {
        BitSetTest a = new BitSetTest();
        a.containChars("how do you do");
    }

    public void containChars(String str) {
        BitSet set = new BitSet();
        for (int i = 0; i < str.length(); i++) {
            set.set(str.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int size = set.size();

        System.out.println(size);

        for (int i = 0; i < size; i++) {
            if (set.get(i)) {
                sb.append((char)i);
            }
        }
        sb.append("]");

        System.out.println(sb.toString());
    }

}

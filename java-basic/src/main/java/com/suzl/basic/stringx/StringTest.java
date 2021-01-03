package com.suzl.basic.stringx;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 字符串相关的测试
 *
 * @author Suz1
 * @date 2020/10/12 9:34 上午
 */
@Slf4j
public class StringTest {
    private static final String HIERARCHY_REGEX      = "([0-9]|,|\\.)+";
    private static final String EACH_HIERARCHY_REGEX = "[0-9]\\d*\\.?\\d*";

    @Test
    public void testRegex() {
        String s = "0,20";
        System.out.println(s.matches(HIERARCHY_REGEX));
    }

    @Test
    public void formatTest() {
        String s = "Hi %s, your score is %d!";
        // System.out.println(s.formatted("Alice", 80));
        System.out.println(String.format("Hi %s, your score is %.2f!", "Bob", 59.5));
    }

    @Test
    public void poolTest() {
        String tableNoKey = String.format("%04d", 1);
        String format = String.format("%s", "hah,");
        System.out.println(format);
        System.out.println(tableNoKey);
    }


    @Test
    public void splitTest() {
        String str = "a,b,c,,";
        String[] ary = str.split(",");
        // 预期大于 3，结果是 3
        System.out.println(ary.length);
    }

}
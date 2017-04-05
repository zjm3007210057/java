package com.design.prototype;

import java.util.Arrays;

/**
 * Created by zjm on 2017/3/24.
 */
public class CloneTest implements Cloneable {

    private String name;

    private String[] arrs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getArrs() {
        return arrs;
    }

    public void setArrs(String[] arrs) {
        this.arrs = arrs;
    }

    @Override
    public String toString() {
        return "CloneTest{" +
                "name='" + name + '\'' +
                ", arrs=" + Arrays.toString(arrs) +
                '}';
    }

    public static void main(String[] args) {
        CloneTest test = new CloneTest();
        CloneTest ct;
        test.setName("张三");
        test.setArrs(new String[]{"a", "b", "c"});
        try {
            ct = (CloneTest) test.clone();
            ct.setName("李四");
//            ct.setArrs(new String[]{"d", "e", "f"});
            ct.getArrs()[0] = "d";
            ct.getArrs()[1] = "e";
            ct.getArrs()[2] = "f";
            System.out.println(test);
            System.out.println(ct);
            System.out.println(ct.getArrs() == test.getArrs());//hash code is same
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}

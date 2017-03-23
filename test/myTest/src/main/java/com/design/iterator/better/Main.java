package com.design.iterator.better;

/**
 * Created by zjm on 2017/3/21.
 */
public class Main {

    public static void main(String[] args) {
        Company company = new Company();
        company.addEmployee(new Employee("张三"));
        company.addEmployee(new Employee("李四"));
        company.addEmployee(new Employee("王五"));
        company.addEmployee(new Employee("赵六"));
        company.addEmployee(new Employee("刘一"));
        company.addEmployee(new Employee("邓七"));
        company.addEmployee(new Employee("钱二"));
        Iiterator iterator = company.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("==========分割线===============");
        while(iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }
    }
}

package com.design.iterator.better;

/**
 * Created by zjm on 2017/3/21.
 */
public class Company implements Collection{

    private Employee[] employees;

    private int index;

    private static final int DEFAULT_SIZE = 5;

    public Company(int size) {
        if(size < 0){
            throw new RuntimeException("Array's length can not be negative !");
        }
        index = 0;
        employees = new Employee[size];
    }

    public Company(){
        employees = new Employee[DEFAULT_SIZE];
    }

    public void addEmployee(Employee employee){
        if(employees.length <= index){
            System.out.println("========扩容=======");
            Employee[] newEmployees = new Employee[2 * index];
            System.arraycopy(employees, 0, newEmployees, 0, index);
            employees = newEmployees;
        }
        employees[index] = employee;
        index++;
    }

    public Employee getEmployeeAtIndex(int point){
        if(point >= employees.length){
            System.out.println("你输入的下标过大，目前公司没有这么多人，所以你得到一个null的结果！");;
            return null;
        }
        return employees[point];
    }

    public int getSize(){
        return index;
    }

    @Override
    public Iiterator iterator() {
        return new CompanyIterator(this);
    }
}

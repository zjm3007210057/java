package com.design.iterator.better;

/**
 * Created by zjm on 2017/3/21.
 */
public class CompanyIterator implements Iiterator {

    private Company company;

    private int nextIndex;

    private int preIndex;

    public CompanyIterator(Company company) {
        this.company = company;
        preIndex = company.getSize();
        nextIndex = 0;
    }

    @Override
    public Employee next() {
        Employee employee = company.getEmployeeAtIndex(nextIndex);
        nextIndex++;
        return employee;
    }

    @Override
    public Employee previous() {
        Employee employee = company.getEmployeeAtIndex(preIndex-1);
        preIndex--;
        return employee;
    }

    @Override
    public boolean hasNext() {
        if(nextIndex < company.getSize()){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasPrevious() {
        if(preIndex > 0){
            return true;
        }
        return false;
    }
}

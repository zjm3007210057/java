package com.design.iterator;

/**
 * Created by zjm on 2017/3/21.
 */
public class BookMain {

    public static void main(String[] args) {
        BookShelf shelf = new BookShelf(5);
        shelf.addBook(new Book("1", "zs"));
        shelf.addBook(new Book("2", "ls"));
        shelf.addBook(new Book("3", "ww"));
        shelf.addBook(new Book("4", "ss"));
        shelf.addBook(new Book("5", "za"));
        shelf.addBook(new Book("6", "tx"));
        shelf.addBook(new Book("7", "dx"));
        Iterator iterator = shelf.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

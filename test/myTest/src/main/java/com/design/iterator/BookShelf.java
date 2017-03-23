package com.design.iterator;

/**
 * Created by zjm on 2017/3/21.
 */
public class BookShelf implements Aggregator {

    private Book[] books;

    private int last;

    private static final int DEFAULT_SIZE = 10;

    public BookShelf(){
        books = new Book[DEFAULT_SIZE];
    }

    public BookShelf(int size) {
        books = new Book[size];
    }

    public Book getBookAt(int index){
        if(index >= last){
            throw new ArrayIndexOutOfBoundsException("BookShelf does not have so much books !");
        }
        return books[index];
    }

    public void addBook(Book book){
        if(last >= books.length){
            System.out.println("BookShelf has full with book, can not add anymore !");
            System.out.println("So, this book : " + book + "does not add success !");
            System.out.println("please use other BookShelf, sorry !");
            return;
        }
        books[last] = book;
        last ++;
    }

    public int getBookSize(){
        return last;
    }

    @Override
    public Iterator iterator() {
        return new BookShelfIterator(this);
    }
}

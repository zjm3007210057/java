package com.design.iterator;

/**
 * Created by zjm on 2017/3/21.
 */
public class BookShelfIterator implements Iterator<Book> {

    private BookShelf bookShelf;

    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if(index < bookShelf.getBookSize()){
            return true;
        }
        return false;
    }

    @Override
    public Book next() {
        Book book = bookShelf.getBookAt(index);
        index++;
        return book;
    }
}

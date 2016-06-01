package by.ansgar.android.booklist.dao;

import java.util.List;
import java.util.UUID;

import by.ansgar.android.booklist.entity.Book;

/**
 * Created by kirila on 27.5.16.
 */
public interface BookDAO {
    public void addBook(Book book);

    public void updateBook(Book book);

    public void deleteBook(Book book);

    public Book getBook(UUID id);

    public List<Book> getAllBook();
}

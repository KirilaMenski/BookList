package by.ansgar.android.booklist.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import by.ansgar.android.booklist.entity.Book;

/**
 * Created by kirila on 29.5.16.
 */
public class BookCursorWrapper extends CursorWrapper {

    public BookCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Book getBook(){
        String uuidString = getString(getColumnIndex(BookDBSchema.BookTable.Cols.UUID));
        String bookTitle = getString(getColumnIndex(BookDBSchema.BookTable.Cols.BOOK_TITLE));
        String authorName = getString(getColumnIndex(BookDBSchema.BookTable.Cols.AUTHOR_NAME));

        Book book = new Book(UUID.fromString(uuidString));
        book.setBook(bookTitle);
        book.setAuthor(authorName);

        return book;
    }

}

package by.ansgar.android.booklist.daoImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import by.ansgar.android.booklist.dao.BookDAO;
import by.ansgar.android.booklist.database.BookBaseHelper;
import by.ansgar.android.booklist.database.BookCursorWrapper;
import by.ansgar.android.booklist.database.BookDBSchema;
import by.ansgar.android.booklist.entity.Book;

/**
 * Created by kirila on 27.5.16.
 */
public class BookDAOImpl implements BookDAO {

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public BookDAOImpl(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new BookBaseHelper(mContext).getWritableDatabase();
    }

    @Override
    public void addBook(Book book) {
        ContentValues values = getContentValues(book);
        mDatabase.insert(BookDBSchema.BookTable.NAME, null, values);
    }

    @Override
    public void updateBook(Book book) {
        String uuidString = book.getId().toString();
        ContentValues values = getContentValues(book);
        mDatabase.update(BookDBSchema.BookTable.NAME,
                values,
                BookDBSchema.BookTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    @Override
    public void deleteBook(Book book) {
        String uuidString = book.getId().toString();
        mDatabase.delete(BookDBSchema.BookTable.NAME,
                BookDBSchema.BookTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    @Override
    public Book getBook(UUID id) {
        BookCursorWrapper cursor = query(
                BookDBSchema.BookTable.Cols.UUID + " = ?",
                new String[]{id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getBook();
        } finally {
            cursor.close();
        }
    }

    @Override
    public List<Book> getAllBook() {
        List<Book> books = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            Book book = new Book();
//            book.setId(UUID.randomUUID());
//            book.setBook("Book #" + i);
//            book.setAuthor("Author #" + i);
//            books.add(book);
//        }
        BookCursorWrapper cursor = query(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                books.add(cursor.getBook());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return books;
    }

    private static ContentValues getContentValues(Book book) {
        ContentValues values = new ContentValues();
        values.put(BookDBSchema.BookTable.Cols.UUID, book.getId().toString());
        values.put(BookDBSchema.BookTable.Cols.BOOK_TITLE, book.getBook());
        values.put(BookDBSchema.BookTable.Cols.AUTHOR_NAME, book.getAuthor());

        return values;
    }

    private BookCursorWrapper query(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                BookDBSchema.BookTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new BookCursorWrapper(cursor);
    }

}

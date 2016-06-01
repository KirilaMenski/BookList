package by.ansgar.android.booklist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kirila on 29.5.16.
 */
public class BookBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "book.db";

    public BookBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + BookDBSchema.BookTable.NAME
                + "("
                + " _id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BookDBSchema.BookTable.Cols.UUID + ", "
                + BookDBSchema.BookTable.Cols.BOOK_TITLE + ", "
                + BookDBSchema.BookTable.Cols.AUTHOR_NAME
                + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

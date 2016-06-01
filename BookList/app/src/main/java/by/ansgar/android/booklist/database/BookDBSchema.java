package by.ansgar.android.booklist.database;

/**
 * Created by kirila on 29.5.16.
 */
public class BookDBSchema {

    public static final class BookTable{
        public static final String NAME = "books";

        public static final class Cols{
            public static final String UUID = "uuid";
            public static final String BOOK_TITLE = "book_title";
            public static final String AUTHOR_NAME = "author_name";
        }

    }

}

package by.ansgar.android.booklist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

import by.ansgar.android.booklist.dao.BookDAO;
import by.ansgar.android.booklist.daoImpl.BookDAOImpl;
import by.ansgar.android.booklist.entity.Book;

/**
 * Created by kirila on 29.5.16.
 */
public class BookPagerActivity extends AppCompatActivity {

    public static final String EXTRA_BOOK_ID = "by.ansgar.android.booklist.book_id";

    private EditText mBookEdit, mAuthorEdit;
    private Button mDeleteBtn, mUpdateBtn;
    private BookDAO mBookDAO;
    private Book mBooks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_inf_activity);

        final UUID bookId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_BOOK_ID);

        mBookDAO = new BookDAOImpl(this);
        mBooks = mBookDAO.getBook(bookId);

        mBookEdit = (EditText) findViewById(R.id.edit_book_title);
        mBookEdit.setText(mBooks.getBook());
        mAuthorEdit = (EditText) findViewById(R.id.edit_author_name);
        mAuthorEdit.setText(mBooks.getAuthor());

        mUpdateBtn = (Button) findViewById(R.id.update_book);
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setId(bookId);
                book.setBook(mBookEdit.getText().toString());
                book.setAuthor(mAuthorEdit.getText().toString());
                mBookDAO.updateBook(book);
                BookPagerActivity.this.finish();
            }
        });

        mDeleteBtn = (Button) findViewById(R.id.delete_book);
        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBookDAO.deleteBook(mBooks);
                BookPagerActivity.this.finish();
            }
        });

    }
}

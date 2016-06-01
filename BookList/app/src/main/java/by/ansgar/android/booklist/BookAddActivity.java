package by.ansgar.android.booklist;

import android.content.Intent;
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
public class BookAddActivity extends AppCompatActivity {

    private EditText mAddBook, mAddAuthor;
    private Button mAddBtn;
    private BookDAO mBookDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_book_activity);
        mBookDAO = new BookDAOImpl(this);

        mAddBook = (EditText) findViewById(R.id.add_edit_book_title);
        mAddAuthor = (EditText) findViewById(R.id.add_edit_author_name);
        mAddBtn = (Button) findViewById(R.id.add_book);
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setId(UUID.randomUUID());
                book.setBook(mAddBook.getText().toString());
                book.setAuthor(mAddAuthor.getText().toString());
                mBookDAO.addBook(book);
                Intent intent = new Intent(BookAddActivity.this, BookListActivity.class);
                BookAddActivity.this.finish();
                startActivity(intent);
            }
        });

    }
}

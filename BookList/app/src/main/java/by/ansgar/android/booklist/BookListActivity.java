package by.ansgar.android.booklist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import by.ansgar.android.booklist.dao.BookDAO;
import by.ansgar.android.booklist.daoImpl.BookDAOImpl;
import by.ansgar.android.booklist.entity.Book;

public class BookListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ModelAdapter mAdapter;

    private BookDAO mBookDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_activity);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();

    }

    private void updateUI() {
        mBookDAO = new BookDAOImpl(this);
        List<Book> books = mBookDAO.getAllBook();
        mAdapter = new ModelAdapter(books);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class ModelHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mBookTitle, mAuthorName;


        private Book mBook;

        public ModelHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            mBookTitle = (TextView) itemView.findViewById(R.id.list_item_book_title);
            mAuthorName = (TextView) itemView.findViewById(R.id.list_item_author_name);

        }

        public void bindBook(Book book) {
            mBook = book;
            mBookTitle.setText("<<" + mBook.getBook() + ">> - ");
            mAuthorName.setText(mBook.getAuthor());
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(BookListActivity.this, BookPagerActivity.class);
            intent.putExtra(BookPagerActivity.EXTRA_BOOK_ID, mBook.getId());
            startActivity(intent);
        }
    }

    private class ModelAdapter extends RecyclerView.Adapter<ModelHolder> {

        private List<Book> mBooksModel;

        public ModelAdapter(List<Book> books) {
            mBooksModel = books;
        }

        @Override
        public ModelHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.list_item_book, parent, false);
            return new ModelHolder(view);
        }

        @Override
        public void onBindViewHolder(ModelHolder holder, int position) {
            Book book = mBooksModel.get(position);
            holder.bindBook(book);
        }

        @Override
        public int getItemCount() {
            return mBooksModel.size();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add_book:
                Intent intent = new Intent(BookListActivity.this, BookAddActivity.class);
                startActivity(intent);
            case R.id.menu_item_exit:
                android.os.Process.killProcess(android.os.Process.myPid());
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}

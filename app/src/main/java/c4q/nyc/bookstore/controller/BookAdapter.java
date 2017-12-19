package c4q.nyc.bookstore.controller;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import c4q.nyc.bookstore.R;
import c4q.nyc.bookstore.RSSPullService;
import c4q.nyc.bookstore.model.BookModel;

/**
 * Created by Wayne Kellman on 12/18/17.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private static String TAG = "MainActivity";
    private BookModel[] bookModels;
    private ViewGroup parent;

    public BookAdapter(BookModel[] bookModels) {
        this.bookModels = bookModels;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview, parent, false);
        this.parent = parent;
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBind(bookModels[position]);
    }


    @Override
    public int getItemCount() {
        return bookModels.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView book_id, cat, name, author, series_t, sequence_i, genre_s, inStock, price, pages_i, link;
        private Button downloadButton;
        public ViewHolder(View itemView) {
            super(itemView);
            book_id = itemView.findViewById(R.id.book_id);
            cat = itemView.findViewById(R.id.cat);
            name = itemView.findViewById(R.id.name);
            author = itemView.findViewById(R.id.author);
            sequence_i = itemView.findViewById(R.id.sequence_i);
            series_t = itemView.findViewById(R.id.series_t);
            genre_s = itemView.findViewById(R.id.genre_s);
            inStock = itemView.findViewById(R.id.inStock);
            price = itemView.findViewById(R.id.price);
            pages_i = itemView.findViewById(R.id.pages_i);
            link = itemView.findViewById(R.id.link);
            downloadButton = itemView.findViewById(R.id.downloadButton);
            downloadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent mServiceIntent = new Intent(parent.getContext(), RSSPullService.class);
                    mServiceIntent.setData(Uri.parse(link.getText().toString()));
                    parent.getContext().startService(mServiceIntent);
                    Log.d(TAG, "fired off rsspullservice");

                }
            });
        }

        public void onBind(BookModel bookModel){
            book_id.setText(bookModel.getId());
            String catString = bookModel.getCat()[0] + " : " + bookModel.getCat()[1];
            cat.setText(catString);
            name.setText(bookModel.getName());
            author.setText(bookModel.getAuthor());
            series_t.setText(bookModel.getSeries_t());
            sequence_i.setText(String.valueOf(bookModel.getSequence_i()));
            genre_s.setText(bookModel.getGenre_s());
            inStock.setText(String.valueOf(bookModel.isInStock()));
            price.setText(String.valueOf(bookModel.getPrice()));
            pages_i.setText(String.valueOf(bookModel.getPages_i()));
            link.setText(bookModel.getLink());

        }
    }
}

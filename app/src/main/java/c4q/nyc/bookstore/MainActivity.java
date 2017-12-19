package c4q.nyc.bookstore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import c4q.nyc.bookstore.controller.BookAdapter;
import c4q.nyc.bookstore.model.BookModel;
import c4q.nyc.bookstore.networking.BookStoreApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    protected MainActivity mainActivity;
    private String BookBaseUrl = "https://gist.githubusercontent.com/justiceo/";
    private BookModel[] bookModels;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;


        getBookModel();

        recyclerView = findViewById(R.id.recycler_view);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initRecView();
            }
        }, 5000);



        // 1. Define the filter
        // The filter's action is BROADCAST_ACTION
        IntentFilter statusIntentFilter = new IntentFilter(
                RSSPullService.BROADCAST_ACTION);

        // Adds a data filter for the HTTP scheme
        // statusIntentFilter.addDataScheme("http");

        // 2. Register the BroadcastReceiver and IntentFilter with the system
        // Instantiates a new DownloadStateReceiver
        DownloadStateReceiver mDownloadStateReceiver =
                new DownloadStateReceiver();
        // Registers the DownloadStateReceiver and its intent filters
        LocalBroadcastManager.getInstance(this).registerReceiver(
                mDownloadStateReceiver,
                statusIntentFilter);
    }

    private void initRecView() {
        BookAdapter hackerAdapter = new BookAdapter(bookModels);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setAdapter(hackerAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void getBookModel() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BookBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        BookStoreApi bookService = retrofit.create(BookStoreApi.class);
        Call<BookModel[]> getModel = bookService.getModel();
        getModel.enqueue(new Callback<BookModel[]>() {
            @Override
            public void onResponse(Call<BookModel[]> call, Response<BookModel[]> response) {
                bookModels = response.body();
                Log.d(TAG, "author " + bookModels[0].getAuthor());
            }

            @Override
            public void onFailure(Call<BookModel[]> call, Throwable t) {

            }
        });
    }

    public void downloadInBackground(View view) {
    }

//    public void downloadInBackground(View v) {
//        Intent mServiceIntent = new Intent(this, RSSPullService.class);
//        mServiceIntent.setData(Uri.parse("http://google.com"));
//        startService(mServiceIntent);
//        Log.d(TAG, "fired off rsspullservice");
//    }

    // Broadcast receiver for receiving status updates from the IntentService
    private class DownloadStateReceiver extends BroadcastReceiver
    {
        String TAG = "DownloadStateReceiver";
        // Prevents instantiation
        private DownloadStateReceiver() {
        }

        // Called when the BroadcastReceiver gets an Intent it's registered to receive
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive fired");
            String data = intent.getStringExtra(RSSPullService.EXTENDED_DATA_STATUS);
            Toast.makeText(MainActivity.this, "Download complete", Toast.LENGTH_SHORT).show();
            TextView tv = mainActivity.findViewById(R.id.textView);
            recyclerView.getAdapter().
            tv.setText("Last downloaded: " + data);
        }
    }
}

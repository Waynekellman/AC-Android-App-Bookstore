package c4q.nyc.bookstore;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    protected MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

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

    public void downloadInBackground(View v) {
        Intent mServiceIntent = new Intent(this, RSSPullService.class);
        mServiceIntent.setData(Uri.parse("http://google.com"));
        startService(mServiceIntent);
        Log.d(TAG, "fired off rsspullservice");
    }

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
            tv.setText("Last downloaded: " + data);
        }
    }
}

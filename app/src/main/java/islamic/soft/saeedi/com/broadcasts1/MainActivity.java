package islamic.soft.saeedi.com.broadcasts1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    ListView lv;
    BrReceiver br;
    List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.listView);

        br = new BrReceiver();

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(br, filter);

    }

    private void loadListView()
    {
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList);
        lv.setAdapter(adapter);
    }

    class BrReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            dataList.add(intent.getAction());
            loadListView();
        }
    }

    @Override
    protected void onDestroy()
    {
        unregisterReceiver(br);
        super.onDestroy();
    }
}
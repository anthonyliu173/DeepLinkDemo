package com.anthony.deeplinkdemo.ItemActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.anthony.deeplinkdemo.R;

/**
 * Created by anthonyliu on 15/9/2.
 */
public class ItemActivity extends AppCompatActivity {

    private TextView txtItem;

    private String section;
    private int item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        Intent intent = getIntent();
        Uri data = intent.getData();

        if (data != null) {

            Log.i("Uri data", data.toString());
            parseDeepLink(data.toString());

        } else {

            Bundle bd = getIntent().getExtras();
            section = bd.getString("section");
            item = bd.getInt("item");

        }

        txtItem = (TextView) findViewById(R.id.txtItemTitle);
        txtItem.setText(section + ": " + String.valueOf(item) + " (" + getString(R.string.detail_info) + ")");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 解析叫醒此app的uri，並且切換到對應的頁面。
     *
     * @param incomingURI 叫醒此app的uri
     * */
    private void parseDeepLink(String incomingURI){

        incomingURI = incomingURI.replace(getString(R.string.deeplink_item_first_parse), "");
        Log.i("Uri first parse",incomingURI);

        section = incomingURI.substring(0, incomingURI.indexOf("/"));
        item = Integer.valueOf(incomingURI.substring(incomingURI.indexOf("/") + 1, incomingURI.length()));

    }

}

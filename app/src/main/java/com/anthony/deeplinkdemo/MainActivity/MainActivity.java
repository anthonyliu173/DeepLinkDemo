package com.anthony.deeplinkdemo.MainActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.anthony.deeplinkdemo.DiscountActivity;
import com.anthony.deeplinkdemo.R;
import com.anthony.deeplinkdemo.SectionFragment.SectionFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TabLayout tabs;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private TextView txtDiscount;

    private String[] TITLES;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Uri data = intent.getData();

        TITLES = new String []{getString(R.string.electronics), getString(R.string.sports), getString(R.string.toys), getString(R.string.books)};

        pager = (ViewPager) findViewById(R.id.pager);
        setupViewPager(pager);

        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(pager);
        tabs.setSelectedTabIndicatorColor(Color.WHITE);
        tabs.setVerticalFadingEdgeEnabled(true);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        setupTabListener();

        txtDiscount = (TextView) findViewById(R.id.txtDiscount);
        txtDiscount.setOnClickListener(this);
        txtDiscount.setVisibility(View.GONE);


        if(data != null) {

            Log.i("Uri data", data.toString());

            parseDeepLink(data.toString());

        }

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.txtDiscount:

                Intent intent = new Intent(MainActivity.this, DiscountActivity.class);
                startActivity(intent);

                break;

        }
    }

    private void setupViewPager(ViewPager viewPager){

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(SectionFragment.newInstance(TITLES[0]), TITLES[0]);
        adapter.addFrag(SectionFragment.newInstance(TITLES[1]), TITLES[1]);
        adapter.addFrag(SectionFragment.newInstance(TITLES[2]), TITLES[2]);
        adapter.addFrag(SectionFragment.newInstance(TITLES[3]), TITLES[3]);

        viewPager.setAdapter(adapter);
    }

    private void setupTabListener()
    {
        tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                pager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {
            }
        });
    }

    /**
     * 解析叫醒此app的uri，並且切換到對應的頁面。
     *
     * @param incomingURI 叫醒此app的uri
     * */
    private void parseDeepLink(String incomingURI){

        incomingURI = incomingURI.replace(getString(R.string.deeplink_main_first_parse), "");
        Log.i("Uri first parse",incomingURI);

        String section = incomingURI;

        switch (section){
            case "electronics":
                pager.setCurrentItem(0);
                break;
            case "sports":
                pager.setCurrentItem(1);
                break;
            case "toys":
                pager.setCurrentItem(2);
                break;
            case "books":
                pager.setCurrentItem(3);
                break;
        }

    }
}

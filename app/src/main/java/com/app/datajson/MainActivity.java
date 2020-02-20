package com.app.datajson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;
    ListView listView;
    String DATA_URL = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts.json";
    static GlobalDataController globalDataController;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init SwipeRefreshLayout and ListView
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);
        // implement setOnRefreshListener event on SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cancel the Visual indication of a refresh
                swipeRefreshLayout.setRefreshing(false);
                refreshLayout();
            }
        });

        listView = findViewById(R.id.listView);

        instance = this;

        globalDataController = new GlobalDataController(DATA_URL);
        globalDataController.execute();
    }

    public static GlobalDataController getGlobalDataController() {
        return globalDataController;
    }

    public static MainActivity get(){
        return instance;
    }

    public void setAdapter(){
        setTitle(globalDataController.getAppLabel());
        listView.setAdapter(new CustomAdapter(this));
    }

    private void refreshLayout(){
        globalDataController.destroy();
        globalDataController = null;
        globalDataController = new GlobalDataController(DATA_URL);
        globalDataController.execute();
    }
}


package com.example.ams.tagmaker;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ams.tagmaker.Adapter.ListAdapter;
import com.example.ams.tagmaker.Db.DataBaseHelper;
import com.example.ams.tagmaker.Models.TagNameModel;

import java.util.List;

public class TagsList extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    DataBaseHelper db = new DataBaseHelper(this);
    List<TagNameModel> datamodel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tags_list);
        datamodel=  db.getListName();

        recyclerView = findViewById(R.id.taglist);
        // use this setting to improve performance if you know that changes
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
      //  mAdapter = new ListAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);



    }
}

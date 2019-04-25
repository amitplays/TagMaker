package com.example.ams.tagmaker;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.ams.tagmaker.Adapter.ListAdapter;
import com.example.ams.tagmaker.Db.DataBaseHelper;
import com.example.ams.tagmaker.Models.TagListModel;
import com.example.ams.tagmaker.Models.TagNameModel;

import java.util.ArrayList;
import java.util.List;

public class TagsList extends Activity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ListAdapter recycler;
    ArrayList<TagNameModel> data = new ArrayList<>();
    TagNameModel dataModel;
    DataBaseHelper db = new DataBaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tags_list);
        TextView heading = findViewById(R.id.heading);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fontBold.ttf");
        heading.setTypeface(typeface);

        recyclerView = findViewById(R.id.taglist);
      getdata();
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recycler = new ListAdapter(TagsList.this, data);
        recyclerView.setAdapter(recycler);


    }

    public void getdata() {

        Cursor cursor = db.getListOfTags();
        if (cursor.getCount() == 0) {

            return;
        }
        while (cursor.moveToNext()) {
            dataModel = new TagNameModel();
            String TagName = cursor.getString(cursor.getColumnIndexOrThrow("TagName"));
            int NoofTags = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("noOfTags")));
            dataModel.setTagName(TagName);
            dataModel.setNumbeOfTags(NoofTags);
            data.add(dataModel);
            // use this setting to improve performance if you know that changes
            // use a linear layout manager

            // specify an adapter (see also next example)
            //  mAdapter = new ListAdapter(myDataset);

        }


    }

}

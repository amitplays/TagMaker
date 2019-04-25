package com.example.ams.tagmaker.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ams.tagmaker.Db.DataBaseHelper;
import com.example.ams.tagmaker.Models.TagListModel;
import com.example.ams.tagmaker.Models.TagNameModel;
import com.example.ams.tagmaker.R;
import com.example.ams.tagmaker.TagsList;

import java.util.ArrayList;

import static android.support.v4.app.ActivityCompat.startActivityForResult;
import static com.google.zxing.integration.android.IntentIntegrator.REQUEST_CODE;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    Context context;
    ArrayList<TagNameModel> mDataset;

    public ListAdapter(Context context, ArrayList<TagNameModel> datamodel) {
        this.context = context;
        mDataset = datamodel;

    }


    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        // create a new view
        View view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.taglist_layout, parent, false);
        MyViewHolder myviewholder = new MyViewHolder(view);

        // use this setting to improve performance if you know that changes
        // use a linear layout manager
        return myviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        TagNameModel tnm = mDataset.get(i);
        myViewHolder.name.setText(tnm.getTagName());
        myViewHolder.noOfTags.setText(String.valueOf(tnm.getNumbeOfTags()));

        myViewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                context.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {

        if (mDataset !=null){


            return mDataset.size();
        }else {
            return 0;
        }
        }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

       public final TextView name, noOfTags;

        // each data item is just a string in this case
        public View view;
        public MyViewHolder(View v) {

            super(v);
            this.view = v;
            name =  v.findViewById((R.id.nameofLayoutTextView));
            noOfTags = v.findViewById(R.id.noOfTagsLayoutTextView);

        }
    }




}




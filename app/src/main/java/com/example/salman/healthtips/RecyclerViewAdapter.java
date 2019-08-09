package com.example.salman.healthtips;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salman on 7/15/2019.
 */
class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView textViewtitle;
    TextView textViewcontent;
    ImageView imageView;
    Intent intent;
    Context context;
    public RecyclerViewHolder(android.view.View itemView) {
        super(itemView);
        context = itemView.getContext();
        textViewtitle = (TextView) itemView.findViewById(R.id.txtTitle);
        textViewcontent = (TextView) itemView.findViewById(R.id.txtcontent);
        imageView = (ImageView) itemView.findViewById(R.id.profile_image);

    }
}

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<Data> listdata = new ArrayList<Data>();

    public RecyclerViewAdapter(List<Data> listdata) {
        this.listdata = listdata;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        android.view.View itemView = layoutInflater.inflate(R.layout.item, parent, false);

        return new RecyclerViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                int temp = holder.textViewcontent.getText().toString().length();
                Bundle bundle = new Bundle();
                bundle.putString("Title", listdata.get(position).getTitle());
                bundle.putString("Content", listdata.get(position).getContent());
                bundle.putString("Image", listdata.get(position).getUrl().toString());

                Intent intent = new Intent(holder.context, Contenview.class);
                intent.putExtras(bundle);
                holder.context.startActivity(intent);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                }
        });
        final String content = listdata.get(position).getContent().substring(0, 25);
        holder.textViewcontent.setText(content + "... ");
        holder.textViewtitle.setText(listdata.get(position).getTitle() + ": ");

        Glide.with(holder.imageView.getContext()).load(listdata.get(position).getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }
}



package com.example.salman.healthtips;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Salman on 7/16/2019.
 */
class gridRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView textViewtitle;
    ImageView imageView;
    CardView    cardView;
   Context context;
    public gridRecyclerViewHolder(android.view.View itemView) {
        super(itemView);

        context = itemView.getContext();

        textViewtitle=(TextView) itemView.findViewById(R.id.Title);
        imageView=(ImageView) itemView.findViewById(R.id.flag);
        cardView = (CardView) itemView.findViewById(R.id.cardview);

    }
}

public class gridRecyclerViewAdapter  extends RecyclerView.Adapter<gridRecyclerViewHolder> {
    private List<griddata> listdata=new ArrayList<griddata>();

    public gridRecyclerViewAdapter(List<griddata> listdata) {
        this.listdata = listdata;
    }

    @Override
    public gridRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        android.view.View itemView = layoutInflater.inflate(R.layout.grid,parent,false);
        return new gridRecyclerViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final gridRecyclerViewHolder holder, final int position) {
holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Bundle bundle=new Bundle();

        bundle.putString("Title",listdata.get(position).getTitlename());
        bundle.putString("Title",listdata.get(position).getTitlename());
        bundle.putString("Title",listdata.get(position).getTitlename());

        Intent intent = new Intent(holder.context,Articles.class);
        bundle.putString("imagearticle",listdata.get(position).getGridimage().toString());
        bundle.putString("imagearticle",listdata.get(position).getGridimage().toString());
        bundle.putString("imagearticle",listdata.get(position).getGridimage().toString());
        bundle.putString("imagearticle",listdata.get(position).getGridimage().toString());
        intent.putExtras(bundle);
        holder.context.startActivity(intent);

    }
});
        holder.textViewtitle.setText(listdata.get(position).getTitlename());
        Glide.with(holder.imageView.getContext()).load(listdata.get(position).getGridimage()).placeholder(R.drawable.placeholder).into(holder.imageView);
      }



    @Override
    public int getItemCount() {
        return listdata.size()/2;
    }
}


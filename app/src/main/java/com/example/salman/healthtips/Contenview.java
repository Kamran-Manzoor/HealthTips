package com.example.salman.healthtips;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class Contenview extends AppCompatActivity {
TextView Content;
TextView Title;
ImageView Image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenview);
        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar_top);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        Content=findViewById(R.id.titlecontent2);
  Image=findViewById(R.id.contentimage);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        Content.setText(bundle.getString("Content").toString());

        mTitle.setText(bundle.getString("Title").toString());
String  url=bundle.getString("Image").toString();
        Glide.with(this).load(url).into(Image);

    }
}

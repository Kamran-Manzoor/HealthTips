package com.example.salman.healthtips;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Articles extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Data> listartile = new ArrayList<Data>();
ImageView mainImage;
ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);

//        action.setTitle("Hairs");

        data();
        mainImage=findViewById(R.id.articleimage);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);}
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String url=bundle.get("imagearticle").toString();
        Glide.with(this).load(url).placeholder(R.drawable.placeholder).into(mainImage);
          recyclerView = findViewById(R.id.recyclerviewarticle);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(listartile);
        recyclerView.setAdapter(adapter);
    }

    public void data() {
        final DatabaseHelper databaseHelper = new DatabaseHelper(this);
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String toast=bundle.get("Title").toString();
        final String name = bundle.getString("Title").toString();
        final DatabaseReference databaseReference = mFirebaseDatabase.getReference().child("Content").child(name).child("Content");
//        action.setTitle(name);

        databaseHelper.create(name);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<String> lst = new ArrayList<String>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    lst.add(String.valueOf(dsp.getKey())); //add result into array list
                }

                if (databaseHelper.getProfilesCount(name.toString()) != dataSnapshot.getChildrenCount()) {

                    databaseHelper.deleteData(name);
                    String content = "";
                    String url = "";
                    for (int i = 0; i < lst.size(); i++) {
                        content = String.valueOf(lst.get(i));
                        databaseHelper.insertData(name, lst.get(i).toString(), dataSnapshot.child(lst.get(i)).child("content").getValue().toString(), dataSnapshot.child(lst.get(i)).child("url").getValue().toString());

                        databaseHelper.close();
                    }

                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Cursor result = databaseHelper.getAllData(name);
        while (result.moveToNext()) {

            String title = result.getString(result.getColumnIndex("Title"));
            String content = result.getString(result.getColumnIndex("Content"));
            String url = result.getString(result.getColumnIndex("Url"));
            listartile.add(new Data(title, content, url));

        }

if(listartile.size()==0){

   Intent refresh=new Intent(getApplicationContext(),Spalash2.class);
startActivity(refresh);
}
    }
}


package com.example.salman.healthtips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

public class slidercontent extends AppCompatActivity {
TextView Title;
TextView content;
ImageView image;
FirebaseDatabase mfirebase;
DatabaseReference mref;

@Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_slidercontent);
content=findViewById(R.id.ctilte2);
image=findViewById(R.id.cimage);
    android.support.v7.widget.Toolbar toolbar=findViewById(R.id.toolbar_top);
    final TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

Intent intent = getIntent();
    Bundle bundle = intent.getExtras();

    mref=mfirebase.getInstance().getReference().child("SlideContent").child(bundle.getString("child"));
mref.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
   String  Content=dataSnapshot.child("Content").getValue(String.class);
   String url=dataSnapshot.child("url").getValue(String.class);
        String title= dataSnapshot.child("Title").getValue(String.class);
        Glide.with(getApplicationContext()).load(url).into(image);
mTitle.setText(title);
content.setText(Content);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});

}
}

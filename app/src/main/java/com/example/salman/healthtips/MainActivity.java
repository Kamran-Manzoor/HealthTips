package com.example.salman.healthtips;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.ACTION_SEND;
import static android.content.Intent.EXTRA_SUBJECT;
import static android.content.Intent.EXTRA_TEXT;
import static android.content.Intent.createChooser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private gridRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<griddata> listgrid=new ArrayList<griddata>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        data();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        data();

        recyclerView = findViewById(R.id.recyclerviewmain);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new gridRecyclerViewAdapter(listgrid);

        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.logout) {
            mAuth = FirebaseAuth.getInstance();
            mAuth.signOut();
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
            finish();
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.befefitofwater) {
            Bundle bundle=new Bundle();
            bundle.putString("child","water");

            Intent intent = new Intent(getApplicationContext(),slidercontent.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.benefitsofvirginoliveoil) {
            Bundle bundle=new Bundle();
            bundle.putString("child","oil");

            Intent intent = new Intent(getApplicationContext(),slidercontent.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.nav_carbs) {
            Bundle bundle=new Bundle();
            bundle.putString("child","carbs");

            Intent intent = new Intent(getApplicationContext(),slidercontent.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.nav_deit) {
            Bundle bundle=new Bundle();
            bundle.putString("child","diet");

            Intent intent = new Intent(getApplicationContext(),slidercontent.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if (id == R.id.nav_relationship) {
            Bundle bundle=new Bundle();
            bundle.putString("child","relationship");

            Intent intent = new Intent(getApplicationContext(),slidercontent.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (id == R.id.nav_egg) {
            Bundle bundle=new Bundle();
            bundle.putString("child","egg");

            Intent intent = new Intent(getApplicationContext(),slidercontent.class);
            intent.putExtras(bundle);
            startActivity(intent);

        }else if (id == R.id.nav_transandfats) {
            Bundle bundle=new Bundle();
            bundle.putString("child","transfats");

            Intent intent = new Intent(getApplicationContext(),slidercontent.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (id == R.id.nav_herbs) {
            Bundle bundle=new Bundle();
            bundle.putString("child","herbs");

            Intent intent = new Intent(getApplicationContext(),slidercontent.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if (id == R.id.Banefitsofvagetables) {
            Bundle bundle=new Bundle();
            bundle.putString("child","vagetables");

            Intent intent = new Intent(getApplicationContext(),slidercontent.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(id==R.id.nav_about)
        {
            Intent intent=new Intent(getApplicationContext(),Aboutus.class);
        startActivity(intent);
        }

        else if(id==R.id.nav_share)
        {
            String share="Sir Ajj Jaldi Ghr Ja Sakta Houn";
            Intent sharingIntent = new Intent(ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBody = share+" cll";
            String shareSub = "Your subject here";
            sharingIntent.putExtra(EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(EXTRA_TEXT, shareBody);
            startActivity(createChooser(sharingIntent, "Share using"));
        }
        /*else if (id == R.id.signup) {
            Intent intent=new Intent(MainActivity.this,Login.class);
            startActivity(intent);

        }
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void data() {


        final DatabaseHelper databaseHelper=new DatabaseHelper(this);
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference =    mFirebaseDatabase.getReference().child("Content");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<String> lst = new ArrayList<String>();
                for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                    lst.add(String.valueOf(dsp.getKey())); //add result into array list
                }

             /*   Toast.makeText(MainActivity.this, databaseHelper.getProfilesCount("Healthtips")+"", Toast.LENGTH_SHORT).show();
             */   if (databaseHelper.getProfilesCount("Healthtips") != dataSnapshot.getChildrenCount()) {

                    databaseHelper.deleteData("Healthtips");
                    int j=lst.size();
                    for (int i = 0; i <j; i++) {
                        databaseHelper.insertData1("Healthtips",dataSnapshot.child(lst.get(i).toString()).child("Title").getValue().toString(),dataSnapshot.child(lst.get(i).toString()).child("url").getValue().toString());

                    }
                    databaseHelper.close();
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Cursor result=databaseHelper.getAllData("Healthtips");
        while (result.moveToNext()){

            String title = result.getString(result.getColumnIndex("Title"));
            String url = result.getString(result.getColumnIndex("Url"));
            listgrid.add(new griddata(title,url));



        }
        if(listgrid.size()==0){
            Intent intent =new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }

    }


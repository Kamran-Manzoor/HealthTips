package com.example.salman.healthtips;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText nam;
    Spinner gender;
    EditText pass;
    EditText emal;
   EditText repass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        Button signup=findViewById(R.id.bt1);
        Button cancel=findViewById(R.id.bt2);
        nam= findViewById(R.id.name);

        gender= findViewById(R.id.occupation);
        pass= findViewById(R.id.password);
        emal= findViewById(R.id.email);
repass=findViewById(R.id.repassword);
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                final String name=nam.getText().toString();
                final String Gender=gender.getSelectedItem().toString();
                final String password=pass.getText().toString();
                final String email=emal.getText().toString();
                final String repassword=repass.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "RE-Enter password is Empty!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!password.equals(repassword))
                {  Toast.makeText(getApplicationContext(), "Password didnt match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Signup.this, "Successfull", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT).show();

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference();
                                    myRef.child(uid).child("personalInfo").child("Name").setValue(name);
                                    myRef.child(uid).child("personalInfo").child("Gender").setValue(Gender);
                                    startActivity(new Intent(Signup.this,MainActivity.class));

                                   /* myRef.child(uid).child("personalInfo").child("Income")setValue(info, new DatabaseReference.CompletionListener() {
                                        @Override
                                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                            startActivity(new Intent(Login.this,MainScreen.class));
                                        }
                                    });
*/
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Signup.this, "Failed to signup", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });


            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,Login.class));

            }
        });
    }
}

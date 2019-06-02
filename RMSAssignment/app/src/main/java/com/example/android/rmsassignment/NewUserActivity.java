package com.example.android.rmsassignment;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class NewUserActivity extends AppCompatActivity {
    private EditText mEditTextName;
    private EditText mEditTextAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add User");

        mEditTextName = findViewById(R.id.et_name);
        mEditTextAge = findViewById(R.id.et_age);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_user_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_save_user:
                saveUser();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveUser(){
        String name = mEditTextName.getText().toString();
        String ageWord = mEditTextAge.getText().toString().trim();

        if(name.trim().isEmpty() || ageWord.isEmpty()){
            Toast.makeText(this, "Please fill all the Field", Toast.LENGTH_SHORT).show();
            return;
        }
        int age = Integer.parseInt(ageWord);
        CollectionReference userCollectionRef = FirebaseFirestore.getInstance().collection("USER");

        userCollectionRef.add(new User(name, age, new Timestamp(new Date())))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(NewUserActivity.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NewUserActivity.this, "Failed, Try Again", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}

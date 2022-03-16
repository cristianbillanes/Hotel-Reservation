package com.example.hotel_reservation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Log_In_forCustomer extends AppCompatActivity {
    private EditText Email,Password;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button SingIN,Register,Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_for_customer);
        Email = findViewById(R.id.EmailAddress_Manager);
        Password = findViewById(R.id.Password_Manager);
        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progress_singIN);
        SingIN = findViewById(R.id.SingIn_Manager);
        Register = findViewById(R.id.Register_Manager);
        Back = findViewById(R.id.Back_SingIN_Manager);
    }
    public void SingIN_Manager(View v){
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        if(emty())return;
        inProgress(true);
        auth.signInWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String UIDkey = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        new DAO_Account().readAccount(UIDkey,"Customer", new DAO_Account.DataStatus_Account() {
                            @Override
                            public void DataIsLoaded(boolean exist) {
                                if(exist){
                                    Toast.makeText(Log_In_forCustomer.this, "User Sing In Success", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Log_In_forCustomer.this,Customer_Home.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();return;
                                }else inProgress(false);

                            }

                            @Override
                            public void DateIsInserted() {

                            }

                            @Override
                            public void DataIsUpdated() {

                            }

                            @Override
                            public void DataIsDeleted() {

                            }
                        });


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                inProgress(false);
                Toast.makeText(Log_In_forCustomer.this, "User Sing In Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Register_Manager(View v){
        String email = Email.getText().toString();
        String password = Password.getText().toString();
        if(emty())return;
        inProgress(true);
        auth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String UIDkey = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        new DAO_Account().addAccount("Customer",UIDkey, new DAO_Account.DataStatus_Account() {
                            @Override
                            public void DataIsLoaded(boolean exist) {

                            }

                            @Override
                            public void DateIsInserted() {
                                Toast.makeText(Log_In_forCustomer.this, "User Register Success", Toast.LENGTH_SHORT).show();
                                inProgress(false);
                            }

                            @Override
                            public void DataIsUpdated() {

                            }

                            @Override
                            public void DataIsDeleted() {

                            }
                        });

                        Toast.makeText(Log_In_forCustomer.this, "User Register Success", Toast.LENGTH_SHORT).show();
                        inProgress(false);

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                inProgress(false);
                Toast.makeText(Log_In_forCustomer.this, "User Register Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Back_Manager(View v){
        finish(); return;
    }
    private void inProgress(Boolean x){
        if(x){
            progressBar.setVisibility(View.VISIBLE);
            SingIN.setEnabled(false);
            Register.setEnabled(false);
            Back.setEnabled(false);
        }
        else {
            progressBar.setVisibility(View.GONE);
            SingIN.setEnabled(true);
            Register.setEnabled(true);
            Back.setEnabled(true);
        }
    }
    private Boolean emty(){
        if(TextUtils.isEmpty(Email.getText().toString())&&TextUtils.isEmpty(Password.getText().toString())){
            Email.setError("Required");
            Password.setError("Required");
            return true;
        }
        if(TextUtils.isEmpty(Email.getText().toString())){
            Email.setError("Required");
            return true;
        }
        if(TextUtils.isEmpty(Password.getText().toString())){
            Password.setError("Required");
            return true;
        }
        return false;
    }
}
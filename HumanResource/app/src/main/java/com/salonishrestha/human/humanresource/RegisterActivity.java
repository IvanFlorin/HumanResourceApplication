package com.salonishrestha.human.humanresource;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.salonishrestha.human.humanresource.models.User;

public class RegisterActivity extends AppCompatActivity {
    EditText email,pass,rpass,name;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email= (EditText) findViewById(R.id.txtEmail);
        pass=(EditText) findViewById(R.id.txtPassword);
        rpass=(EditText) findViewById(R.id.txtConPassword);
        name=(EditText) findViewById(R.id.txtName);
        mAuth = FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(RegisterActivity.this);
        progressDialog.setMessage("Creating user");
        progressDialog.setTitle("User creation in process");

    }

    public void register(View view){
        if(TextUtils.isEmpty(email.getText().toString())){
            email.setError("Email cannot be empty");
            return;
        }

        if(TextUtils.isEmpty(pass.getText().toString())){
            pass.setError("Pass cannot be empty");
            return;
        }

        if(pass.length()<6){
            pass.setError("Pass cannot be less than 6 characters");
            return;
        }


        if(TextUtils.isEmpty(rpass.getText().toString())){
            rpass.setError("Confirm password cannot be empty");
            return;
        }


        if(!pass.getText().toString().equals(rpass.getText().toString())){
            pass.setError("Password do not match");
            return;
        }

        if(TextUtils.isEmpty(name.getText().toString())){
            name.setError("Name cannot be empty");
            return;
        }

        if(TextUtils.isDigitsOnly(name.getText().toString())){
            name.setError("Name cannot be number. Only alphabets");
            return;
        }

        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            task.getException().printStackTrace();
                        }else{
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("users");

                            User user = new User(name.getText().toString(),"user");
                            myRef.child(task.getResult().getUser().getUid()).setValue(user);

                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            finish();


                        }


                    }
                });

    }
}

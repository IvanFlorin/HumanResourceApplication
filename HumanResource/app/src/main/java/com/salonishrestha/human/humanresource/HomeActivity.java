package com.salonishrestha.human.humanresource;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Toast.makeText(HomeActivity.this, "welcome :" + user.getEmail(), Toast.LENGTH_SHORT).show();

        Button btn = (Button) findViewById(R.id.btnaddStaff);

        SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
        System.out.println(sharedPreferences.getString("user_type", null));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddStaffActivity.class));
            }
        });

    }

    public void viewstaff(View view) {
        startActivity(new Intent(getApplicationContext(), StaffListActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}
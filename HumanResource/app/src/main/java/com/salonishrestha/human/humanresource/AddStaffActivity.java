package com.salonishrestha.human.humanresource;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.salonishrestha.human.humanresource.models.Staff;

import org.w3c.dom.Text;

public class AddStaffActivity extends AppCompatActivity {

    TextInputEditText name;
    TextInputEditText address;
    TextInputEditText designation;

    private DatabaseReference mDatabase;

    Staff staff;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        name= (TextInputEditText) findViewById(R.id.txtName);
        address = (TextInputEditText) findViewById(R.id.tf_address);
        designation = (TextInputEditText) findViewById(R.id.tf_desig);

        staff= (Staff) getIntent().getSerializableExtra("staff");

        if(staff!=null){
            name.setText(staff.getName());
            address.setText(staff.getName());
            designation.setText(staff.getName());
        }
        key= getIntent().getStringExtra("id");
    }

    public void addStaff(View view){
        String n= name.getText().toString();
        String a= address.getText().toString();
        String d= designation.getText().toString();
        Staff s = new Staff(n);

        if(staff!=null){
            mDatabase.child("staffs").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(key).setValue(s);
            Toast.makeText(AddStaffActivity.this, "Edited successfully", Toast.LENGTH_SHORT).show();
        }else {

            mDatabase.child("staffs").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).push().setValue(s);
            Toast.makeText(AddStaffActivity.this, "added successfully", Toast.LENGTH_SHORT).show();
        }


        finish();


    }
}

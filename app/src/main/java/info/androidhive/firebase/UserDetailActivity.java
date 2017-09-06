package info.androidhive.firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import info.androidhive.firebase.model.InputData;

public class UserDetailActivity extends AppCompatActivity {

    EditText firstName,lastName,phoneNo,address;
    Button createAccount;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        firstName=(EditText)findViewById(R.id.firstName);
        lastName=(EditText)findViewById(R.id.lastName);
        phoneNo=(EditText)findViewById(R.id.phoneNo);
        address=(EditText)findViewById(R.id.address);
        createAccount=(Button)findViewById(R.id.createAccount);

        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddData();
                Toast.makeText(UserDetailActivity.this, "Account creation successful", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(UserDetailActivity.this,MainActivity.class));
                finish();


            }
        });
    }
    public void AddData() {

        String firstname = firstName.getText().toString().trim();
        String lastname = lastName.getText().toString().trim();
        String phoneno = phoneNo.getText().toString().trim();
        String addr = address.getText().toString().trim();

        InputData saveData=new InputData(firstname,lastname,phoneno,addr);
        databaseReference.push().setValue(saveData);

    }
}

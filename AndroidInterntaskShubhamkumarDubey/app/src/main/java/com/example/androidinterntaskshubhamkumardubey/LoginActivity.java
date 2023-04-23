package com.example.androidinterntaskshubhamkumardubey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginDbHelper databaseHelper;
        Button loginBtn;
        TextInputEditText username = findViewById(R.id.userName);
        TextInputEditText passwrd = findViewById(R.id.loginPass);
        TextView signuptext = findViewById(R.id.createAccTxt);;

        databaseHelper = new LoginDbHelper(this);

        loginBtn = findViewById(R.id.loginButton);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = username.getText().toString();
                String user_pass = passwrd.getText().toString();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

                if(user_name.isEmpty() || user_pass.isEmpty())
                {
                    Toast.makeText(LoginActivity.this,"All Field's Required",Toast.LENGTH_SHORT).show();

                }else {
                        Boolean checkdetails = databaseHelper.checkuserpassword(user_name,user_pass);

                        if(checkdetails ==true)
                        {
                            Toast.makeText(LoginActivity.this,"Login Successfull",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(i);
                        }else {
                            Toast.makeText(LoginActivity.this,"Invalid Details",Toast.LENGTH_SHORT).show();

                        }

                    }
            }
        });

        signuptext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(i);
            }
        });


}

}
package com.example.androidinterntaskshubhamkumardubey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidinterntaskshubhamkumardubey.databinding.ActivitySignUpBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.sql.DatabaseMetaData;

public class SignUpActivity extends AppCompatActivity {

//    ActivitySignUpBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        LoginDbHelper databaseHelper;
        Button creatAccBtn;
        TextView loginText = findViewById(R.id.goto_LoginText);
        TextInputEditText username = findViewById(R.id.signupName);
        TextInputEditText passwrd = findViewById(R.id.signUpPass);
        TextInputEditText confrmpass = findViewById(R.id.confirmPass);


        creatAccBtn = findViewById(R.id.createAccButton);


        databaseHelper = new LoginDbHelper(this);
        creatAccBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

                String user_name = username.getText().toString();
                String user_pass = passwrd.getText().toString();
                String confrm_pass = confrmpass.getText().toString();

                if(user_name.isEmpty() || user_pass.isEmpty() || confrm_pass.isEmpty())
                {
                    Toast.makeText(SignUpActivity.this,"All Field's Required",Toast.LENGTH_SHORT).show();
                }else {
                    if(user_pass.equals(confrm_pass)){
                        Boolean checkUserName = databaseHelper.checkUsername(user_name);

                        if(checkUserName == false){
                            Boolean insert = databaseHelper.insertData(user_name,user_pass);
                            Toast.makeText(SignUpActivity.this,"user created",Toast.LENGTH_SHORT).show();

                            if(insert == true ){
                            Toast.makeText(SignUpActivity.this,"Signup Successful",Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(SignUpActivity.this,"Signup Failed",Toast.LENGTH_SHORT).show();

                        }
                        }
                        else {
                            Toast.makeText(SignUpActivity.this,"User Already exists",Toast.LENGTH_SHORT).show();

                        }


                    }else {
                        Toast.makeText(SignUpActivity.this,"Invalid password",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });




    }
}
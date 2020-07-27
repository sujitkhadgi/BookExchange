package com.example.bookexchange1.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookexchange1.API.UserAPI;
import com.example.bookexchange1.BLL.UserBLL;
import com.example.bookexchange1.Model.User;
import com.example.bookexchange1.R;
import com.google.android.material.textfield.TextInputLayout;


import org.json.JSONObject;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bookexchange1.URL.URL.userAPI;

public class LoginActivity extends AppCompatActivity {
 EditText etEmail, etPassword;
 TextInputLayout email_input_layout,password_input_layout;
 Button btnLogin;
 TextView linkRegister;
 UserBLL userBLL= new UserBLL();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        linkRegister=findViewById(R.id.link_register);
        email_input_layout=findViewById(R.id.email_input_layout);
        password_input_layout=findViewById(R.id.password_input_layout);
        etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    email_input_layout.setErrorEnabled(false);
                }
                else
                {
                    if(etEmail.getText().toString().trim().length() > 0){
                        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                                "[a-zA-Z0-9_+&*-]+)*@" +
                                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                                "A-Z]{2,7}$";
                        Pattern pattern=Pattern.compile(emailRegex);
                        Matcher matcher=pattern.matcher(etEmail.getText());
                        if(!matcher.matches()) {
                            email_input_layout.setErrorEnabled(true);
                            email_input_layout.setError("Invalid email format");
                        }

                    }
                }
            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    password_input_layout.setErrorEnabled(false);
                }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etEmail.getText().toString();
                String password =etPassword.getText().toString();
               validation();

          boolean result= userBLL.login(email,password);

          if(result)
          {
              Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
              User.t_email=email;
              Intent intent=new Intent(LoginActivity.this,DashboardActivity.class);
              startActivity(intent);
          }
          else
          {
              Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
          }



            }
        });

    }
    public void validation()
    {
       if( TextUtils.isEmpty(etEmail.getText()))
        {
            email_input_layout.setErrorEnabled(true);
            email_input_layout.setError("Please enter email address");
        }
        if( TextUtils.isEmpty(etPassword.getText()))
        {
            password_input_layout.setErrorEnabled(true);
            password_input_layout.setError("Please enter password");
        }
    }
}
 package com.example.shgseller;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.shgseller.ui.RegisterActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

 public class MainActivity extends AppCompatActivity {
    @BindView(R.id.textInputLayoutAccount)  TextInputLayout accountInputText;
    @BindView(R.id.textInputLayoutPassword)   TextInputLayout passwordInputText;
    @BindView(R.id.textInputEditAccount)  TextInputEditText accountEditText;
    @BindView(R.id.textInputEditPassword)   TextInputEditText passwordEditText;
    @BindView(R.id.buttonLogin)  Button buttonLogin;
    @BindView(R.id.buttonRegister)  Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isValidPassword(passwordEditText.getText())){
                    passwordInputText.setError(getString(R.string.invalid_password));
                }else{
                    passwordInputText.setError(null);
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(registerActivity);
            }
        });

        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(isValidPassword(passwordEditText.getText())){
                    passwordInputText.setError(null);
                }
                return false;
            }
        });



    }

    private boolean isValidPassword(@Nullable Editable text){
        return (text!=null && text.length()>=6);
    }
}

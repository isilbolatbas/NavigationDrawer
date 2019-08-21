package com.example.vallason;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class LoginRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);


        LoginFragment loginFragment = new LoginFragment();
        FragmentManager manager = getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.linearLogin, loginFragment).commit();




    }
}

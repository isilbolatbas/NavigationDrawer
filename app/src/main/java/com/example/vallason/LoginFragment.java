package com.example.vallason;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;




/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private static final String PRES_NAME = "loginPreferences";
    EventDatabase eventDatabase;
   public static String user, pass;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean loginCheck;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Button login = v.findViewById(R.id.logButton);
        Button register = v.findViewById(R.id.registerButton);

        final CheckBox checkBox =(CheckBox) v.findViewById(R.id.rememberCheck);
        final EditText username = (EditText) v.findViewById(R.id.userEdit);
        final EditText password = (EditText) v.findViewById(R.id.passEdit);

        eventDatabase=EventDatabase.getDatabase(getContext());
        loginPreferences = getActivity().getSharedPreferences(PRES_NAME, Context.MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        loginCheck = loginPreferences.getBoolean("loginCheck", false);
        if (loginCheck == true) {
            username.setText(loginPreferences.getString("user", ""));
            password.setText(loginPreferences.getString("pass", ""));
            checkBox.setChecked(true);
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                user = username.getText().toString();
                pass = password.getText().toString();

                if (checkBox.isChecked()) {
                    loginPrefsEditor.putBoolean("loginCheck", true);
                    loginPrefsEditor.putString("user", user);
                    loginPrefsEditor.putString("pass", pass);
                    loginPrefsEditor.commit();
                } else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();
                }

                LoginEvent loginEvent = eventDatabase.daoEvent().control(username.getText().toString(), password.getText().toString());

                if (loginEvent != null) {

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);

                } else {

                    Toast.makeText(getActivity(), "invalid username or password", Toast.LENGTH_LONG).show();
                }

            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager manager= getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.linearLogin , new RegisterFragment()).commit();


            }
        });

        return v;


    }


}

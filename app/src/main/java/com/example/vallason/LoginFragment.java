package com.example.vallason;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    EventDatabase eventDatabase;
    public static String username;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_login, container, false);
        Button login = v.findViewById(R.id.logButton);
        Button register = v.findViewById(R.id.registerButton);

        CheckBox checkBox =(CheckBox) v.findViewById(R.id.rememberCheck);
        final EditText username = (EditText) v.findViewById(R.id.userEdit);
        final EditText password = (EditText) v.findViewById(R.id.passEdit);

        eventDatabase=EventDatabase.getDatabase(getContext());


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

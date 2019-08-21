package com.example.vallason;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    EventDatabase eventDatabase;
    private List<LoginEvent> loginEvents;
    Context context;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);

        Button cancel = v.findViewById(R.id.cancelButton);
        Button register = v.findViewById(R.id.registerButton);


        final EditText username =(EditText) v.findViewById(R.id.userEditText);
        final EditText email = (EditText)v.findViewById(R.id.emailEditText);
        final EditText password =(EditText)v.findViewById(R.id.passEditText);
        final EditText confirm =(EditText)v.findViewById(R.id.confirmEdit);





        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager manager= getFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.linearLogin , new LoginFragment()).commit();

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(username.getText().toString().isEmpty()){
                    Toast.makeText(context, "event bos olamaz", Toast.LENGTH_SHORT).show();
                    return;

                }
                else if(email.getText().toString().isEmpty()){
                    Toast.makeText(context, "event bos olamaz", Toast.LENGTH_SHORT).show();
                    return;

                }
                else if(password.getText().toString().isEmpty()){
                    Toast.makeText(context, "type secmeye ihtiyacın var", Toast.LENGTH_SHORT).show();

                }
                else if(confirm.getText().toString().isEmpty()){
                    Toast.makeText(context, "type secmeye ihtiyacın var", Toast.LENGTH_SHORT).show();

                }else {

                    LoginEvent loginEvent = new LoginEvent(username.getText().toString(), email.getText().toString(), password.getText().toString(), confirm.getText().toString()  );


                    EventDatabase.getDatabase(context).daoEvent().insertRegister(loginEvent);


                    FragmentManager manager= getFragmentManager();
                    FragmentTransaction fragmentTransaction = manager.beginTransaction();
                    fragmentTransaction.replace(R.id.linearLogin , new LoginFragment()).commit();


                }
            }
        });

        return v;
    }

}


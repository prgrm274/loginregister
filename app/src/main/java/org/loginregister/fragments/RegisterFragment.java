package org.loginregister.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.loginregister.androiddev.R;
import org.loginregister.activities.StartAct;
import org.loginregister.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    private TextView registerTv;
    private EditText nameEdit, usernameEdit, passwordEdit;

    public RegisterFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_activity, container, false);

        registerTv = view.findViewById(R.id.tv_register);
        nameEdit = view.findViewById(R.id.edit_name);
        usernameEdit = view.findViewById(R.id.edit_username);
        passwordEdit = view.findViewById(R.id.edit_password);

        registerTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistrationTv();
            }
        });

        return view;
    }


    public void performRegistrationTv() {
        String name = nameEdit.getText().toString();
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        Call<User> call = StartAct.apiInterface.performRegistration(
                name, username, password
        );
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                // see response status in register.php
                if (response.body() == null | response.body().getResponse() == null) {
                    Log.i(response.body().toString(), response.body().toString());
                    Log.i(response.body().getResponse(), response.body().getResponse());

                } else if (response.body().getResponse().equals("ok")) {
                    StartAct.prefConfig.displayToast("Registration success");
                } else if (response.body().getResponse().equals("exist")) {
                    StartAct.prefConfig.displayToast("User already exists");
                } else if (response.body().getResponse().equals("error")) {
                    StartAct.prefConfig.displayToast("Error");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "onFailure", Toast.LENGTH_SHORT).show();
                Log.i(call.toString(), t.getMessage());
            }
        });

        nameEdit.setText("");
        usernameEdit.setText("");
        passwordEdit.setText("");
    }
}

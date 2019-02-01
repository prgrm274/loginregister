package org.loginregister.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.loginregister.androiddev.R;
import org.loginregister.activities.NavigationAct;
import org.loginregister.activities.StartAct;
import org.loginregister.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {
    private TextView loginOkTv, notRegisteredHere;
    private EditText usernameEdit, passwordEdit;

    public interface OnLoginFormActivityListener {
        public void performRegister();
        public void performLogin(String name);
    }
    OnLoginFormActivityListener listener;


    public LoginFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_activity, container, false);

        loginOkTv = view.findViewById(R.id.tv_login);
        notRegisteredHere = view.findViewById(R.id.tv_not_registered_here);
        usernameEdit = view.findViewById(R.id.edit_username);
        passwordEdit = view.findViewById(R.id.edit_password);

        loginOkTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        notRegisteredHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.performRegister();
            }
        });
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        listener = (OnLoginFormActivityListener) activity;
    }


    private void performLogin() {
        String username = usernameEdit.getText().toString();
        String password = passwordEdit.getText().toString();

        Call<User> call = StartAct.apiInterface.performUserLogin(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getResponse().equals("ok")) {
                    StartAct.prefConfig.writeLoginStatus(true);
                    listener.performLogin(response.body().getName());
                    Intent intent = new Intent(getActivity(), NavigationAct.class);
                    startActivity(intent);
                } else if (response.body().getResponse().equals("failed")){
                    StartAct.prefConfig.displayToast("Login failed");
                }
            }

            @Override
            public void onFailure(retrofit2.Call call, Throwable t) {

            }
        });

        usernameEdit.setText("");
        passwordEdit.setText("");
    }
}

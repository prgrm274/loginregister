package org.loginregister.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.loginregister.APIClient;
import org.loginregister.APIInterface;
import org.loginregister.PrefConfig;
import org.loginregister.androiddev.R;
import org.loginregister.fragments.LoginFragment;
import org.loginregister.fragments.RegisterFragment;

public class StartAct extends AppCompatActivity implements LoginFragment.OnLoginFormActivityListener {
    public static PrefConfig prefConfig;
    public static APIInterface apiInterface;

    Button continuefb, continuevk;
    TextView loginTv, createAccountTv;
    TextView tvAwareness;
    ImageView imageViewAwareness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);


        prefConfig = new PrefConfig(this);

        apiInterface = APIClient.getAPIClient().create(APIInterface.class);


        continuefb = findViewById(R.id.button_fb);
        continuevk = findViewById(R.id.button_vk);
        loginTv = findViewById(R.id.tv_login_start_act);
        createAccountTv = findViewById(R.id.tv_create_account);
        tvAwareness = findViewById(R.id.tv_awareness);
        imageViewAwareness = findViewById(R.id.image_view_awareness);


        if (findViewById(R.id.fragment_container_startact) != null) {
            if (savedInstanceState != null) {
                return;
            }

            if (prefConfig.readLoginStatus()) {

                Intent intent = new Intent(StartAct.this, NavigationAct.class);
                startActivity(intent);
                Toast.makeText(this, "Login succesful", Toast.LENGTH_SHORT).show();

            } else {// if user not login

                Toast.makeText(this, prefConfig.toString() + " login failed", Toast.LENGTH_SHORT).show();

                tvAwareness.setTextColor(Color.argb(50, 255, 255, 255));
                tvAwareness.setText("GET YOUR BRAIN");
                imageViewAwareness.setImageAlpha(50);

                continuefb.setVisibility(View.GONE);
                continuevk.setVisibility(View.GONE);
                loginTv.setVisibility(View.GONE);
                createAccountTv.setVisibility(View.GONE);


                getSupportFragmentManager().beginTransaction().add(
                        R.id.fragment_container_startact,
                        new LoginFragment()
                ).commit();

            }
        }


        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(
                        R.id.fragment_container_startact,
                        new LoginFragment()
                ).commit();
            }
        });

        createAccountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().add(
                        R.id.fragment_container_startact,
                        new RegisterFragment()
                ).commit();
            }
        });

    }


    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(
                R.id.fragment_container_startact,
                new RegisterFragment()
        ).addToBackStack(null).commit();
    }

    @Override
    public void performLogin(String name) {
        prefConfig.writeName(name);
        Intent intent = new Intent(StartAct.this, NavigationAct.class);
        startActivity(intent);
    }
}
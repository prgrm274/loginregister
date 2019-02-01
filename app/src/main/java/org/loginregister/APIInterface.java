package org.loginregister;

import org.loginregister.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("register.php")
    Call<User> performRegistration(
            @Query("name") String name,
            @Query("user_name") String username,
            @Query("user_password") String userpassword
    );

    @GET("login.php")
    Call<User> performUserLogin(
            @Query("user_name") String username,
            @Query("user_password") String userpassword
    );
}

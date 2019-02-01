package org.loginregister;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String BASE_URL = "http://10.0.2.2/loginregister/";

    public static Retrofit retrofit = null;

    public static Retrofit getAPIClient() {
        if (retrofit == null) {
            final OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            return onIntercept(chain);
                        }
                    })
                    .retryOnConnectionFailure(true)
                    .build();

            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }

    protected static Response onIntercept(Interceptor.Chain chain) throws IOException {
        try {
            Response response = chain.proceed(chain.request());
            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), response.message())).build();
        }
        catch (SocketTimeoutException exception) {
            exception.printStackTrace();
            if(listener != null)
                listener.onConnectionTimeout();
        }

        return chain.proceed(chain.request());
    }
    private static OnConnectionTimeoutListener listener;
    public interface OnConnectionTimeoutListener {
        void onConnectionTimeout();
    }
}

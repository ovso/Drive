package io.github.ovso.drive.framework.network;

import io.github.ovso.drive.Security;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jaeho on 2017. 11. 8
 */

public class NetworkHelper {
  public final static int DELAY = 0;

  private String baseUrl;

  public NetworkHelper(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  protected NetworkApi getNetworkApi() {
    final Retrofit retrofit = createRetrofit();
    return retrofit.create(NetworkApi.class);
  }

  private Retrofit createRetrofit() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(createClient())
        .build();
    return retrofit;
  }

  private OkHttpClient createClient() {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.addInterceptor(chain -> {
      Request original = chain.request();
      Request.Builder requestBuilder = original.newBuilder()
          .header("Content-Type", "application/json;charset=UTF-8")
          .addHeader("Authorization", Security.KAKAO_API_KEY.getValue());

      Request request = requestBuilder.build();
      return chain.proceed(request);
    });
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

    httpClient.addInterceptor(interceptor);
    OkHttpClient client = httpClient.build();
    return client;
  }
}

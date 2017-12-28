package io.github.ovso.drive.framework.network;

import io.github.ovso.drive.f_phone.model.DResult;
import io.reactivex.Single;
import java.util.HashMap;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by jaeho on 2017. 12. 28
 */

public interface NetworkApi {

  //public final static String BASE_URL = "https://openapi.naver.com";
  public final static String BASE_URL = "https://dapi.kakao.com";

  @GET("/v2/local/search/keyword.json") Single<DResult> getResult(
      @QueryMap HashMap<String, String> queryMap);

  //@GET("/v1/search/news") Single<NewsResult> getNews(@Query("query") String query,
  //    @Query("display") int display, @Query("start") int start, @Query("sort") String sort);
}
package io.github.ovso.drive.framework.network;

import io.github.ovso.drive.f_phone.model.DResult;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jaeho on 2017. 12. 28
 */

public interface NetworkApi {

  //public final static String BASE_URL = "https://openapi.naver.com";
  public final static String BASE_URL = "https://dapi.kakao.com";

  @GET("/v2/local/search/keyword.json") Single<DResult> getResult(
      @Query("query") String query, @Query("page") int page);

}
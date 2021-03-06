package io.github.ovso.drive.framework.listener;

/**
 * Created by jaeho on 2017. 10. 18..
 */

public interface OnNetworkResultListener<T> {
  void onPre();
  void onResult(T result);
  void onPost();
  void onError();
}
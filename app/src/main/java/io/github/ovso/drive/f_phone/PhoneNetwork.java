package io.github.ovso.drive.f_phone;

import io.github.ovso.drive.f_phone.model.DResult;
import io.github.ovso.drive.framework.network.NetworkHelper;
import io.reactivex.Single;

/**
 * Created by jaeho on 2017. 12. 28
 */

public class PhoneNetwork extends NetworkHelper {
  public PhoneNetwork(String baseUrl) {
    super(baseUrl);
  }

  public Single<DResult> getResult(String query, int page) {
    return getNetworkApi().getResult(query, page);
  }
}

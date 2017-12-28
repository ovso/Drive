package io.github.ovso.drive.f_phone;

import io.github.ovso.drive.R;
import io.github.ovso.drive.app.MyApplication;
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

  public Single<DResult> getResult(int page) {
    String query = MyApplication.getInstance().getString(R.string.query);
    return getNetworkApi().getResult(query, page);
  }
}

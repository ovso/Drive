package io.github.ovso.drive.f_phone;

import android.support.annotation.StringRes;
import io.github.ovso.drive.f_phone.model.Documents;

/**
 * Created by jaeho on 2017. 11. 27
 */

public interface PhonePresenter {

  void onActivityCreate();

  void onDetach();

  void onItemClick(Documents item);

  interface View {

    void setRecyclerView();

    void showMessage(@StringRes int resId);

    void refresh();

    void showMessage(String msg);

    void showLoading();

    void hideLoading();
  }
}

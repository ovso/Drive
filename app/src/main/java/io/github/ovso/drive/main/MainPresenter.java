package io.github.ovso.drive.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import de.psdev.licensesdialog.model.Notices;

/**
 * Created by jaeho on 2017. 10. 16
 */

public interface MainPresenter {

  void onCreate(Bundle savedInstanceState);

  boolean onNavItemSelected(@IdRes int itemId);

  void onBackPressed(boolean isDrawerOpen);

  boolean onBottomNavItemSelected(@IdRes int itemId, boolean isChecked);

  interface View {

    void setListener();

    void closeDrawer();

    void finish();

    void showPhoneFragment();

    void showLicensesDialog(Notices notices);

    void showMessage(@StringRes int resId);

    void showMessage(String msg);

    void showHelpAlert(@StringRes int resId);

    void showAd();

    void showHelpAlert(String msg);

    void showRecentFragment();

    void changeTheme();

    void showOpensourceLicenseDialog(Notices notices);

    void showHelpDialog();
  }
}

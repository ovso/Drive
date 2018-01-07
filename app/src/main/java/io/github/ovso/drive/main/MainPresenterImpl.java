package io.github.ovso.drive.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import io.github.ovso.drive.R;
import io.github.ovso.drive.framework.Constants;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jaeho on 2017. 10. 16
 */

public class MainPresenterImpl implements MainPresenter {

  private MainPresenter.View view;
  private CompositeDisposable compositeDisposable;

  MainPresenterImpl(MainPresenter.View view) {
    this.view = view;
    this.compositeDisposable = new CompositeDisposable();
    view.changeTheme();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    view.showPhoneFragment();
    view.setListener();
    view.showAd();
  }

  @Override public boolean onNavItemSelected(int itemId) {
    switch (itemId) {
      case R.id.nav_opensource:
        view.showOpensourceLicenseDialog(Constants.getNotices());
        break;
      case R.id.nav_help:
        view.showHelpDialog();
        break;
    }
    view.closeDrawer();
    return true;
  }

  @Override public boolean onBottomNavItemSelected(@IdRes int itemId, boolean isChecked) {
    if (!isChecked) {
      switch (itemId) {
        case R.id.bottom_nav_phone:
          view.showPhoneFragment();
          break;
        case R.id.bottom_nav_recent:
          view.showRecentFragment();
          break;
      }
    }
    return true;
  }

  @Override public void onBackPressed(boolean isDrawerOpen) {
    if (isDrawerOpen) {
      view.closeDrawer();
    } else {
      compositeDisposable.clear();
      view.finish();
    }
  }
}

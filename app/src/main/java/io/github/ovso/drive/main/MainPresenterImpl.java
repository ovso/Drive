package io.github.ovso.drive.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
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
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    view.setListener();
    view.showPhoneFragment();
    //view.showAd();
  }

  @Override public boolean onNavItemSelected(int itemId) {
    return true;
  }

  @Override public boolean onBottomNavItemSelected(@IdRes int itemId, boolean isChecked) {
    if (!isChecked) {
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

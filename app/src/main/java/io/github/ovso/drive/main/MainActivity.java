package io.github.ovso.drive.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.fsn.cauly.CaulyAdView;
import de.psdev.licensesdialog.LicensesDialog;
import de.psdev.licensesdialog.model.Notices;
import io.github.ovso.drive.R;
import io.github.ovso.drive.f_phone.PhoneFragment;
import io.github.ovso.drive.f_recent.RecentFragment;
import io.github.ovso.drive.framework.ActivityUtils;
import io.github.ovso.drive.framework.SystemUtility;
import io.github.ovso.drive.framework.customview.BaseActivity;
import io.github.ovso.drive.framework.customview.BottomNavigationViewBehavior;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainPresenter.View {

  @Inject CaulyAdView caulyAdView;
  @Inject MainPresenter presenter;
  @BindView(R.id.fragment_container) FrameLayout fragmentContainer;
  @BindView(R.id.bottom_navigation_view) BottomNavigationView bottomNavigationView;
  @BindView(R.id.navigation_view) NavigationView navigationView;
  @BindView(R.id.ad_container) ViewGroup adContainer;

  //@Inject PhoneFragment phoneFragment;
  @Inject RecentFragment recentFragment;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter.onCreate(savedInstanceState);
  }

  @Override public void changeTheme() {
    setTheme(R.style.AppTheme_NoActionBar);
  }

  @Override public void showOpensourceLicenseDialog(Notices notices) {
    new LicensesDialog.Builder(this).setNotices(notices).setIncludeOwnLicense(true).build().show();
  }

  @Override public void showHelpDialog() {
    new AlertDialog.Builder(this).setTitle(R.string.help)
        .setMessage(R.string.help_message)
        .setPositiveButton(android.R.string.ok, null)
        .show();
  }

  @Override public void setListener() {
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    navigationView.setNavigationItemSelectedListener(
        item -> presenter.onNavItemSelected(item.getItemId()));
    bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
      boolean isChecked = bottomNavigationView.getMenu().findItem(item.getItemId()).isChecked();
      return presenter.onBottomNavItemSelected(item.getItemId(), isChecked);
    });
    CoordinatorLayout.LayoutParams layoutParams =
        (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
    layoutParams.setBehavior(new BottomNavigationViewBehavior());

    TextView versionTextView = navigationView.getHeaderView(0).findViewById(R.id.version_textview);
    versionTextView.setText(SystemUtility.getVersionName(getApplicationContext()));
  }

  @Override public void closeDrawer() {
    drawer.closeDrawer(GravityCompat.START);
  }

  @Override public void showPhoneFragment() {
    ActivityUtils.replaceFragment(getSupportFragmentManager(), PhoneFragment.newInstance(),
        R.id.fragment_container);
  }

  @Override public void showRecentFragment() {
    ActivityUtils.replaceFragment(getSupportFragmentManager(), RecentFragment.newInstance(),
        R.id.fragment_container);
  }

  @Override public void showLicensesDialog(Notices notices) {
    new LicensesDialog.Builder(this).setNotices(notices).setIncludeOwnLicense(true).build().show();
  }

  @Override protected int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override public void onBackPressed() {
    presenter.onBackPressed(drawer.isDrawerOpen(GravityCompat.START));
  }

  @Override public void showMessage(int resId) {
    Snackbar.make(drawer, resId, Snackbar.LENGTH_SHORT).show();
  }

  @Override public void showMessage(@NonNull String msg) {
    Snackbar.make(drawer, msg, Snackbar.LENGTH_SHORT).show();
  }

  @Override public void showHelpAlert(int resId) {
    new AlertDialog.Builder(this).setMessage(resId)
        .setPositiveButton(android.R.string.ok, null)
        .show();
  }

  @Override public void showHelpAlert(String msg) {
    new AlertDialog.Builder(this).setMessage(msg)
        .setPositiveButton(android.R.string.ok, null)
        .show();
  }

  @Override public void showAd() {
    adContainer.addView(caulyAdView);
  }

  @Override protected void onDestroy() {
    caulyAdView.destroy();
    super.onDestroy();
  }
}
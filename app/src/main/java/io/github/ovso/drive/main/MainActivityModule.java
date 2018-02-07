package io.github.ovso.drive.main;

import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyAdInfoBuilder;
import com.fsn.cauly.CaulyAdView;
import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;
import io.github.ovso.drive.Security;
import io.github.ovso.drive.di.FragmentScoped;
import io.github.ovso.drive.f_phone.PhoneFragment;
import io.github.ovso.drive.f_phone.di.PhoneFragmentModule;
import io.github.ovso.drive.f_recent.RecentFragment;
import io.github.ovso.drive.main.listener.OnSimpleAdViewListener;

/**
 * Created by jaeho on 2017. 10. 16
 */

@Module public abstract class MainActivityModule {
  @FragmentScoped @ContributesAndroidInjector(modules = PhoneFragmentModule.class)
  abstract PhoneFragment providePhoneFragmentFactory();

  @FragmentScoped @ContributesAndroidInjector
  abstract RecentFragment provideRecentFragmentFactory();

  @Provides static MainPresenter provideMainPresenter(MainActivity activity) {
    return new MainPresenterImpl(activity);
  }

  @Provides static CaulyAdView provideCaulyAdView(MainActivity activity) {
    CaulyAdView view;
    CaulyAdInfo info = new CaulyAdInfoBuilder(Security.CAULY_APP_CODE.getValue()).effect(
        CaulyAdInfo.Effect.Circle.toString()).build();
    view = new CaulyAdView(activity.getApplicationContext());
    view.setAdInfo(info);
    view.setAdViewListener(new OnSimpleAdViewListener() {
      @Override public void onFailedToReceiveAd(CaulyAdView caulyAdView, int i, String s) {
        caulyAdView.reload();
      }
    });
    return view;
  }
}
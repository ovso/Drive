package io.github.ovso.drive.main;

import android.support.v4.app.FragmentManager;
import com.fsn.cauly.CaulyAdInfo;
import com.fsn.cauly.CaulyAdInfoBuilder;
import com.fsn.cauly.CaulyAdView;
import dagger.Module;
import dagger.Provides;
import io.github.ovso.drive.Security;
import io.github.ovso.drive.main.listener.OnSimpleAdViewListener;

/**
 * Created by jaeho on 2017. 10. 16
 */

@Module public class MainActivityModule {

  @Provides MainPresenter provideMainPresenter(MainActivity activity) {
    return new MainPresenterImpl(activity);
  }

  @Provides FragmentManager provideFragmentManager(MainActivity mainActivity) {
    return mainActivity.getSupportFragmentManager();
  }

  @Provides CaulyAdView provideCaulyAdView(MainActivity activity) {
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
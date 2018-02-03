package io.github.ovso.drive.main;

import android.support.v4.app.FragmentManager;
import dagger.Module;
import dagger.Provides;

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
}
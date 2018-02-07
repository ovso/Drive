package io.github.ovso.drive.main.f_recent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by jaeho on 2018. 2. 7
 */

@Module public class RecentFragmentModule {

  @Provides RecentPresenter provideRecentPresenter(RecentFragment fragment) {
    return new RecentPresenterImpl(fragment);
  }
}

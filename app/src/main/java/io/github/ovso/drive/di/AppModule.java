package io.github.ovso.drive.di;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jaeho on 2017. 10. 16
 */
@Module public class AppModule {
  @Provides Context providesContext(Application application) {
    return application;
  }
}

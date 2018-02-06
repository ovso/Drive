package io.github.ovso.drive.di;

import android.app.Application;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.ovso.drive.app.MyApplication;
import javax.inject.Singleton;

/**
 * Created by jaeho on 2017. 10. 16
 */
@Singleton
@Component(modules = {
    AndroidSupportInjectionModule.class, AppModule.class, ActivityBuilder.class
}) public interface AppComponent extends AndroidInjector<MyApplication> {
  @Component.Builder interface Builder {

    @BindsInstance Builder application(Application application);

    AppComponent build();
  }
}
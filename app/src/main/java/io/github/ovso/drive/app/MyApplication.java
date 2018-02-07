package io.github.ovso.drive.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.squareup.leakcanary.LeakCanary;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.github.ovso.drive.BuildConfig;
import io.github.ovso.drive.di.DaggerAppComponent;
import lombok.Getter;
import timber.log.Timber;

/**
 * Created by jaeho on 2017. 12. 28
 */

public class MyApplication extends DaggerApplication {
  @Getter private static Application instance;
  public static boolean DEBUG = false;

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
    initLeakCanary();
    this.DEBUG = isDebuggable(this);
    initTimber();
  }

  private void initTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  @Override protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
  }

  private boolean isDebuggable(Context context) {
    boolean debuggable = false;

    PackageManager pm = context.getPackageManager();
    try {
      ApplicationInfo appinfo = pm.getApplicationInfo(context.getPackageName(), 0);
      debuggable = (0 != (appinfo.flags & ApplicationInfo.FLAG_DEBUGGABLE));
    } catch (PackageManager.NameNotFoundException e) {
      /* debuggable variable will remain false */
    }

    return debuggable;
  }

  private void initLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return;
    }
    LeakCanary.install(this);
    // Normal app init code...
  }
}

package io.github.ovso.drive.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.pixplicity.easyprefs.library.Prefs;
import com.squareup.leakcanary.LeakCanary;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.github.ovso.drive.BuildConfig;
import io.github.ovso.drive.di.DaggerAppComponent;
import javax.inject.Inject;
import lombok.Getter;
import timber.log.Timber;

/**
 * Created by jaeho on 2017. 12. 28
 */

public class MyApplication extends Application implements HasActivityInjector {
  @Getter private static Application instance;
  @Inject DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;
  public static boolean DEBUG = false;

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
    initLeakCanary();
    initTimber();
    initDagger();
    initEasyPrefs();
    this.DEBUG = isDebuggable(this);
  }

  private void initEasyPrefs() {
    new Prefs.Builder().setContext(this)
        .setMode(ContextWrapper.MODE_PRIVATE)
        .setPrefsName(getPackageName())
        .setUseDefaultSharedPreference(true)
        .build();
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

  private void initDagger() {
    DaggerAppComponent.builder().application(this).build().inject(this);
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

  private void initTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  @Override public AndroidInjector<Activity> activityInjector() {
    return activityDispatchingAndroidInjector;
  }
}

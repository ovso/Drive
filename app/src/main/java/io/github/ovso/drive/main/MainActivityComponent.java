package io.github.ovso.drive.main;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import io.github.ovso.drive.f_phone.di.PhoneFragmentProvider;

/**
 * Created by jaeho on 2017. 10. 16
 */

@Subcomponent(modules = {
    MainActivityModule.class, PhoneFragmentProvider.class, PhoneFragmentProvider.class,
    PhoneFragmentProvider.class
}) public interface MainActivityComponent extends AndroidInjector<MainActivity> {

  @Subcomponent.Builder abstract class Builder extends AndroidInjector.Builder<MainActivity> {
  }
}

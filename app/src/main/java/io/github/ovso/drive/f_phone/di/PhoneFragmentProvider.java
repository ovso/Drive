package io.github.ovso.drive.f_phone.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import io.github.ovso.drive.f_phone.PhoneFragment;

/**
 * Created by jaeho on 2017. 10. 20
 */

@Module public abstract class PhoneFragmentProvider {

  @ContributesAndroidInjector(modules = PhoneFragmentModule.class)
  abstract PhoneFragment providePhoneFragmentFactory();

}

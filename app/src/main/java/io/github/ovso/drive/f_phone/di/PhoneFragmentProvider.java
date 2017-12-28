package io.github.ovso.drive.f_phone.di;

import android.support.v4.app.Fragment;
import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import io.github.ovso.drive.f_phone.PhoneFragment;

/**
 * Created by jaeho on 2017. 10. 20
 */

@Module public abstract class PhoneFragmentProvider {
  @Binds @IntoMap @FragmentKey(PhoneFragment.class)
  abstract AndroidInjector.Factory<? extends Fragment> providePhoneFragmentFactory(
      PhoneFragmentComponent.Builder builder);
}

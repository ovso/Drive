package io.github.ovso.drive.f_phone.di;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import io.github.ovso.drive.f_phone.PhoneFragment;

/**
 * Created by jaeho on 2017. 10. 20
 */

@Subcomponent(modules = PhoneFragmentModule.class) public interface PhoneFragmentComponent
    extends AndroidInjector<PhoneFragment> {

  @Subcomponent.Builder abstract class Builder extends AndroidInjector.Builder<PhoneFragment> {
  }
}

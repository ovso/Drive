package io.github.ovso.drive.f_phone.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.drive.f_phone.PhoneFragment;
import io.github.ovso.drive.f_phone.PhonePresenter;
import io.github.ovso.drive.f_phone.PhonePresenterImpl;
import io.github.ovso.drive.f_phone.adapter.PhoneAdapter;
import io.github.ovso.drive.f_phone.adapter.PhoneAdapterView;

/**
 * Created by jaeho on 2017. 10. 20
 */

@Module public class PhoneFragmentModule {

  @Provides PhonePresenter provideSymptomPresenter(PhoneFragment fragment) {
    return new PhonePresenterImpl(fragment, fragment.getAdapter());
  }

  @Provides PhoneAdapter provideSymptomAdapter(PhoneFragment fragment) {
    return new PhoneAdapter().setOnRecyclerItemClickListener(fragment);
  }

  @Provides PhoneAdapterView provideBaseAdapterView(PhoneFragment fragment) {
    return fragment.getAdapter();
  }
}

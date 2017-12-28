package io.github.ovso.drive.f_phone.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.drive.f_phone.PhoneFragment;
import io.github.ovso.drive.f_phone.PhoneNetwork;
import io.github.ovso.drive.f_phone.PhonePresenter;
import io.github.ovso.drive.f_phone.PhonePresenterImpl;
import io.github.ovso.drive.f_phone.adapter.PhoneAdapter;
import io.github.ovso.drive.framework.adapter.BaseAdapterView;
import io.github.ovso.drive.framework.network.NetworkApi;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jaeho on 2017. 10. 20
 */

@Module public class PhoneFragmentModule {

  @Provides PhonePresenter providePhonePresenter(PhoneFragment fragment, PhoneNetwork network) {
    return new PhonePresenterImpl(fragment, fragment.getAdapter(), network, fragment.getCompositeDisposable());
  }

  @Provides PhoneAdapter providePhoneAdapter(PhoneFragment fragment) {
    return new PhoneAdapter().setOnRecyclerItemClickListener(fragment).setCompositeDisposable(fragment.getCompositeDisposable());
  }

  @Provides BaseAdapterView provideBaseAdapterView(PhoneFragment fragment) {
    return fragment.getAdapter();
  }

  @Provides PhoneNetwork provideNetwork(PhoneFragment fragment) {
    return new PhoneNetwork(NetworkApi.BASE_URL);
  }

  @Provides CompositeDisposable provideCompositeDisposable() {
    return new CompositeDisposable();
  }
}

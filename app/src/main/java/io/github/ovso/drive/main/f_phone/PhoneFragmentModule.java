package io.github.ovso.drive.main.f_phone;

import com.tbruyelle.rxpermissions2.RxPermissions;
import dagger.Module;
import dagger.Provides;
import io.github.ovso.drive.framework.network.NetworkApi;
import io.github.ovso.drive.main.f_phone.adapter.OnEndlessRecyclerScrollListener;
import io.github.ovso.drive.main.f_phone.adapter.PhoneAdapter;
import io.github.ovso.drive.main.f_phone.adapter.PhoneAdapterView;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Singleton;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

/**
 * Created by jaeho on 2017. 10. 20
 */

@Module public class PhoneFragmentModule {

  @Provides @Singleton PhonePresenter providePhonePresenter(PhoneFragment fragment,
      PhoneNetwork network, RxPermissions permissions, ReactiveLocationProvider locationProvider,
      CompositeDisposable compositeDisposable, PhoneAdapter adapter) {
    return new PhonePresenterImpl(fragment, adapter, network, compositeDisposable, permissions,
        locationProvider);
  }

  @Provides @Singleton PhoneAdapter providePhoneAdapter(PhoneFragment fragment,
      CompositeDisposable compositeDisposable) {
    return new PhoneAdapter().setOnRecyclerItemClickListener(fragment)
        .setCompositeDisposable(compositeDisposable);
  }

  @Provides PhoneAdapterView provideBaseAdapterView(PhoneAdapter adapter) {
    return adapter;
  }

  @Provides @Singleton PhoneNetwork provideNetwork() {
    return new PhoneNetwork(NetworkApi.BASE_URL);
  }

  @Provides @Singleton CompositeDisposable provideCompositeDisposable() {
    return new CompositeDisposable();
  }

  @Provides @Singleton RxPermissions provideRxPermissions(PhoneFragment fragment) {
    return new RxPermissions(fragment.getActivity());
  }

  @Provides @Singleton ReactiveLocationProvider provideReactiveLocationProvider(
      PhoneFragment fragment) {
    return new ReactiveLocationProvider(fragment.getContext());
  }

  @Provides @Singleton OnEndlessRecyclerScrollListener provideEndlessRecyclerScrollListener(
      PhoneFragment fragment) {
    OnEndlessRecyclerScrollListener listener = new OnEndlessRecyclerScrollListener();
    listener.setOnLoadMoreListener(fragment);
    return listener;
  }
}

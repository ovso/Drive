package io.github.ovso.drive.f_phone.di;

import com.tbruyelle.rxpermissions2.RxPermissions;
import dagger.Module;
import dagger.Provides;
import io.github.ovso.drive.f_phone.PhoneFragment;
import io.github.ovso.drive.f_phone.PhoneNetwork;
import io.github.ovso.drive.f_phone.PhonePresenter;
import io.github.ovso.drive.f_phone.PhonePresenterImpl;
import io.github.ovso.drive.f_phone.adapter.OnEndlessRecyclerScrollListener;
import io.github.ovso.drive.f_phone.adapter.PhoneAdapter;
import io.github.ovso.drive.f_phone.adapter.PhoneAdapterView;
import io.github.ovso.drive.framework.network.NetworkApi;
import io.reactivex.disposables.CompositeDisposable;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

/**
 * Created by jaeho on 2017. 10. 20
 */

@Module public class PhoneFragmentModule {

  @Provides PhonePresenter providePhonePresenter(PhoneFragment fragment, PhoneNetwork network,
      RxPermissions permissions, ReactiveLocationProvider locationProvider) {
    return new PhonePresenterImpl(fragment, fragment.getAdapter(), network,
        fragment.getCompositeDisposable(), permissions, locationProvider);
  }

  @Provides PhoneAdapter providePhoneAdapter(PhoneFragment fragment) {
    return new PhoneAdapter().setOnRecyclerItemClickListener(fragment)
        .setCompositeDisposable(fragment.getCompositeDisposable());
  }

  @Provides PhoneAdapterView provideBaseAdapterView(PhoneFragment fragment) {
    return fragment.getAdapter();
  }

  @Provides PhoneNetwork provideNetwork(PhoneFragment fragment) {
    return new PhoneNetwork(NetworkApi.BASE_URL);
  }

  @Provides CompositeDisposable provideCompositeDisposable() {
    return new CompositeDisposable();
  }

  @Provides RxPermissions provideRxPermissions(PhoneFragment fragment) {
    return new RxPermissions(fragment.getActivity());
  }

  @Provides ReactiveLocationProvider provideReactiveLocationProvider(PhoneFragment fragment) {
    return new ReactiveLocationProvider(fragment.getContext());
  }

  @Provides OnEndlessRecyclerScrollListener provideEndlessRecyclerScrollListener(
      PhoneFragment fragment) {
    OnEndlessRecyclerScrollListener listener = new OnEndlessRecyclerScrollListener();
    listener.setOnLoadMoreListener(fragment);
    return listener;
  }
}

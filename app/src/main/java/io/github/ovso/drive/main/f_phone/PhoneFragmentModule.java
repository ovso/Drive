package io.github.ovso.drive.main.f_phone;

import com.tbruyelle.rxpermissions2.RxPermissions;
import dagger.Module;
import dagger.Provides;
import hugo.weaving.DebugLog;
import io.github.ovso.drive.framework.network.NetworkApi;
import io.github.ovso.drive.main.f_phone.adapter.OnEndlessRecyclerScrollListener;
import io.github.ovso.drive.main.f_phone.adapter.PhoneAdapter;
import io.github.ovso.drive.main.f_phone.adapter.PhoneAdapterView;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Singleton;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;
import timber.log.Timber;

/**
 * Created by jaeho on 2017. 10. 20
 */

@Module public class PhoneFragmentModule {

  @Provides PhonePresenter providePhonePresenter(PhoneFragment fragment, PhoneNetwork network,
      RxPermissions permissions, ReactiveLocationProvider locationProvider,
      CompositeDisposable compositeDisposable, PhoneAdapter adapter) {
    return new PhonePresenterImpl(fragment, adapter, network, compositeDisposable, permissions,
        locationProvider);
  }

  @DebugLog @Singleton @Provides PhoneAdapter providePhoneAdapter(PhoneFragment fragment,
      CompositeDisposable compositeDisposable) {
    return new PhoneAdapter().setOnRecyclerItemClickListener(fragment)
        .setCompositeDisposable(compositeDisposable);
  }

  @DebugLog @Provides PhoneAdapterView provideBaseAdapterView(PhoneAdapter adapter) {

    return adapter;
  }

  @Singleton @Provides PhoneNetwork provideNetwork() {
    return new PhoneNetwork(NetworkApi.BASE_URL);
  }

  @DebugLog @Singleton @Provides CompositeDisposable provideCompositeDisposable() {
    final CompositeDisposable compositeDisposable = new CompositeDisposable();
    Timber.d("compositeDisposable = " + compositeDisposable);
    return compositeDisposable;
  }

  @Singleton @Provides RxPermissions provideRxPermissions(PhoneFragment fragment) {
    return new RxPermissions(fragment.getActivity());
  }

  @Singleton @Provides ReactiveLocationProvider provideReactiveLocationProvider(
      PhoneFragment fragment) {
    return new ReactiveLocationProvider(fragment.getContext());
  }

  @Singleton @Provides OnEndlessRecyclerScrollListener provideEndlessRecyclerScrollListener(
      PhoneFragment fragment) {
    OnEndlessRecyclerScrollListener listener = new OnEndlessRecyclerScrollListener();
    listener.setOnLoadMoreListener(fragment);
    return listener;
  }
}

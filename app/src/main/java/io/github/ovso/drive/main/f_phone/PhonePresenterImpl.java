package io.github.ovso.drive.main.f_phone;

import android.Manifest;
import android.location.Address;
import android.text.TextUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import hugo.weaving.DebugLog;
import io.github.ovso.drive.R;
import io.github.ovso.drive.app.MyApplication;
import io.github.ovso.drive.main.f_phone.model.Documents;
import io.github.ovso.drive.framework.adapter.BaseAdapterDataModel;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;
import timber.log.Timber;

/**
 * Created by jaeho on 2017. 11. 27
 */

public class PhonePresenterImpl implements PhonePresenter {
  private PhonePresenter.View view;
  private BaseAdapterDataModel<Documents> adapterDataModel;
  private PhoneNetwork network;
  private CompositeDisposable compositeDisposable;
  private RxPermissions permissions;
  private ReactiveLocationProvider locationProvider;

  public PhonePresenterImpl(PhonePresenter.View view, BaseAdapterDataModel adapterDataModel,
      PhoneNetwork network, CompositeDisposable compositeDisposable, RxPermissions permissions,
      ReactiveLocationProvider locationProvider) {
    this.view = view;
    this.adapterDataModel = adapterDataModel;
    this.network = network;
    this.compositeDisposable = compositeDisposable;
    this.permissions = permissions;
    this.locationProvider = locationProvider;
  }

  private int page = 1;

  @DebugLog @Override public void onActivityCreate() {
    view.showLoading();
    view.setRecyclerView();
    reqPermission();
  }

  private void reqPermission() {
    permissions.request(Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.CALL_PHONE).subscribe(granted -> {
      if (granted) { // Always true pre-M
        lastKnownLocation();
      } else {
        // Oups permission denied
        view.hideLoading();
        view.showPermissionAlert();
      }
    });
  }

  private String query;

  private void reverseGeocodeObservable(final double lat, final double lng) {
    compositeDisposable.add(locationProvider.getReverseGeocodeObservable(lat, lng, 1)
        .subscribeOn(Schedulers.io())               // use I/O thread to query for addresses
        .observeOn(
            AndroidSchedulers.mainThread())  // return result in main android thread to manipulate UI
        .subscribe(addresses -> {
          if (addresses.size() > 0) {
            Address address = addresses.get(0);
            query = getQuery(address.getLocality(), address.getThoroughfare());
            req(query);
          } else {
            view.hideLoading();
          }
        }, throwable -> view.hideLoading()));
  }

  @DebugLog private void req(String query) {
    compositeDisposable.add(
        network.getResult(query, page)
            .subscribeOn(Schedulers.io())
            .flattenAsObservable(dResult -> {
              is_end = dResult.getMeta().is_end();
              return dResult.getDocuments();
            })
            .flatMap(documents -> Observable.fromArray(documents))
            .filter(documents -> !TextUtils.isEmpty(documents.getPhone()))
            .toList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(items -> {
              adapterDataModel.addAll(items);
              view.refresh();
              view.hideLoading();
              view.setLoaded();
            }, throwable -> {
              view.hideLoading();
            }));
  }

  @DebugLog private String getQuery(String locality, String thoroughfare) {
    final String query = MyApplication.getInstance().getString(R.string.query);
    if (!TextUtils.isEmpty(locality) && !TextUtils.isEmpty(thoroughfare)) {
      return locality + " " + thoroughfare + " " + query;
    } else if (!TextUtils.isEmpty(locality)) {
      return locality + " " + query;
    } else if (!TextUtils.isEmpty(thoroughfare)) {
      return thoroughfare + " " + query;
    } else {
      return MyApplication.getInstance().getString(R.string.nationwide) + " " + query;
    }
  }

  private void lastKnownLocation() {
    compositeDisposable.add(locationProvider.getLastKnownLocation().subscribe(location -> {
      double lat = location.getLatitude();
      double lng = location.getLongitude();
      Timber.d("lat = " + lat + ", lng = " + lng);
      reverseGeocodeObservable(lat, lng);
    }, throwable -> {
      view.hideLoading();
      view.showMessage(R.string.error_server);
    }));
  }

  @Override public void onDetach() {
    compositeDisposable.clear();
  }

  @DebugLog @Override public void onItemClick(Documents item) {
    view.makeCall(item.getPhone());
  }

  private boolean is_end;

  @DebugLog @Override public void onLoadMore() {

    if (is_end) {
      return;
    }

    adapterDataModel.add(null);
    final int loadingPosition = adapterDataModel.getSize() - 1;
    view.notifyItemInserted(loadingPosition);
    page++;
    compositeDisposable.add(network.getResult(this.query, page)
        .subscribeOn(Schedulers.io())
        .flattenAsObservable(dResult -> {
          is_end = dResult.getMeta().is_end();
          return dResult.getDocuments();
        })
        .flatMap(documents -> Observable.just(documents))
        .filter(documents -> !TextUtils.isEmpty(documents.getPhone()))
        .toList()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe((List<Documents> items) -> {
          adapterDataModel.remove(loadingPosition);
          view.notifyItemRemoved(loadingPosition);

          int oldSize = loadingPosition;
          adapterDataModel.addAll(items);
          view.notifyItemRangeInserted(oldSize, adapterDataModel.getSize());
          view.setLoaded();
        }, throwable -> {
          view.showMessage(R.string.error_server);
          view.hideLoading();
        }));
  }
}
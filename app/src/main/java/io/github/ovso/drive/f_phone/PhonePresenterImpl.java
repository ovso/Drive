package io.github.ovso.drive.f_phone;

import android.Manifest;
import android.location.Address;
import android.text.TextUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import hugo.weaving.DebugLog;
import io.github.ovso.drive.R;
import io.github.ovso.drive.app.MyApplication;
import io.github.ovso.drive.f_phone.model.Documents;
import io.github.ovso.drive.framework.adapter.BaseAdapterDataModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;
import timber.log.Timber;

/**
 * Created by jaeho on 2017. 11. 27
 */

public class PhonePresenterImpl extends Exception implements PhonePresenter {
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

    permissions.request(Manifest.permission.ACCESS_NETWORK_STATE,
        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
        .subscribe(granted -> {
          if (granted) { // Always true pre-M
            lastKnownLocation();
          } else {
            // Oups permission denied
            view.hideLoading();
          }
          Timber.d("granted = " + granted);
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
            Timber.d("address = " + address);
            query = getQuery(address.getLocality(), address.getThoroughfare());
            query = "서울 대리운전";
            req(query);
          } else {
            view.hideLoading();
          }
        }, throwable -> view.hideLoading()));
  }

  @DebugLog private void req(String query) {
    compositeDisposable.add(network.getResult(query, page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(dResult -> {
          is_end = dResult.getMeta().is_end();
          adapterDataModel.addAll(dResult.getDocuments());
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
      //
      double[][] loc = new double[5][2];
      loc[0][0] = 36.3021057;   //옥천군 옥천읍
      loc[0][1] = 127.5681262;
      loc[1][0] = 37.6841556;   //남양주시 화도읍
      loc[1][1] = 127.303926;
      loc[2][0] = 37.487338;    //명달로 26길 44
      loc[2][1] = 127.0046589;
      loc[3][0] = 36.3317856;   //대전광역시 중구 은행동
      loc[3][1] = 127.41271923;
      loc[4][0] = 35.1801988;   //부산광역시 송정
      loc[4][1] = 129.189196;

      reverseGeocodeObservable(loc[0][0], loc[0][1]);
    }, throwable -> view.hideLoading()));
  }

  @Override public void onDetach() {
    compositeDisposable.clear();
  }

  @DebugLog @Override public void onItemClick(Documents item) {
  }
  private boolean is_end;
  @DebugLog @Override public void onLoadMore() {

    if(is_end) {
      Timber.d("is_end = " + is_end);
      return;
    }

    adapterDataModel.add(null);
    final int lastPosition = adapterDataModel.getSize() - 1;
    view.notifyItemInserted(lastPosition);
    page++;
    compositeDisposable.add(network.getResult(this.query, page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(dResult -> {
          adapterDataModel.remove(lastPosition);
          view.notifyItemRemoved(lastPosition);
          is_end = dResult.getMeta().is_end();
          int oldSize = adapterDataModel.getSize();
          adapterDataModel.addAll(dResult.getDocuments());
          view.notifyItemRangeInserted(oldSize, adapterDataModel.getSize()-1);
          view.setLoaded();
        }, throwable -> {

        }));
  }
}
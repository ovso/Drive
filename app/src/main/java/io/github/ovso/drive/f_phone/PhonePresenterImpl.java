package io.github.ovso.drive.f_phone;

import hugo.weaving.DebugLog;
import io.github.ovso.drive.f_phone.model.Documents;
import io.github.ovso.drive.framework.adapter.BaseAdapterDataModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by jaeho on 2017. 11. 27
 */

public class PhonePresenterImpl extends Exception implements PhonePresenter {
  private PhonePresenter.View view;
  private CompositeDisposable compositeDisposable;
  private BaseAdapterDataModel<Documents> adapterDataModel;
  private PhoneNetwork network;
  private Disposable disposable;

  public PhonePresenterImpl(PhonePresenter.View view, BaseAdapterDataModel adapterDataModel,
      PhoneNetwork network, CompositeDisposable compositeDisposable) {
    this.view = view;
    this.adapterDataModel = adapterDataModel;
    this.network = network;
    this.compositeDisposable = compositeDisposable;
  }

  private int page = 1;

  @DebugLog @Override public void onActivityCreate() {
    view.setRecyclerView();
    view.setPagination();
    disposable = network.getResult(page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(dResult -> {
          adapterDataModel.addAll(dResult.getDocuments());
          view.refresh();
        }, throwable -> {

        });
  }

  @Override public void onDetach() {
    disposable.dispose();
  }

  @DebugLog @Override public void onItemClick(Documents item) {
  }

  @Override public void onLoadMore() {
    if (adapterDataModel.getSize() < 1) {
      return;
    }
    page++;
    Timber.d("page count = " + page);
    disposable = network.getResult(page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(dResult -> {
          int oldLastPosition = adapterDataModel.getSize() - 1;
          adapterDataModel.addAll(dResult.getDocuments());
          view.refreshToEnd(oldLastPosition);
          //view.refresh();
        }, throwable -> {

        });
  }
}
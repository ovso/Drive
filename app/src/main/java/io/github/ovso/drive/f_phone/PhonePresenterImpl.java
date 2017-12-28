package io.github.ovso.drive.f_phone;

import hugo.weaving.DebugLog;
import io.github.ovso.drive.f_phone.model.DResult;
import io.github.ovso.drive.f_phone.model.Documents;
import io.github.ovso.drive.framework.adapter.BaseAdapterDataModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jaeho on 2017. 11. 27
 */

public class PhonePresenterImpl extends Exception implements PhonePresenter {
  private PhonePresenter.View view;
  private CompositeDisposable compositeDisposable;
  private BaseAdapterDataModel<Documents> adapterDataModel;
  private PhoneNetwork network;

  public PhonePresenterImpl(PhonePresenter.View view, BaseAdapterDataModel adapterDataModel,
      PhoneNetwork network, CompositeDisposable compositeDisposable) {
    this.view = view;
    this.adapterDataModel = adapterDataModel;
    this.network = network;
    this.compositeDisposable = compositeDisposable;
  }
  private int page = 2;
  @Override public void onActivityCreate() {
    view.setRecyclerView();
    view.showLoading();
    compositeDisposable.add(network.getResult(page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<DResult>() {
          @DebugLog @Override public void accept(DResult dResult) throws Exception {
            adapterDataModel.addAll(dResult.getDocuments());
            view.refresh();
          }
        }, new Consumer<Throwable>() {
          @DebugLog @Override public void accept(Throwable throwable) throws Exception {

          }
        }));
  }

  @Override public void onDetach() {
    compositeDisposable.clear();
  }

  @DebugLog @Override public void onItemClick(Documents item) {
  }
}
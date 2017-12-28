package io.github.ovso.drive.f_phone;

import hugo.weaving.DebugLog;
import io.github.ovso.drive.f_phone.model.Phone;
import io.github.ovso.drive.framework.SelectableItem;
import io.github.ovso.drive.framework.adapter.BaseAdapterDataModel;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by jaeho on 2017. 11. 27
 */

public class PhonePresenterImpl extends Exception implements PhonePresenter {
  private PhonePresenter.View view;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private BaseAdapterDataModel<SelectableItem<Phone>> adapterDataModel;

  public PhonePresenterImpl(PhonePresenter.View view, BaseAdapterDataModel adapterDataModel) {
    this.view = view;
    this.adapterDataModel = adapterDataModel;
  }

  @Override public void onActivityCreate() {
    view.setRecyclerView();
    view.showLoading();
    /*
    compositeDisposable.add(RxFirebaseDatabase.data(databaseReference)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(dataSnapshot -> {
          List<SelectableItem<Phone>> items = new ArrayList<>();
          for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
            Phone phone = snapshot.getValue(Phone.class);
          }
          adapterDataModel.addAll(items);
          view.refresh();
          view.hideLoading();
        }, throwable -> {
          view.showMessage(R.string.error_server);
          view.hideLoading();
        }));
    */
  }

  @Override public void onDetach() {
    compositeDisposable.clear();
  }

  @DebugLog @Override public void onItemClick(SelectableItem<Phone> selectableItem) {
  }
}
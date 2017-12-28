package io.github.ovso.drive.f_phone;

import com.androidhuman.rxfirebase2.database.RxFirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import hugo.weaving.DebugLog;
import io.github.ovso.drive.R;
import io.github.ovso.drive.f_phone.model.Phone;
import io.github.ovso.drive.framework.SelectableItem;
import io.github.ovso.drive.framework.adapter.BaseAdapterDataModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeho on 2017. 11. 27
 */

public class PhonePresenterImpl extends Exception implements PhonePresenter {
  private PhonePresenter.View view;
  private DatabaseReference databaseReference;
  private CompositeDisposable compositeDisposable = new CompositeDisposable();
  private BaseAdapterDataModel<SelectableItem<Phone>> adapterDataModel;

  public PhonePresenterImpl(PhonePresenter.View view, BaseAdapterDataModel adapterDataModel) {
    this.view = view;
    this.adapterDataModel = adapterDataModel;
  }

  @Override public void onActivityCreate() {
    view.setRecyclerView();
    view.showLoading();
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
  }

  @Override public void onDetach() {
    compositeDisposable.clear();
  }

  @DebugLog @Override public void onItemClick(SelectableItem<Phone> selectableItem) {
  }
}
package io.github.ovso.drive.f_phone;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.BindView;
import com.paginate.Paginate;
import hugo.weaving.DebugLog;
import io.github.ovso.drive.R;
import io.github.ovso.drive.f_phone.adapter.PhoneAdapter;
import io.github.ovso.drive.f_phone.adapter.PhoneAdapterView;
import io.github.ovso.drive.f_phone.model.Documents;
import io.github.ovso.drive.framework.Constants;
import io.github.ovso.drive.framework.customview.BaseFragment;
import io.github.ovso.drive.framework.listener.OnRecyclerItemClickListener;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;
import lombok.Getter;

/**
 * Created by jaeho on 2017. 12. 28
 */

public class PhoneFragment extends BaseFragment
    implements PhonePresenter.View, OnRecyclerItemClickListener<Documents> {
  @BindView(R.id.recyclerview) RecyclerView recyclerView;
  @Inject @Getter CompositeDisposable compositeDisposable;
  @Inject @Getter PhoneAdapter adapter;
  @Inject @Getter PhoneAdapterView adapterView;
  @Inject PhonePresenter presenter;

  @Override protected int getLayoutResID() {
    return R.layout.fragment_phone;
  }

  @Override protected void onActivityCreate(Bundle savedInstanceState) {
    presenter.onActivityCreate();
  }

  @Override protected boolean isDagger() {
    return true;
  }

  public static PhoneFragment newInstance() {
    PhoneFragment f = new PhoneFragment();
    return f;
  }

  @Override public void setRecyclerView() {
    recyclerView.getItemAnimator().setChangeDuration(Constants.DURATION_RECYCLERVIEW_ANI);
    recyclerView.setItemAnimator(new SlideInDownAnimator());
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setAdapter(adapter);
  }

  @Override public void setPagination() {
    /*
    paginate = new PaginateBuilder().with(recyclerView).setOnLoadMoreListener(
        () -> presenter.onLoadMore()).setLoadingTriggerThreshold(5).build();
    */
    Paginate.Callbacks callbacks = new Paginate.Callbacks() {
      @Override
      public void onLoadMore() {
        // Load next page of data (e.g. network or database)
        loadingInProgress = true;
        hasLoadedAllItems = true;
        presenter.onLoadMore();
      }

      @Override
      public boolean isLoading() {
        // Indicate whether new page loading is in progress or not
        return loadingInProgress;
      }

      @Override
      public boolean hasLoadedAllItems() {
        // Indicate whether all data (pages) are loaded or not
        return hasLoadedAllItems;
      }
    };
    Paginate.with(recyclerView, callbacks)
        .setLoadingTriggerThreshold(2)
        .addLoadingListItem(true)
        //.setLoadingListItemCreator(new CustomLoadingListItemCreator())
        //.setLoadingListItemSpanSizeLookup(new CustomLoadingListItemSpanLookup())
        .build();
  }
  private boolean loadingInProgress;
  private boolean hasLoadedAllItems;
  @Override public void showMessage(int resId) {
    Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
  }

  @Override public void showMessage(String msg) {
    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
  }

  @Override public void showLoading() {
  }

  @Override public void hideLoading() {
  }

  @Override public void refresh() {
    adapterView.refresh();
    loadingInProgress = false;
    hasLoadedAllItems = false;
  }

  @Override public void refreshToEnd(int position) {
    adapterView.refreshToEnd(position);
    loadingInProgress = false;
    hasLoadedAllItems = false;
  }

  @DebugLog @Override public void onDetach() {
    super.onDetach();
    presenter.onDetach();
  }

  @Override public void onItemClick(Documents item) {
    presenter.onItemClick(item);
  }
}

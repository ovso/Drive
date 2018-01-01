package io.github.ovso.drive.f_phone;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import butterknife.BindView;
import hugo.weaving.DebugLog;
import io.github.ovso.drive.R;
import io.github.ovso.drive.f_phone.adapter.OnEndlessRecyclerScrollListener;
import io.github.ovso.drive.f_phone.adapter.PhoneAdapter;
import io.github.ovso.drive.f_phone.adapter.PhoneAdapterView;
import io.github.ovso.drive.f_phone.model.Documents;
import io.github.ovso.drive.framework.customview.BaseFragment;
import io.github.ovso.drive.framework.listener.OnRecyclerItemClickListener;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;
import lombok.Getter;

/**
 * Created by jaeho on 2017. 12. 28
 */

public class PhoneFragment extends BaseFragment
    implements PhonePresenter.View, OnRecyclerItemClickListener<Documents>,
    OnEndlessRecyclerScrollListener.OnLoadMoreListener {
  @Getter @BindView(R.id.recyclerview) RecyclerView recyclerView;
  @Inject @Getter CompositeDisposable compositeDisposable;
  @Inject OnEndlessRecyclerScrollListener onEndlessRecyclerScrollListener;
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
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    onEndlessRecyclerScrollListener.setLayoutManager(recyclerView.getLayoutManager());
    recyclerView.addOnScrollListener(onEndlessRecyclerScrollListener);
    recyclerView.setAdapter(adapter);

  }

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
  }

  @Override public void refreshStartToEnd(int startPosition) {
    adapterView.refreshToEnd(startPosition);
  }

  @DebugLog @Override public void onDetach() {
    super.onDetach();
    presenter.onDetach();
  }

  @Override public void onItemClick(Documents item) {
    presenter.onItemClick(item);
  }

  @Override public void onLoadMore() {
    presenter.onLoadMore();
  }

  @Override public void setLoaded() {
    onEndlessRecyclerScrollListener.setLoaded();
  }

  @Override public void notifyItemInserted(int position) {
    adapterView.notifyItemInserted(position);
  }

  @Override public void notifyItemRemoved(int position) {
    adapterView.notifyItemRemoved(position);
  }

  @Override public void notifyItemRangeInserted(int startPosition, int itemCount) {
    adapterView.notifyItemRangeInserted(startPosition, itemCount);
  }
}

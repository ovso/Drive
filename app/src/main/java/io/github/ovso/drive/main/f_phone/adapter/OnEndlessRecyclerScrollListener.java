package io.github.ovso.drive.main.f_phone.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class OnEndlessRecyclerScrollListener extends RecyclerView.OnScrollListener {
  public static String TAG = "OnEndlessRecyclerScrollListener";

  private int visibleThreshold = 1;
  private int lastVisibleItem, totalItemCount;
  private boolean loading;

  private LinearLayoutManager mLayoutManager;

  @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);

    totalItemCount = mLayoutManager.getItemCount();
    lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
    if (!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
      loading = true;
      if (mOnLoadMoreListener != null) {
        mOnLoadMoreListener.onLoadMore();
      }
    }
  }

  public void setLayoutManager(RecyclerView.LayoutManager layout) {
    this.mLayoutManager = (LinearLayoutManager) layout;
  }

  private OnLoadMoreListener mOnLoadMoreListener;

  public void setOnLoadMoreListener(OnLoadMoreListener listener) {
    mOnLoadMoreListener = listener;
  }

  public void setLoaded() {
    loading = false;
  }

  public interface OnLoadMoreListener {
    void onLoadMore();
  }
}

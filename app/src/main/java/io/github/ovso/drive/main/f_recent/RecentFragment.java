package io.github.ovso.drive.main.f_recent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import io.github.ovso.drive.R;
import io.github.ovso.drive.framework.customview.BaseFragment;
import javax.inject.Inject;

/**
 * Created by jaeho on 2017. 12. 28
 */

public class RecentFragment extends BaseFragment implements RecentPresenter.View {
  @Inject RecentPresenter presenter;

  @Inject public RecentFragment() {

  }

  @Override protected int getLayoutResID() {
    return R.layout.fragment_recent;
  }

  @Override protected void onActivityCreate(Bundle savedInstanceState) {

  }

  @Override public void onDetach() {
    super.onDetach();
  }

  @Override public void setRecyclerView() {

  }

  public static Fragment newInstance() {
    return new RecentFragment();
  }
}

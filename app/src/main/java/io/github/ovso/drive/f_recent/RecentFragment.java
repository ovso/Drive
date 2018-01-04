package io.github.ovso.drive.f_recent;

import android.os.Bundle;
import hugo.weaving.DebugLog;
import io.github.ovso.drive.R;
import io.github.ovso.drive.framework.customview.BaseFragment;

/**
 * Created by jaeho on 2017. 12. 28
 */

public class RecentFragment extends BaseFragment {

  @Override protected int getLayoutResID() {
    return R.layout.fragment_recent;
  }

  @Override protected void onActivityCreate(Bundle savedInstanceState) {
  }

  @Override protected boolean isDagger() {
    return false;
  }

  public static RecentFragment newInstance() {
    RecentFragment f = new RecentFragment();
    return f;
  }

  @DebugLog @Override public void onDetach() {
    super.onDetach();
  }
}

package io.github.ovso.drive.main.f_recent;

/**
 * Created by jaeho on 2017. 11. 27
 */

public class RecentPresenterImpl implements RecentPresenter {
  private RecentPresenter.View view;

  public RecentPresenterImpl(RecentPresenter.View view) {
    this.view = view;
  }

  @Override public void onActivityCreate() {

  }
}
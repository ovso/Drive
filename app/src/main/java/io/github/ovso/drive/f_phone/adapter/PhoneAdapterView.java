package io.github.ovso.drive.f_phone.adapter;

import io.github.ovso.drive.framework.adapter.BaseAdapterView;

/**
 * Created by jaeho on 2017. 11. 28
 */

public interface PhoneAdapterView extends BaseAdapterView {
  void refresh(int position);
  void refresh();
  void refreshRemove();
  void refreshRemove(int position);
}

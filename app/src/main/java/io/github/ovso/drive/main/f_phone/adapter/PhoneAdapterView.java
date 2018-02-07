package io.github.ovso.drive.main.f_phone.adapter;

import io.github.ovso.drive.framework.adapter.BaseAdapterView;

/**
 * Created by jaeho on 2017. 12. 29
 */

public interface PhoneAdapterView extends BaseAdapterView {
  void refreshToEnd(int start);
  void notifyItemInserted(int position);
  void notifyItemRemoved(int position);
  void notifyItemRangeInserted(int startPosition, int itemCount);
}

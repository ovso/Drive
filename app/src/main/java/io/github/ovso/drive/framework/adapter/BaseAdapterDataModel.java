package io.github.ovso.drive.framework.adapter;

import java.util.List;

/**
 * Created by jaeho on 2017. 10. 18
 */

public interface BaseAdapterDataModel<T> {
  void add(T item);
  void addAll(List<T> items);
  T remove(int position);
  T getItem(int position);
  void add(int index, T item);
  int getSize();

  void clear();
}

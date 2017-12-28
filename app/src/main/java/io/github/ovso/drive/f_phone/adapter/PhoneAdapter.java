package io.github.ovso.drive.f_phone.adapter;

import android.view.View;
import com.jakewharton.rxbinding2.view.RxView;
import io.github.ovso.drive.R;
import io.github.ovso.drive.f_phone.model.Phone;
import io.github.ovso.drive.framework.SelectableItem;
import io.github.ovso.drive.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.drive.framework.adapter.BaseRecyclerAdapter;
import io.github.ovso.drive.framework.listener.OnRecyclerItemClickListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Created by jaeho on 2017. 11. 27
 */

public class PhoneAdapter extends BaseRecyclerAdapter
    implements PhoneAdapterView, BaseAdapterDataModel<SelectableItem<Phone>> {

  private List<SelectableItem<Phone>> selectableItems = new ArrayList<>();

  @Accessors(chain = true) @Setter private OnRecyclerItemClickListener<SelectableItem<Phone>>
      onRecyclerItemClickListener;

  @Accessors(chain = true) private @Setter CompositeDisposable compositeDisposable;

  @Override protected BaseViewHolder createViewHolder(View view, int viewType) {
    return new PhoneViewHolder(view);
  }

  @Override public int getLayoutRes(int viewType) {
    return R.layout.fragment_phone_item;
  }

  @Override public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
    if (viewHolder instanceof PhoneViewHolder) {
      SelectableItem<Phone> selectableItem = this.selectableItems.get(position);
      Phone item = selectableItem.getItem();
      PhoneViewHolder holder = (PhoneViewHolder) viewHolder;

      //holder.setIsRecyclable(false);

      holder.titleTextview.setText(item.getTitle());
      compositeDisposable.add(RxView.clicks(holder.itemView)
          .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(o -> onRecyclerItemClickListener.onItemClick(selectableItem)));
    }
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public void add(SelectableItem<Phone> changeItem) {
    selectableItems.add(changeItem);
  }

  @Override public void addAll(List<SelectableItem<Phone>> items) {
    this.selectableItems.addAll(items);
  }

  @Override public SelectableItem<Phone> remove(int position) {
    return this.selectableItems.remove(position);
  }

  @Override public SelectableItem<Phone> getItem(int position) {
    return null;
  }

  @Override public void add(int index, SelectableItem<Phone> item) {
    this.selectableItems.add(index, item);
  }

  @Override public int getSize() {
    return selectableItems.size();
  }

  @Override public void clear() {
    selectableItems.clear();
  }

  @Override public void refresh(int position) {
    notifyItemChanged(position);
  }

  @Override public void refresh() {
    notifyDataSetChanged();
  }

  @Override public void refreshRemove() {
    notifyItemRangeRemoved(0, getSize());
  }

  @Override public void refreshRemove(int position) {
    notifyItemRemoved(position);
  }
}

package io.github.ovso.drive.f_phone.adapter;

import android.view.View;
import com.jakewharton.rxbinding2.view.RxView;
import io.github.ovso.drive.R;
import io.github.ovso.drive.f_phone.model.Phone;
import io.github.ovso.drive.framework.adapter.BaseAdapterDataModel;
import io.github.ovso.drive.framework.adapter.BaseAdapterView;
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
    implements BaseAdapterView, BaseAdapterDataModel<Phone> {

  private List<Phone> items = new ArrayList<>();

  @Accessors(chain = true) @Setter private OnRecyclerItemClickListener<Phone>
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
      Phone item = this.items.get(position);
      PhoneViewHolder holder = (PhoneViewHolder) viewHolder;

      //holder.setIsRecyclable(false);

      holder.titleTextview.setText(item.getTitle());
      holder.phoneTextView.setText(item.getPhoneNumber());
      compositeDisposable.add(RxView.clicks(holder.itemView)
          .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(o -> onRecyclerItemClickListener.onItemClick(item)));
    }
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public void add(Phone item) {
    items.add(item);
  }

  @Override public void addAll(List<Phone> items) {
    this.items.addAll(items);
  }

  @Override public Phone remove(int position) {
    return this.items.remove(position);
  }

  @Override public Phone getItem(int position) {
    return items.get(position);
  }

  @Override public void add(int index, Phone item) {
    this.items.add(index, item);
  }

  @Override public int getSize() {
    return items.size();
  }

  @Override public void clear() {
    items.clear();
  }

  @Override public void refresh() {
    notifyDataSetChanged();
  }
}

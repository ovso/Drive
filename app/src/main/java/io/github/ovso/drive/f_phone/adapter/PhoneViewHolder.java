package io.github.ovso.drive.f_phone.adapter;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import io.github.ovso.drive.R;
import io.github.ovso.drive.framework.adapter.BaseRecyclerAdapter;

/**
 * Created by jaeho on 2017. 11. 27
 */

public class PhoneViewHolder extends BaseRecyclerAdapter.BaseViewHolder {

  @BindView(R.id.title_textview) TextView titleTextview;
  @BindView(R.id.phone_textview) TextView phoneTextView;

  public PhoneViewHolder(View itemView) {
    super(itemView);
  }
}
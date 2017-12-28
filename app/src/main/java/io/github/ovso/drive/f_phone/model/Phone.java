package io.github.ovso.drive.f_phone.model;

import com.google.firebase.database.IgnoreExtraProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by jaeho on 2017. 11. 27
 */

@IgnoreExtraProperties @EqualsAndHashCode(callSuper = false) @Getter @ToString public class Phone {
  public String title;
  private String address;
  private String phoneNumber;
  private String video_id;
  private int rec;
}
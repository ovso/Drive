package io.github.ovso.drive.main.f_phone.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by jaeho on 2017. 11. 27
 */

@EqualsAndHashCode(callSuper = false) @Getter @ToString public class Phone {
  public String title;
  private String address;
  private String phoneNumber;
}
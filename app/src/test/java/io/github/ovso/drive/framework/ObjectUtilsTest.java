package io.github.ovso.drive.framework;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jaeho on 2018. 3. 8
 */
public class ObjectUtilsTest {
  @Before public void setUp() throws Exception {
  }

  @After public void tearDown() throws Exception {
  }

  @Test public void stringValidator_CorrectReturnsTrue() {
    assertTrue(ObjectUtils.isEmpty(""));
  }

  @Test public void stringValidator_InvalidReturnsFalse() {
    assertFalse(ObjectUtils.isEmpty("!"));
  }

  @Test public void listValidator_CorrectReturnsTrue() {
    assertTrue(ObjectUtils.isEmpty(new ArrayList<String>()));
  }

  @Test public void listValidator_InvalidReturnsFalse() {
    List<String> items = new ArrayList<>();
    items.add("1");
    assertFalse(ObjectUtils.isEmpty(items));
  }

  @Test public void mapValidator_CorrectReturnsTrue() {
    assertTrue(ObjectUtils.isEmpty(new HashMap<String, String>()));
  }

  @Test public void mapValidator_InvalidReturnsFalse() {
    Map<String, String> map = new HashMap<>();
    map.put("1", "1");
    map.put("2", "2");
    assertFalse(ObjectUtils.isEmpty(map));
  }

  @Test public void arrayValidator_CorrectReturnsTrue() {
    Object[] objects = {};
    assertTrue(ObjectUtils.isEmpty(objects));
  }

  @Test public void arrayValidator_InvalidReturnsFalse() {
    Object[] objects = new Object[3];
    objects[0] = "1";
    objects[1] = new Date();
    objects[2] = Calendar.getInstance();
    assertFalse(ObjectUtils.isEmpty(objects));
  }
}
package io.github.ovso.drive.framework;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import io.github.ovso.drive.R;
import javax.annotation.Nonnull;

/**
 * Created by jaeho on 2018. 2. 6
 */

public class ActivityUtils {

  /**
   * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
   * performed by the {@code fragmentManager}.
   */
  public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
      @NonNull Fragment fragment, int frameId) {
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.add(frameId, fragment);
    transaction.commit();
  }

  public static void replaceFragment(@Nonnull FragmentManager fragmentManager,
      @Nonnull Fragment fragment, int containerViewId) {
    fragmentManager.beginTransaction()
        .setCustomAnimations(R.animator.enter_animation, R.animator.exit_animation,
            R.animator.enter_animation, R.animator.exit_animation)
        .replace(containerViewId, fragment)
        .commit();
  }
}

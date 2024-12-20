// Generated by view binder compiler. Do not edit!
package org.hyperskill.simplebankmanager.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import org.hyperskill.simplebankmanager.R;

public final class FragmentViewBalanceBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView viewBalanceAmountTextView;

  @NonNull
  public final TextView viewBalanceLabelTextView;

  private FragmentViewBalanceBinding(@NonNull LinearLayout rootView,
      @NonNull TextView viewBalanceAmountTextView, @NonNull TextView viewBalanceLabelTextView) {
    this.rootView = rootView;
    this.viewBalanceAmountTextView = viewBalanceAmountTextView;
    this.viewBalanceLabelTextView = viewBalanceLabelTextView;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentViewBalanceBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentViewBalanceBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_view_balance, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentViewBalanceBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.viewBalanceAmountTextView;
      TextView viewBalanceAmountTextView = ViewBindings.findChildViewById(rootView, id);
      if (viewBalanceAmountTextView == null) {
        break missingId;
      }

      id = R.id.viewBalanceLabelTextView;
      TextView viewBalanceLabelTextView = ViewBindings.findChildViewById(rootView, id);
      if (viewBalanceLabelTextView == null) {
        break missingId;
      }

      return new FragmentViewBalanceBinding((LinearLayout) rootView, viewBalanceAmountTextView,
          viewBalanceLabelTextView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

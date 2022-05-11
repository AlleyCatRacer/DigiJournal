// Generated by view binder compiler. Do not edit!
package com.s22.digijournal.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.s22.digijournal.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCategoryDetailsBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final TextView categoryTitle;

  @NonNull
  public final Button editButton;

  @NonNull
  public final RecyclerView taskListRecycler;

  private FragmentCategoryDetailsBinding(@NonNull ScrollView rootView,
      @NonNull TextView categoryTitle, @NonNull Button editButton,
      @NonNull RecyclerView taskListRecycler) {
    this.rootView = rootView;
    this.categoryTitle = categoryTitle;
    this.editButton = editButton;
    this.taskListRecycler = taskListRecycler;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCategoryDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCategoryDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_category_details, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCategoryDetailsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.category_title;
      TextView categoryTitle = ViewBindings.findChildViewById(rootView, id);
      if (categoryTitle == null) {
        break missingId;
      }

      id = R.id.edit_button;
      Button editButton = ViewBindings.findChildViewById(rootView, id);
      if (editButton == null) {
        break missingId;
      }

      id = R.id.taskList_recycler;
      RecyclerView taskListRecycler = ViewBindings.findChildViewById(rootView, id);
      if (taskListRecycler == null) {
        break missingId;
      }

      return new FragmentCategoryDetailsBinding((ScrollView) rootView, categoryTitle, editButton,
          taskListRecycler);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
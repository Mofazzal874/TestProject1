// Generated by view binder compiler. Do not edit!
package com.example.testproject1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.testproject1.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class MostVisitedDesignBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView mostVisitedDescription;

  @NonNull
  public final ImageView mostVisitedImage;

  @NonNull
  public final RatingBar mostVisitedRating;

  @NonNull
  public final TextView mostVisitedTitle;

  private MostVisitedDesignBinding(@NonNull CardView rootView,
      @NonNull TextView mostVisitedDescription, @NonNull ImageView mostVisitedImage,
      @NonNull RatingBar mostVisitedRating, @NonNull TextView mostVisitedTitle) {
    this.rootView = rootView;
    this.mostVisitedDescription = mostVisitedDescription;
    this.mostVisitedImage = mostVisitedImage;
    this.mostVisitedRating = mostVisitedRating;
    this.mostVisitedTitle = mostVisitedTitle;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static MostVisitedDesignBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static MostVisitedDesignBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.most_visited_design, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static MostVisitedDesignBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.most_visited_description;
      TextView mostVisitedDescription = ViewBindings.findChildViewById(rootView, id);
      if (mostVisitedDescription == null) {
        break missingId;
      }

      id = R.id.most_visited_image;
      ImageView mostVisitedImage = ViewBindings.findChildViewById(rootView, id);
      if (mostVisitedImage == null) {
        break missingId;
      }

      id = R.id.most_visited_rating;
      RatingBar mostVisitedRating = ViewBindings.findChildViewById(rootView, id);
      if (mostVisitedRating == null) {
        break missingId;
      }

      id = R.id.most_visited_title;
      TextView mostVisitedTitle = ViewBindings.findChildViewById(rootView, id);
      if (mostVisitedTitle == null) {
        break missingId;
      }

      return new MostVisitedDesignBinding((CardView) rootView, mostVisitedDescription,
          mostVisitedImage, mostVisitedRating, mostVisitedTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

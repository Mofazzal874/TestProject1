// Generated by view binder compiler. Do not edit!
package com.example.testproject1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

public final class WishlistDesignBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView wishlistDescription;

  @NonNull
  public final ImageView wishlistImage;

  @NonNull
  public final TextView wishlistTitle;

  private WishlistDesignBinding(@NonNull CardView rootView, @NonNull TextView wishlistDescription,
      @NonNull ImageView wishlistImage, @NonNull TextView wishlistTitle) {
    this.rootView = rootView;
    this.wishlistDescription = wishlistDescription;
    this.wishlistImage = wishlistImage;
    this.wishlistTitle = wishlistTitle;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static WishlistDesignBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static WishlistDesignBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.wishlist_design, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static WishlistDesignBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.wishlist_description;
      TextView wishlistDescription = ViewBindings.findChildViewById(rootView, id);
      if (wishlistDescription == null) {
        break missingId;
      }

      id = R.id.wishlist_image;
      ImageView wishlistImage = ViewBindings.findChildViewById(rootView, id);
      if (wishlistImage == null) {
        break missingId;
      }

      id = R.id.wishlist_title;
      TextView wishlistTitle = ViewBindings.findChildViewById(rootView, id);
      if (wishlistTitle == null) {
        break missingId;
      }

      return new WishlistDesignBinding((CardView) rootView, wishlistDescription, wishlistImage,
          wishlistTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

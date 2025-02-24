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

public final class ItemTeamMemberBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final TextView address;

  @NonNull
  public final TextView addressValue;

  @NonNull
  public final TextView bloodGroup;

  @NonNull
  public final TextView bloodGroupValue;

  @NonNull
  public final TextView phone;

  @NonNull
  public final TextView phoneValue;

  @NonNull
  public final TextView userName;

  @NonNull
  public final TextView userNameValue;

  @NonNull
  public final ImageView wishlistImage;

  @NonNull
  public final TextView wishlistTitle;

  private ItemTeamMemberBinding(@NonNull CardView rootView, @NonNull TextView address,
      @NonNull TextView addressValue, @NonNull TextView bloodGroup,
      @NonNull TextView bloodGroupValue, @NonNull TextView phone, @NonNull TextView phoneValue,
      @NonNull TextView userName, @NonNull TextView userNameValue, @NonNull ImageView wishlistImage,
      @NonNull TextView wishlistTitle) {
    this.rootView = rootView;
    this.address = address;
    this.addressValue = addressValue;
    this.bloodGroup = bloodGroup;
    this.bloodGroupValue = bloodGroupValue;
    this.phone = phone;
    this.phoneValue = phoneValue;
    this.userName = userName;
    this.userNameValue = userNameValue;
    this.wishlistImage = wishlistImage;
    this.wishlistTitle = wishlistTitle;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static ItemTeamMemberBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ItemTeamMemberBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.item_team_member, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ItemTeamMemberBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.address;
      TextView address = ViewBindings.findChildViewById(rootView, id);
      if (address == null) {
        break missingId;
      }

      id = R.id.addressValue;
      TextView addressValue = ViewBindings.findChildViewById(rootView, id);
      if (addressValue == null) {
        break missingId;
      }

      id = R.id.bloodGroup;
      TextView bloodGroup = ViewBindings.findChildViewById(rootView, id);
      if (bloodGroup == null) {
        break missingId;
      }

      id = R.id.bloodGroupValue;
      TextView bloodGroupValue = ViewBindings.findChildViewById(rootView, id);
      if (bloodGroupValue == null) {
        break missingId;
      }

      id = R.id.phone;
      TextView phone = ViewBindings.findChildViewById(rootView, id);
      if (phone == null) {
        break missingId;
      }

      id = R.id.phoneValue;
      TextView phoneValue = ViewBindings.findChildViewById(rootView, id);
      if (phoneValue == null) {
        break missingId;
      }

      id = R.id.userName;
      TextView userName = ViewBindings.findChildViewById(rootView, id);
      if (userName == null) {
        break missingId;
      }

      id = R.id.userNameValue;
      TextView userNameValue = ViewBindings.findChildViewById(rootView, id);
      if (userNameValue == null) {
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

      return new ItemTeamMemberBinding((CardView) rootView, address, addressValue, bloodGroup,
          bloodGroupValue, phone, phoneValue, userName, userNameValue, wishlistImage,
          wishlistTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

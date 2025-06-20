// Generated by view binder compiler. Do not edit!
package com.example.allergologswps.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.allergologswps.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.card.MaterialCardView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomeBinding implements ViewBinding {
  @NonNull
  private final CoordinatorLayout rootView;

  @NonNull
  public final MaterialCardView addProductCard;

  @NonNull
  public final BottomNavigationView bottomNav;

  @NonNull
  public final ImageButton buttomAddProduct;

  @NonNull
  public final TextView frequentLabel;

  @NonNull
  public final RecyclerView frequentProductsRecyclerView;

  @NonNull
  public final TextView homeTitle;

  private ActivityHomeBinding(@NonNull CoordinatorLayout rootView,
      @NonNull MaterialCardView addProductCard, @NonNull BottomNavigationView bottomNav,
      @NonNull ImageButton buttomAddProduct, @NonNull TextView frequentLabel,
      @NonNull RecyclerView frequentProductsRecyclerView, @NonNull TextView homeTitle) {
    this.rootView = rootView;
    this.addProductCard = addProductCard;
    this.bottomNav = bottomNav;
    this.buttomAddProduct = buttomAddProduct;
    this.frequentLabel = frequentLabel;
    this.frequentProductsRecyclerView = frequentProductsRecyclerView;
    this.homeTitle = homeTitle;
  }

  @Override
  @NonNull
  public CoordinatorLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.addProductCard;
      MaterialCardView addProductCard = ViewBindings.findChildViewById(rootView, id);
      if (addProductCard == null) {
        break missingId;
      }

      id = R.id.bottomNav;
      BottomNavigationView bottomNav = ViewBindings.findChildViewById(rootView, id);
      if (bottomNav == null) {
        break missingId;
      }

      id = R.id.buttom_add_product;
      ImageButton buttomAddProduct = ViewBindings.findChildViewById(rootView, id);
      if (buttomAddProduct == null) {
        break missingId;
      }

      id = R.id.frequentLabel;
      TextView frequentLabel = ViewBindings.findChildViewById(rootView, id);
      if (frequentLabel == null) {
        break missingId;
      }

      id = R.id.frequentProductsRecyclerView;
      RecyclerView frequentProductsRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (frequentProductsRecyclerView == null) {
        break missingId;
      }

      id = R.id.homeTitle;
      TextView homeTitle = ViewBindings.findChildViewById(rootView, id);
      if (homeTitle == null) {
        break missingId;
      }

      return new ActivityHomeBinding((CoordinatorLayout) rootView, addProductCard, bottomNav,
          buttomAddProduct, frequentLabel, frequentProductsRecyclerView, homeTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}

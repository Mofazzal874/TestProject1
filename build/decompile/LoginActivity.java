/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Bundle
 *  android.os.Handler
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.animation.Animation
 *  android.view.animation.AnimationUtils
 *  androidx.appcompat.app.AppCompatActivity
 *  com.example.testproject1.R$anim
 *  com.example.testproject1.databinding.ActivityMainBinding
 */
package com.example.testproject1;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;
import com.example.testproject1.R;
import com.example.testproject1.databinding.ActivityMainBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={1, 9, 0}, k=1, xi=48, d1={"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\r"}, d2={"Lcom/example/testproject1/LoginActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/testproject1/databinding/ActivityMainBinding;", "getBinding", "()Lcom/example/testproject1/databinding/ActivityMainBinding;", "setBinding", "(Lcom/example/testproject1/databinding/ActivityMainBinding;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class LoginActivity
extends AppCompatActivity {
    public ActivityMainBinding binding;

    @NotNull
    public final ActivityMainBinding getBinding() {
        ActivityMainBinding activityMainBinding = this.binding;
        if (activityMainBinding != null) {
            return activityMainBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(@NotNull ActivityMainBinding activityMainBinding) {
        Intrinsics.checkNotNullParameter((Object)activityMainBinding, "<set-?>");
        this.binding = activityMainBinding;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate((LayoutInflater)this.getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue((Object)activityMainBinding, "inflate(...)");
        this.setBinding(activityMainBinding);
        this.setContentView((View)this.getBinding().getRoot());
        Ref.ObjectRef fade_in = new Ref.ObjectRef();
        fade_in.element = AnimationUtils.loadAnimation((Context)((Context)this), (int)R.anim.fade_in);
        Animation bottom_down = AnimationUtils.loadAnimation((Context)((Context)this), (int)R.anim.bottom_down);
        this.getBinding().topLinearLayout.setAnimation(bottom_down);
        Handler handler = new Handler();
        Runnable runnable = () -> LoginActivity.onCreate$lambda$0(this, fade_in);
        handler.postDelayed(runnable, 1000L);
    }

    private static final void onCreate$lambda$0(LoginActivity this$0, Ref.ObjectRef $fade_in) {
        Intrinsics.checkNotNullParameter((Object)this$0, "this$0");
        Intrinsics.checkNotNullParameter($fade_in, "$fade_in");
        this$0.getBinding().textView.setAnimation((Animation)$fade_in.element);
        this$0.getBinding().cardView1.setAnimation((Animation)$fade_in.element);
        this$0.getBinding().cardView.setAnimation((Animation)$fade_in.element);
        this$0.getBinding().signInWithFacebook.setAnimation((Animation)$fade_in.element);
        this$0.getBinding().signInWithGoogle.setAnimation((Animation)$fade_in.element);
        this$0.getBinding().signInWithGithub.setAnimation((Animation)$fade_in.element);
        this$0.getBinding().OR.setAnimation((Animation)$fade_in.element);
        this$0.getBinding().signUpButton.setAnimation((Animation)$fade_in.element);
    }
}

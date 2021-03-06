package com.toutools.me.toutools;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by User on 18/1/2561.
 */

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
public ScrollAwareFABBehavior(Context context, AttributeSet attrs){
    super();
}
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                                       @NonNull FloatingActionButton child,
                                       @NonNull View directTargetChild,
                                       @NonNull View target, int axes, int type) {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, axes, type);
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout,
                               @NonNull FloatingActionButton child,
                               @NonNull View target, int dxConsumed,
                               int dyConsumed, int dxUnconsumed,
                               int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
   if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE){
       child.hide();;

   }else if(dyConsumed < 0 && child.getVisibility() != View.VISIBLE){
       child.show();
   }

    }
}

package com.alodia.bitbash.adapters;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Alaina Traxler on 3/15/2017.
 */

public class SwipelessViewPager extends ViewPager {
    public SwipelessViewPager(Context context) {
        super(context);
    }

    public SwipelessViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}

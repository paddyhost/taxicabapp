package com.example.administrator.taxicab.Utils.MapUtils;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * Created by shree on 01/09/2017.
 */

public class TouchableWrapper  extends FrameLayout {

    public interface OnDragListener {
         boolean onDrag(MotionEvent motionEvent);
    }

    private OnDragListener mOnDragListener;

    public TouchableWrapper(Context context) {
        super(context);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mOnDragListener != null) {
            mOnDragListener.onDrag(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mOnDragListener != null) {
            mOnDragListener.onDrag(ev);
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setOnDragListener(OnDragListener mOnDragListener) {
        this.mOnDragListener = mOnDragListener;
    }}

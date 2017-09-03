package com.example.administrator.taxicab.Utils.MapUtils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by shree on 01/09/2017.
 */

public class CustomSupportMapFragment extends SupportMapFragment
{
    private View mOriginalView;
    private TouchableWrapper mMapWrapperLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mOriginalView = super.onCreateView(inflater, container, savedInstanceState);

        mMapWrapperLayout = new TouchableWrapper(getActivity());
        mMapWrapperLayout.addView(mOriginalView);

        return mMapWrapperLayout;
    }

    @Override
    public View getView() {
        return mOriginalView;
    }

    public void setOnDragListener(TouchableWrapper.OnDragListener onDragListener) {
        mMapWrapperLayout.setOnDragListener(onDragListener);
    }
}

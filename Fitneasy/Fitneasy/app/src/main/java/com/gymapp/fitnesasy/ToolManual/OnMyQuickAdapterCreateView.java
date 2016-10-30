package com.gymapp.fitnesasy.ToolManual;

import android.view.View;


public interface OnMyQuickAdapterCreateView<T> {
    public void onCreateView(View view, T item);
}

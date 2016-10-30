package com.gymapp.fitnesasy.ToolManual;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;


public class MyQuickAdapter<T> extends ArrayAdapter<T> {
    private final Context context;
    private final ArrayList<T> dataset;
    private int viewLayout;

    public void setOnCreateView(OnMyQuickAdapterCreateView<T> onCreateView) {
        this.onCreateView = onCreateView;
    }

    private OnMyQuickAdapterCreateView<T> onCreateView;

    public MyQuickAdapter(Context context,ArrayList<T> dataset,int viewLayout) {
        super(context, android.R.layout.simple_list_item_1,dataset );
        this.context = context;
        this.dataset = dataset;
        this.viewLayout = viewLayout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(viewLayout, parent, false);
        }

        if(onCreateView!=null){
            onCreateView.onCreateView(convertView,dataset.get(position));
        }
        return convertView;
    }





}

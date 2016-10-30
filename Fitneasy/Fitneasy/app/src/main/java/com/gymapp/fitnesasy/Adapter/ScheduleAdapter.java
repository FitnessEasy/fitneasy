package com.gymapp.fitnesasy.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gymapp.fitnesasy.Fragment.ListScheduleFragment;
import com.gymapp.fitnesasy.Model.DTO.Schedule;
import com.gymapp.fitnesasy.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;



public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    Context context;
    List<Schedule> scheduleList;
    int layout;


    public ScheduleAdapter(Context context, int layout, List<Schedule> scheduleList){
        this.context = context;
        this.scheduleList = scheduleList;
        this.layout = layout;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imMuscleType;
        TextView txtMuscleType;
        FrameLayout cardView;
        ProgressBar progressBar;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            imMuscleType = (ImageView) itemView.findViewById(R.id.imMuscleTypeItem);
            txtMuscleType = (TextView) itemView.findViewById(R.id.txtMuscleTypeItem);
            cardView = (FrameLayout) itemView.findViewById(R.id.cardView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress_bar_MuscleType);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.lnMuscleTypeItem);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(layout,parent,false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Schedule schedule = scheduleList.get(position);
        if(!schedule.getImage().isEmpty()){
            Picasso.with(context).load(schedule.getImage()).resize(140,140).into(holder.imMuscleType, new Callback() {
                @Override
                public void onSuccess() {
                    
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError() {

                }
            });
        }


        holder.txtMuscleType.setText(schedule.getName());


        holder.cardView.setTag(schedule.getId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ListScheduleFragment listScheduleFragment = new ListScheduleFragment();
                Bundle bundle = new Bundle();
                bundle.putString("scheduleId", schedule.getId());
                bundle.putString("scheduleName", schedule.getName());
                bundle.putString("scheduleInfo", schedule.getInfo());
                listScheduleFragment.setArguments(bundle);
                fragmentTransaction.addToBackStack("TrangChuActivity");
                fragmentTransaction.replace(R.id.themFragment,listScheduleFragment);
                fragmentTransaction.commit();


            }
        });

    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }



}

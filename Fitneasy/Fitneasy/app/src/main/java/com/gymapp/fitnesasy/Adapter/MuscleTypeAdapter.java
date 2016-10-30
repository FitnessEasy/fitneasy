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

import com.gymapp.fitnesasy.Fragment.ListExerciseFragment;
import com.gymapp.fitnesasy.Model.DTO.MuscleType;
import com.gymapp.fitnesasy.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;



public class MuscleTypeAdapter extends RecyclerView.Adapter<MuscleTypeAdapter.ViewHolder> {

    Context context;
    List<MuscleType> muscleTypesList;
    int layout;


    public MuscleTypeAdapter(Context context, int layout, List<MuscleType> muscleTypesList){
        this.context = context;
        this.muscleTypesList = muscleTypesList;
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
        final MuscleType muscleType = muscleTypesList.get(position);
        Picasso.with(context).load(muscleType.getImage()).resize(140,140).into(holder.imMuscleType, new Callback() {
            @Override
            public void onSuccess() {
                
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError() {

            }
        });

        holder.txtMuscleType.setText(muscleType.getName());


        holder.cardView.setTag(muscleType.getId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ListExerciseFragment exerciseFragment = new ListExerciseFragment();
                Bundle bundle = new Bundle();
                bundle.putString("muscleTypeID", muscleType.getId());
                bundle.putString("muscleName", muscleType.getName());
                exerciseFragment.setArguments(bundle);
                fragmentTransaction.addToBackStack("MainActivity");
                fragmentTransaction.replace(R.id.themFragment,exerciseFragment);
                fragmentTransaction.commit();


            }
        });

    }

    @Override
    public int getItemCount() {
        return muscleTypesList.size();
    }



}


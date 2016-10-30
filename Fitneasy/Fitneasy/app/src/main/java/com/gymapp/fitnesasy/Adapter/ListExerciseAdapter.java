package com.gymapp.fitnesasy.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gymapp.fitnesasy.Activity.ExerciseActivity;
import com.gymapp.fitnesasy.Model.DTO.Exercise;
import com.gymapp.fitnesasy.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;



public class ListExerciseAdapter extends RecyclerView.Adapter<ListExerciseAdapter.ViewHolder> {

    Context context;
    List<Exercise> muscleTypesList;
    int layout;


    public ListExerciseAdapter(Context context, int layout, List<Exercise> muscleTypesList) {
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
        View view = layoutInflater.inflate(layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Exercise exercise = muscleTypesList.get(position);
        if (exercise.getImage().length()>0) {
            Picasso.with(context).load(exercise.getImage()).resize(140, 140).into(holder.imMuscleType, new Callback() {
                @Override
                public void onSuccess() {
                    
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError() {

                }
            });
        }


        holder.txtMuscleType.setText(exercise.getName());


        holder.cardView.setTag(exercise);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent iDetailExercise = new Intent(context, ExerciseActivity.class);
                iDetailExercise.putExtra("exercise", (Exercise) v.getTag());
                context.startActivity(iDetailExercise);


            }
        });

    }

    @Override
    public int getItemCount() {
        return muscleTypesList.size();
    }
}
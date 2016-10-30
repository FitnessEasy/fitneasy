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

import com.gymapp.fitnesasy.Activity.NutitionDetailActivity;
import com.gymapp.fitnesasy.Model.DTO.Nutrition;
import com.gymapp.fitnesasy.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;



public class NutitionAdapter extends RecyclerView.Adapter<NutitionAdapter.ViewHolder> {

    Context context;
    List<Nutrition> nutritionList;
    int layout;


    public NutitionAdapter(Context context, int layout, List<Nutrition> nutritionList){
        this.context = context;
        this.nutritionList = nutritionList;
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
        final Nutrition nutrition = nutritionList.get(position);
        if(nutrition.getImage()!=null){
            Picasso.with(context).load(nutrition.getImage()).resize(140,140).into(holder.imMuscleType, new Callback() {
                @Override
                public void onSuccess() {
                    
                    holder.progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onError() {

                }
            });
        }




        holder.txtMuscleType.setText(nutrition.getName());


        holder.cardView.setTag(nutrition);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, NutitionDetailActivity.class);
                intent.putExtra("nutition",(Nutrition)v.getTag());
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return nutritionList.size();
    }



}
package com.gymapp.fitnesasy.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gymapp.fitnesasy.Adapter.ListExerciseAdapter;
import com.gymapp.fitnesasy.DataManager.FavoriteExerciseDataManager;
import com.gymapp.fitnesasy.DataManager.OnLoadDataComplete;
import com.gymapp.fitnesasy.Model.DTO.Exercise;
import com.gymapp.fitnesasy.Model.DTO.FavoriteExercise;
import com.gymapp.fitnesasy.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.gymapp.fitnesasy.Fragment.MuscleTypeFragment.exerciseArrayList;


public class ExerciseActivity extends AppCompatActivity implements View.OnClickListener, OnLoadDataComplete {

    
    TextView txtNameExerciseDetail, txtThongTinChiTiet;
    Toolbar toolbar;
    ImageView imXemThemChiTiet;
    boolean kiemtraxochitiet = false;
    ProgressBar progressBar;
    ImageView imImageExerciseDetail;
    Button btnWatchExercise;
    Exercise exercise;
    RecyclerView recylerExerciseInvolve;
    ImageView imageViewlike;
    boolean isLike;

    FavoriteExerciseDataManager favoriteExerciseDataManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isLike=false;
        setContentView(R.layout.activity_exercise);
        txtNameExerciseDetail = (TextView) findViewById(R.id.txtNameExerciseDetail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtThongTinChiTiet = (TextView) findViewById(R.id.txtThongTinChiTiet);
        imXemThemChiTiet = (ImageView) findViewById(R.id.imXemThemChiTiet);
        imImageExerciseDetail = (ImageView) findViewById(R.id.imImageExerciseDetail);
        btnWatchExercise = (Button) findViewById(R.id.btnWatchExercise);
        recylerExerciseInvolve = (RecyclerView) findViewById(R.id.recylerExerciseInvolve);
        imageViewlike = (ImageView) findViewById(R.id.imageViewlike);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        
        exercise = (Exercise) getIntent().getSerializableExtra("exercise");
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_chi_tiet);

        favoriteExerciseDataManager = new FavoriteExerciseDataManager();
        favoriteExerciseDataManager.setOnLoadDataComplete(this);

        setEventHandler();

 

        setData();
        setAdapterExerciseInvolve(exerciseArrayList);

    }

    private void setEventHandler() {
        btnWatchExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iDetailExercise = new Intent(ExerciseActivity.this, WatchExercise.class);
                iDetailExercise.putExtra("id", exercise.getId());
               
                startActivity(iDetailExercise);
            }
        });
        imageViewlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(favoriteExerciseDataManager.email==null)
                {
                    Intent intentTimKiem = new Intent(ExerciseActivity.this, SignInActivity.class);
                    startActivity(intentTimKiem);

                   

                   
                    
                }

                if(favoriteExerciseDataManager.checkLike(exercise.getId())){
                    imageViewlike.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    isLike=false;
                }
                else
                    imageViewlike.setImageResource(R.drawable.ic_favorite_border_red_24dp);


                favoriteExerciseDataManager.changeLike(exercise.getId());

            }
        });
    }

    private void setData() {
        if (exercise.getImage() == null) {
            Toast.makeText(this, "Chưa có image", Toast.LENGTH_SHORT).show();
        } else {
            try {
                Picasso.with(ExerciseActivity.this).load(exercise.getImage()).resize(200, 200).into(imImageExerciseDetail, new Callback() {
                    @Override
                    public void onSuccess() {

                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
            } catch (Exception ex) {

            }
        }
        
        if (exercise.getName() != null || exercise.getName().length() == 0)
            txtNameExerciseDetail.setText(exercise.getName());


        if (exercise.getInfo() == null || exercise.getInfo().length() == 0)
            txtThongTinChiTiet.setText("Chưa có thông tin chi tiết");
        else {
            txtThongTinChiTiet.setText(Html.fromHtml(exercise.getInfo()));
























        }


    }


    void setAdapterExerciseInvolve(ArrayList<Exercise> dataset) {









        ListExerciseAdapter listExerciseAdapter;
        listExerciseAdapter = new ListExerciseAdapter(this, R.layout.custom_layout_exercise, dataset);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        
        
        recylerExerciseInvolve.setLayoutManager(layoutManager);
        recylerExerciseInvolve.setAdapter(listExerciseAdapter);
        listExerciseAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }


    private Drawable getHinhChiTiet(int idDrawable) {
        Drawable drawable;
        if (Build.VERSION.SDK_INT > 21) {
            drawable = ContextCompat.getDrawable(this, idDrawable);
        } else {
            drawable = getResources().getDrawable(idDrawable);
        }

        return drawable;
    }

    private int getIdColor(int idcolor) {

        int color = 0;
        if (Build.VERSION.SDK_INT > 21) {
            color = ContextCompat.getColor(this, idcolor);
        } else {
            color = getResources().getColor(idcolor);
        }

        return color;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
        }
    }


    @Override
    public void onComplete() {

        if(favoriteExerciseDataManager.getDataset().size()==0)
            return;

        FavoriteExercise favoriteExercise = favoriteExerciseDataManager.getDataset().get(favoriteExerciseDataManager.getDataset().size()-1);


        if (favoriteExerciseDataManager.checkLike(exercise.getId())) {
            imageViewlike.setImageResource(R.drawable.ic_favorite_border_red_24dp);
            isLike=true;
        }
        

    }
}
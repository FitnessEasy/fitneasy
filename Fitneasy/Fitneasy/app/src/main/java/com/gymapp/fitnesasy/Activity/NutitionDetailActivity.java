package com.gymapp.fitnesasy.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.gymapp.fitnesasy.Adapter.NutitionAdapter;
import com.gymapp.fitnesasy.Model.DTO.Nutrition;
import com.gymapp.fitnesasy.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.gymapp.fitnesasy.Fragment.NutitionFragment.nutritionArrayList;



public class NutitionDetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtNameExerciseDetail, txtThongTinChiTiet;
    Toolbar toolbar;
    ImageView imXemThemChiTiet;
    ProgressBar progressBar;
    ImageView imImageExerciseDetail;
    Button btnWatchExercise;
    Nutrition nutrition;
    RecyclerView recylerExerciseInvolve;
    ImageView imageViewlike;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        nutrition = (Nutrition) getIntent().getSerializableExtra("nutition");
        progressBar = (ProgressBar) findViewById(R.id.progress_bar_chi_tiet);

        setEventHandler();

        setData();
        setAdapterExerciseInvolve(nutritionArrayList);

    }

    private void setEventHandler() {
        imageViewlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    private void setData() {
        if (nutrition.getImage() == null) {
            Toast.makeText(this, "Chưa có image", Toast.LENGTH_SHORT).show();
        } else {
            try {
                Picasso.with(NutitionDetailActivity.this).load(nutrition.getImage()).resize(200, 200).into(imImageExerciseDetail, new Callback() {
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
        if (nutrition.getName() != null || nutrition.getName().length() == 0)
            txtNameExerciseDetail.setText(nutrition.getName());


        if (nutrition.getInfo() == null || nutrition.getInfo().length() == 0)
            txtThongTinChiTiet.setText("Chưa có thông tin chi tiết");
        else {
            txtThongTinChiTiet.setText(Html.fromHtml(nutrition.getInfo()));

        }


    }


    void setAdapterExerciseInvolve(ArrayList<Nutrition> dataset) {






        NutitionAdapter nutitionAdapter;
        nutitionAdapter = new NutitionAdapter(this, R.layout.custom_layout_exercise, dataset);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        
        
        recylerExerciseInvolve.setLayoutManager(layoutManager);
        recylerExerciseInvolve.setAdapter(nutitionAdapter);
        nutitionAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }




    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
        }
    }


}

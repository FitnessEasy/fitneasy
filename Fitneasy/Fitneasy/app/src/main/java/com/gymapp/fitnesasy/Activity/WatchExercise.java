package com.gymapp.fitnesasy.Activity;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.gymapp.fitnesasy.Adapter.ViewPagerSliderAdapter;
import com.gymapp.fitnesasy.DataManager.ExerciseDetailDataManager;
import com.gymapp.fitnesasy.DataManager.OnLoadDataComplete;
import com.gymapp.fitnesasy.Fragment.FragmentSliderWatchExercise;
import com.gymapp.fitnesasy.Model.DTO.ExerciseDetail;
import com.gymapp.fitnesasy.R;
import com.gymapp.fitnesasy.ToolManual.MyTimeRunAndBreak;

import java.util.ArrayList;
import java.util.List;



public class WatchExercise extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, OnLoadDataComplete, MyTimeRunAndBreak.MyTimeRunAndBreakTimeEvent {

    TextView txtNameExerciseDetail, txtThongTinChiTiet;
    Toolbar toolbar;
    ImageView imXemThemChiTiet;
    boolean kiemtraxochitiet = false;
    ProgressBar progressBar;
    ViewPager viewPager;
    ImageView imImageExerciseDetail;
    TextView[] txtDots;
    LinearLayout layoutDots;
    List<Fragment> fragmentList = new ArrayList<>();
    ExerciseDetailDataManager exerciseDetailDataManager;
    List<ExerciseDetail> exerciseDetailList = new ArrayList<>();
    String exerciseId;
    TextView nullInfo;
    private Button buttonStartTime;

    private MyTimeRunAndBreak exerciseTimer;

    

    private void setEnableButtonStartTime(boolean enable) {
        buttonStartTime.setEnabled(enable);
        if(enable)
        {
            buttonStartTime.setBackgroundResource(R.drawable.ic_button_timer_on);
           
        }
        else
        {
            buttonStartTime.setBackgroundResource(R.drawable.ic_button_timer_off);
           
        }
    }

    private void setCurrentExercise(ExerciseDetail currentExercise) {

        if(exerciseTimer.isFinish()) {
            exerciseTimer.setExerciseDetail(currentExercise);
            buttonStartTime.setText(getTimeText(currentExercise.getTimeExercise()));
            this.currentExercise = currentExercise;
        }



    }

    ExerciseDetail currentExercise = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_exercise);
        txtNameExerciseDetail = (TextView) findViewById(R.id.txtNameExerciseDetail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txtThongTinChiTiet = (TextView) findViewById(R.id.txtThongTinChiTiet);
        imXemThemChiTiet = (ImageView) findViewById(R.id.imXemThemChiTiet);

        buttonStartTime = (Button) findViewById(R.id.buttonStartTime);
        buttonStartTime.setEnabled(true);
        
        imImageExerciseDetail = (ImageView) findViewById(R.id.imImageExerciseDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        exerciseId = getIntent().getStringExtra("id");
        
        


        exerciseDetailDataManager = new ExerciseDetailDataManager();
        exerciseDetailDataManager.setOnLoadDataComplete(this);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar_chi_tiet);

        viewPager = (ViewPager) findViewById(R.id.viewpagerSliderExercise);
        layoutDots = (LinearLayout) findViewById(R.id.layoutDots);

        exerciseDetailList = exerciseDetailDataManager.getDataset();


        exerciseTimer = new MyTimeRunAndBreak(this);
        
        


        exerciseTimer.setMyTimeRunAndBreakTimeEvent(this);

        HienSliderDetailExercise();

        setEventHandler();

    }

    private void setEventHandler() {
        buttonStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentExercise != null) {
                    exerciseTimer = new MyTimeRunAndBreak(WatchExercise.this);
                    exerciseTimer.setMyTimeRunAndBreakTimeEvent(WatchExercise.this);
                    exerciseTimer.setExerciseDetail(currentExercise);
                    exerciseTimer.start();
                    setEnableButtonStartTime(false);
                }

                



            }
        });
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

    ViewPagerSliderAdapter adapterViewPagerSlider;


    public void HienSliderDetailExercise() {
        

        
            

        

        adapterViewPagerSlider = new ViewPagerSliderAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapterViewPagerSlider);
        viewPager.setVisibility(View.VISIBLE);
        adapterViewPagerSlider.notifyDataSetChanged();


        viewPager.addOnPageChangeListener(this);
    }

    private void ThemDotSlider(int vitrihientai) {
        txtDots = new TextView[fragmentList.size()];

        layoutDots.removeAllViews();
        for (int i = 0; i < fragmentList.size(); i++) {
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActive));

            layoutDots.addView(txtDots[i]);
        }

        txtDots[vitrihientai].setTextColor(getIdColor(R.color.bgToolbar));
    }

    private String getTimeText(long second) {
        long hour = second/3600;
        second = second % 3600;
        long minute = second/60;
        second = second %60;
        String strh,strm,strsec;
        strh = "" +hour;
        strm = "" +minute;
        strsec = "" +second;
        if(strh.length()==1)
            strh="0"+strh;
        if(strm.length()==1)
            strm="0"+strm;
        if(strsec.length()==1)
            strsec="0"+strsec;

        return strh + ":" + strm + ":" + strsec;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        ExerciseDetail exerciseDetail = exerciseDetailDataManager.getDataset().get(position);
        setCurrentExercise(exerciseDetail);



        




    }

    @Override
    public void onPageSelected(int position) {
        
        ThemDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
        }
    }

    @Override
    public void onComplete() {

        ExerciseDetail exerciseDetail = exerciseDetailDataManager.getDataset().get(exerciseDetailDataManager.getDataset().size() - 1);

        if (exerciseId.equals(exerciseDetail.getExerciseId())) {
            FragmentSliderWatchExercise fragmentSliderChiTietSanPham = new FragmentSliderWatchExercise();
            Bundle bundle = new Bundle();
            bundle.putString("name", exerciseDetail.getName());
            bundle.putString("image", exerciseDetail.getImage());
            bundle.putString("videoID", exerciseDetail.getVideoID());
            bundle.putString("info", exerciseDetail.getInfo());
            fragmentSliderChiTietSanPham.setArguments(bundle);
            fragmentList.add(fragmentSliderChiTietSanPham);

            ThemDotSlider(0);
            adapterViewPagerSlider.notifyDataSetChanged();
        }

    }


    
    @Override
    public void onTimeExerciseFinish(int timeSetRemaining) {
        buttonStartTime.setBackgroundResource(R.drawable.ic_button_timer_break);




    }

    @Override
    public void onTimeBreakFinish() {
        buttonStartTime.setBackgroundResource(R.drawable.ic_button_timer_off);
    }

    @Override
    public void onAllTimeFinish() {
        buttonStartTime.setText("Tập lại!");
        setEnableButtonStartTime(true);

    }

    @Override
    public void onDelayTime(int timeRemaining) {
        buttonStartTime.setText(getTimeText(timeRemaining));
        

    }
}

package com.gymapp.fitnesasy.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gymapp.fitnesasy.Adapter.ListExerciseAdapter;
import com.gymapp.fitnesasy.Adapter.ScheduleAdapter;
import com.gymapp.fitnesasy.DataManager.ExerciseDataManager;
import com.gymapp.fitnesasy.DataManager.OnLoadDataComplete;
import com.gymapp.fitnesasy.DataManager.Schedule_ExerciseDataManager;
import com.gymapp.fitnesasy.Model.DTO.Schedule_Exercise;
import com.gymapp.fitnesasy.R;


public class Schedule_ExerciseFragment extends Fragment implements OnLoadDataComplete {


    private RecyclerView recylerSchedule;
    private RecyclerView recylerListExercise;
    private Button btnInsert;
    Schedule_ExerciseDataManager schedule_exerciseDataManager;
    ExerciseDataManager exerciseDataManager;
    ScheduleAdapter scheduleAdapter;
    ListExerciseAdapter listExerciseAdapter;
    private Button buttonTimKiem;
    private EditText textViewTimTheoTen;
    
    ImageView imloading;

    public Schedule_ExerciseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_schedule, container, false);

        this.textViewTimTheoTen = (EditText) view.findViewById(R.id.textViewTimTheoTen);
        this.buttonTimKiem = (Button) view.findViewById(R.id.buttonTimKiem);
        this.btnInsert = (Button) view.findViewById(R.id.btnInsert);

        this.recylerSchedule = (RecyclerView) view.findViewById(R.id.recylerSchedule);
        this.recylerListExercise = (RecyclerView) view.findViewById(R.id.recylerListExercise);
       
        schedule_exerciseDataManager = new Schedule_ExerciseDataManager();
        schedule_exerciseDataManager.setOnLoadDataComplete(this);

        Schedule_Exercise schedule = new Schedule_Exercise();
        schedule.setId("");
        schedule.setScheduleId("1");
        schedule.setExerciseId("1");
        schedule.setInfo("Chưa có thông tin");
        schedule.setTuan(1);

        schedule_exerciseDataManager.insert(schedule);














    


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

      
      

        return view;
    }























    @Override
    public void onComplete() {
       
       
    }


}

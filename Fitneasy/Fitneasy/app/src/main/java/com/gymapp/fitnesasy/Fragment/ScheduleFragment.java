package com.gymapp.fitnesasy.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.gymapp.fitnesasy.Adapter.ListExerciseAdapter;
import com.gymapp.fitnesasy.Adapter.ScheduleAdapter;
import com.gymapp.fitnesasy.DataManager.DataManager;
import com.gymapp.fitnesasy.DataManager.OnLoadDataComplete;
import com.gymapp.fitnesasy.DataManager.ScheduleDataManager;
import com.gymapp.fitnesasy.Model.DTO.Schedule;
import com.gymapp.fitnesasy.Model.DTO.Schedule_Exercise;
import com.gymapp.fitnesasy.R;

import java.util.ArrayList;



public class ScheduleFragment extends Fragment implements OnLoadDataComplete {

    
    private RecyclerView recylerSchedule;

    private Button btnInsert;
    

    DataManager<Schedule_Exercise> manager;
    ScheduleAdapter scheduleAdapter;
    ListExerciseAdapter listExerciseAdapter;

    ScheduleDataManager scheduleDataManager;
    

    
    ImageView imloading;

    
    public ScheduleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        

        this.btnInsert = (Button) view.findViewById(R.id.btnInsert);
        
        this.recylerSchedule = (RecyclerView) view.findViewById(R.id.recylerSchedule);

        

        scheduleDataManager = new ScheduleDataManager();
        scheduleDataManager.setOnLoadDataComplete(this);

       
        
        
        manager = new DataManager<>(Schedule_Exercise.class);


        setAdapterMuscleType(scheduleDataManager.getDataset());
        

        return view;
    }

    void setAdapterMuscleType(ArrayList<Schedule> dataset) {
        
        scheduleAdapter = new ScheduleAdapter(getContext(), R.layout.custom_layout_list_exercise, dataset);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);


        recylerSchedule.setLayoutManager(layoutManager);
        recylerSchedule.setAdapter(scheduleAdapter);
        scheduleAdapter.notifyDataSetChanged();
    }


    @Override
    public void onComplete() {
        
        
        scheduleAdapter.notifyDataSetChanged();
        
    }
}

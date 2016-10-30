package com.gymapp.fitnesasy.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gymapp.fitnesasy.Adapter.ListExerciseAdapter;
import com.gymapp.fitnesasy.DataManager.ExerciseDataManager;
import com.gymapp.fitnesasy.DataManager.OnLoadDataComplete;
import com.gymapp.fitnesasy.Model.DTO.Exercise;
import com.gymapp.fitnesasy.Model.DTO.Schedule_Exercise;
import com.gymapp.fitnesasy.R;

import java.util.ArrayList;

import static com.gymapp.fitnesasy.Fragment.MuscleTypeFragment.exerciseArrayList;
import static com.gymapp.fitnesasy.Fragment.MuscleTypeFragment.schedule_exerciseArrayList;



public class ListScheduleFragment extends Fragment implements OnLoadDataComplete {

    private RecyclerView recylerMuscleType;
    private Button btnInsert;
    RecyclerView[] recycleViewSchedule;
    Button btnThayDoiTrangThaiRecycler;

    boolean danggrid = true;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.LayoutManager[] layoutManagers;

    Toolbar toolbar;
    public String scheduleId;
    ListExerciseAdapter[] adapter;

    ExerciseDataManager exerciseDataManager;
    private android.widget.TextView txtInfoSchedule;

    ArrayList<Exercise>[] exercisess;
    public ListScheduleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_schedule, container, false);
        this.txtInfoSchedule = (TextView) view.findViewById(R.id.txtInfoSchedule);
        recycleViewSchedule = new RecyclerView[4];
        this.recycleViewSchedule[0] = (RecyclerView) view.findViewById(R.id.recycleViewSchedule0);
        this.recycleViewSchedule[1] = (RecyclerView) view.findViewById(R.id.recycleViewSchedule1);
        this.recycleViewSchedule[2] = (RecyclerView) view.findViewById(R.id.recycleViewSchedule2);
        this.recycleViewSchedule[3] = (RecyclerView) view.findViewById(R.id.recycleViewSchedule3);

        this.btnInsert = (Button) view.findViewById(R.id.btnInsert);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
       
        Bundle bundle = getArguments();
        scheduleId = bundle.getString("scheduleId");
      
        String scheduleInfo = bundle.getString("scheduleInfo");
        String scheduleName = bundle.getString("scheduleName");




        


        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(!scheduleName.isEmpty())
            toolbar.setTitle(scheduleName);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("MainActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        exerciseDataManager = new ExerciseDataManager();
        setAdapter();

        if(!scheduleInfo.isEmpty()) {
            txtInfoSchedule.setText(scheduleName);
            txtInfoSchedule.setVisibility(View.GONE);
        }

        return view;
    }


    void setAdapter() {
        ArrayList<Schedule_Exercise> dataset = new ArrayList<>();

        

        for (Schedule_Exercise schedule_exercise : schedule_exerciseArrayList) {
            if (schedule_exercise.getScheduleId().contains(scheduleId)) {
                dataset.add(schedule_exercise);
            }
        }

        ArrayList<Exercise> exercises = new ArrayList<>();


        exercisess = new ArrayList[4];
        for (int i = 0; i <exercisess.length ; i++) {
            exercisess[i] = new ArrayList<>();

        }

        for(Schedule_Exercise schedule_exercise : dataset){

            for (Exercise exerciseItem : exerciseArrayList){
                if(schedule_exercise.getExerciseId().equals(exerciseItem.getId())){
                    if(!exercisess[schedule_exercise.getTuan()-1].contains(exerciseItem)){
                        exercisess[schedule_exercise.getTuan()-1].add(exerciseItem);
                    }
                }
            }
        }


        layoutManagers = new RecyclerView.LayoutManager[4];

        adapter = new ListExerciseAdapter[4];


        for (int i = 0; i <exercisess.length ; i++) {
            if(exercisess[i].size()>0){
                layoutManagers[i] = new LinearLayoutManager(getContext());
                adapter[i] = new ListExerciseAdapter(getContext(), R.layout.custom_layout_list_exercise, exercisess[i]);
                recycleViewSchedule[i].setLayoutManager(layoutManagers[i]);
                recycleViewSchedule[i].setAdapter(adapter[i]);
                adapter[i].notifyDataSetChanged();
            }
        }



    }

    @Override
    public void onComplete() {
        for (int i = 0; i <exercisess.length ; i++) {
            if(exercisess[i].size()>0)
                adapter[i].notifyDataSetChanged();

        }

    }
}


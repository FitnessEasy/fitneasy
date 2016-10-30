package com.gymapp.fitnesasy.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.gymapp.fitnesasy.Adapter.ListExerciseAdapter;
import com.gymapp.fitnesasy.Adapter.MuscleTypeAdapter;
import com.gymapp.fitnesasy.DataManager.ExerciseDataManager;
import com.gymapp.fitnesasy.DataManager.MuscleTypeDataManager;
import com.gymapp.fitnesasy.DataManager.OnLoadDataComplete;
import com.gymapp.fitnesasy.DataManager.Schedule_ExerciseDataManager;
import com.gymapp.fitnesasy.Model.DTO.Exercise;
import com.gymapp.fitnesasy.Model.DTO.MuscleType;
import com.gymapp.fitnesasy.Model.DTO.Schedule_Exercise;
import com.gymapp.fitnesasy.R;

import java.util.ArrayList;



public class MuscleTypeFragment extends Fragment implements OnLoadDataComplete {


    private RecyclerView recylerMuscleType;
    private android.widget.Button btnInsert;


    MuscleTypeAdapter muscleTypeAdapter;
    ListExerciseAdapter listExerciseAdapter;
    MuscleTypeDataManager muscleTypeDataManager;
    ExerciseDataManager exerciseDataManager;

    private Button buttonTimKiem;
    private android.widget.EditText textViewTimTheoTen;
    public static ArrayList<Exercise> exerciseArrayList;
    ImageView imloading;
    private RecyclerView recylerListExercise;
    public MuscleTypeFragment() {
    }
    Schedule_ExerciseDataManager schedule_exerciseDataManager;
    public static ArrayList<Schedule_Exercise> schedule_exerciseArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_muscle_type, container, false);
        this.recylerListExercise = (RecyclerView) view.findViewById(R.id.recylerListExercise);
        this.textViewTimTheoTen = (EditText) view.findViewById(R.id.textViewTimTheoTen);
        this.buttonTimKiem = (Button) view.findViewById(R.id.buttonTimKiem);
        this.btnInsert = (Button) view.findViewById(R.id.btnInsert);
        this.recylerMuscleType = (RecyclerView) view.findViewById(R.id.recylerMuscleType);
        

        muscleTypeDataManager = new MuscleTypeDataManager();
        muscleTypeDataManager.setOnLoadDataComplete(this);

        exerciseDataManager = new ExerciseDataManager();
        exerciseDataManager.setOnLoadDataComplete(this);
        exerciseArrayList = exerciseDataManager.getDataset();


        schedule_exerciseDataManager = new Schedule_ExerciseDataManager();
        schedule_exerciseDataManager.setOnLoadDataComplete(this);
        schedule_exerciseArrayList = schedule_exerciseDataManager.getDataset();

      













































        setAdapterMuscleType(muscleTypeDataManager.getDataset());
        setAdapterExercise(exerciseDataManager.getDataset());

        return view;
    }

    void setAdapterMuscleType(ArrayList<MuscleType> dataset) {
        
        muscleTypeAdapter = new MuscleTypeAdapter(getContext(), R.layout.custom_layout_muscle_type, dataset);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        
        recylerMuscleType.setLayoutManager(layoutManager);
        recylerMuscleType.setAdapter(muscleTypeAdapter);
        muscleTypeAdapter.notifyDataSetChanged();
        
    }

    void setAdapterExercise(ArrayList<Exercise> dataset) {
        
        listExerciseAdapter = new ListExerciseAdapter(getContext(), R.layout.custom_layout_exercise, dataset);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        
        
        recylerListExercise.setLayoutManager(layoutManager);
        recylerListExercise.setAdapter(listExerciseAdapter);
        listExerciseAdapter.notifyDataSetChanged();
    }

    @Override
    public void onComplete() {
        
        
        muscleTypeAdapter.notifyDataSetChanged();
        listExerciseAdapter.notifyDataSetChanged();
    }
}

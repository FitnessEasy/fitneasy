package com.gymapp.fitnesasy.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gymapp.fitnesasy.Adapter.ListExerciseAdapter;
import com.gymapp.fitnesasy.DataManager.ExerciseDataManager;
import com.gymapp.fitnesasy.DataManager.OnLoadDataComplete;
import com.gymapp.fitnesasy.Model.DTO.Exercise;
import com.gymapp.fitnesasy.R;

import java.util.ArrayList;

import static com.gymapp.fitnesasy.Fragment.MuscleTypeFragment.exerciseArrayList;



public class ListExerciseFragment extends Fragment implements OnLoadDataComplete {

    private RecyclerView recylerMuscleType;
    private android.widget.Button btnInsert;
    RecyclerView recyclerView;
    Button btnThayDoiTrangThaiRecycler;

    boolean danggrid = true;
    RecyclerView.LayoutManager layoutManager;

    Toolbar toolbar;
    public String muscleTypeID;
    ListExerciseAdapter adapter;

    ExerciseDataManager exerciseDataManager;


    public ListExerciseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_exercises, container, false);
        this.btnInsert = (Button) view.findViewById(R.id.btnInsert);
        this.recylerMuscleType = (RecyclerView) view.findViewById(R.id.recycleViewExercise);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        
        Bundle bundle = getArguments();
        muscleTypeID = bundle.getString("muscleTypeID");



        String muscleName = bundle.getString("muscleName");
        


        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setTitle(muscleName);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack("MainActivity", FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        });

        exerciseDataManager = new ExerciseDataManager();
        setAdapter();

        return view;
    }


    void setAdapter(){
        ArrayList<Exercise> dataset = new ArrayList<>();

        for(Exercise muscleType : exerciseArrayList){
            if(muscleType.getMuscleTypeID().contains(muscleTypeID)){
                dataset.add(muscleType);
            }
        }
        
        
        
        
        if(danggrid){
            layoutManager = new LinearLayoutManager(getContext());
            adapter = new ListExerciseAdapter(getContext(), R.layout.custom_layout_list_exercise,dataset);

        }else{

            layoutManager = new GridLayoutManager(getContext(),2);
            adapter =  new ListExerciseAdapter(getContext(),R.layout.custom_layout_muscle_type,dataset);


        }
        recylerMuscleType.setLayoutManager(layoutManager);
        recylerMuscleType.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onComplete() {
        adapter.notifyDataSetChanged();
    }
}


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

import com.gymapp.fitnesasy.Adapter.NutitionAdapter;
import com.gymapp.fitnesasy.DataManager.NutitionDataManager;
import com.gymapp.fitnesasy.DataManager.OnLoadDataComplete;
import com.gymapp.fitnesasy.Model.DTO.Nutrition;
import com.gymapp.fitnesasy.R;

import java.util.ArrayList;



public class NutitionFragment extends Fragment implements OnLoadDataComplete {


    private RecyclerView recylerMuscleType;
    private android.widget.Button btnInsert;
    RecyclerView recyclerView;
    Button btnThayDoiTrangThaiRecycler;

    boolean danggrid = true;
    RecyclerView.LayoutManager layoutManager;

    public String muscleTypeID;
    NutitionAdapter nutitionAdapter;

    NutitionDataManager nutitionDataManager;
    public static ArrayList<Nutrition> nutritionArrayList;

    public NutitionFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_nutition, container, false);
        this.btnInsert = (Button) view.findViewById(R.id.btnInsert);
        this.recylerMuscleType = (RecyclerView) view.findViewById(R.id.recycleViewExercise);

      
       



       
     


        nutitionDataManager = new NutitionDataManager();
        nutitionDataManager.setOnLoadDataComplete(this);
        setAdapter(nutitionDataManager.getDataset());
        nutritionArrayList = nutitionDataManager.getDataset();

        return view;
    }

    


    void setAdapter(ArrayList<Nutrition> dataset){

        if(danggrid){
            layoutManager = new LinearLayoutManager(getContext());
            nutitionAdapter = new NutitionAdapter(getContext(), R.layout.custom_layout_list_exercise,dataset);

        }else{

            layoutManager = new GridLayoutManager(getContext(),2);
            nutitionAdapter =  new NutitionAdapter(getContext(),R.layout.custom_layout_muscle_type,dataset);


        }
        recylerMuscleType.setLayoutManager(layoutManager);
        recylerMuscleType.setAdapter(nutitionAdapter);
        nutitionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onComplete() {
        nutitionAdapter.notifyDataSetChanged();
    }
}

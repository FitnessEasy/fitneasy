package com.gymapp.fitnesasy.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gymapp.fitnesasy.Activity.Youtube_Activity;
import com.gymapp.fitnesasy.R;
import com.squareup.picasso.Picasso;



public class FragmentSliderWatchExercise extends Fragment {


    private ImageView imHinhSlider;
    private android.widget.TextView txtNameDetailExercise;
    private android.widget.TextView txtInfoDetailExercise;
    private ImageView imXemThemChiTiet;
    String videoID;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_fragment_slider_watch_exercise, container, false);
        this.imXemThemChiTiet = (ImageView) view.findViewById(R.id.imXemThemChiTiet);
        this.txtInfoDetailExercise = (TextView) view.findViewById(R.id.txtInfoDetailExercise);
        this.txtNameDetailExercise = (TextView) view.findViewById(R.id.txtNameDetailExercise);
        this.imHinhSlider = (ImageView) view.findViewById(R.id.imHinhSlider);
        Button btnWatchVideo=(Button) view.findViewById(R.id.btnWatchVideo);;
        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        String image = bundle.getString("image");
        videoID = bundle.getString("videoID");
        String info = bundle.getString("info");



        if(!name.isEmpty())
            txtNameDetailExercise.setText(name);
        if(!info.isEmpty())
            txtInfoDetailExercise.setText(Html.fromHtml(info));

        if(!image.isEmpty())

            Picasso.with(getContext()).load(image).into(imHinhSlider);

        btnWatchVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Youtube_Activity.class);
                intent.putExtra("videoId",videoID);
                getActivity().startActivity(intent);

            }
        });


        return view;
    }
}


package com.gymapp.fitnesasy.ToolManual;

import android.content.Context;

import com.gymapp.fitnesasy.Model.DTO.ExerciseDetail;



public class MyTimeRunAndBreak {

    public static interface MyTimeRunAndBreakTimeEvent {
        public void onTimeExerciseFinish(int setRemaining);

        public void onTimeBreakFinish();

        public void onAllTimeFinish();

        public void onDelayTime(int timeRemaining);
    }

    public void setMyTimeRunAndBreakTimeEvent(MyTimeRunAndBreakTimeEvent myTimeRunAndBreakTimeEvent) {
        this.myTimeRunAndBreakTimeEvent = myTimeRunAndBreakTimeEvent;
    }
    MyTimerCountAsyncTask.TimerEvent exerciseTimeEvent;
    MyTimerCountAsyncTask.TimerEvent breakTimeEvent;

    MyTimeRunAndBreakTimeEvent myTimeRunAndBreakTimeEvent;

    private Context context;

    public void setExerciseDetail(ExerciseDetail exerciseDetail) {
        this.exerciseDetail = exerciseDetail;
    }

    private ExerciseDetail exerciseDetail;


    MyTimerCountAsyncTask myExerciseTimer;
    MyTimerCountAsyncTask myBreakTimer;

    int timeSetRemaining;

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    public boolean isFinish() {
        return isFinish;
    }

    boolean isFinish;


    public MyTimeRunAndBreak(Context context) {
        this.context = context;
        myExerciseTimer = new MyTimerCountAsyncTask(context);
        myBreakTimer = new MyTimerCountAsyncTask(context);
        isFinish = true;
        setEvent();
    }



    void setEvent() {
        exerciseTimeEvent = new MyTimerCountAsyncTask.TimerEvent() {
            @Override
            public void onFinish() {
                timeSetRemaining--;
                if (timeSetRemaining == 0) {
                    isFinish=true;
                    if (myTimeRunAndBreakTimeEvent != null) {
                        myTimeRunAndBreakTimeEvent.onAllTimeFinish();
                    }
                } else {
                    if (myTimeRunAndBreakTimeEvent != null) {
                        myTimeRunAndBreakTimeEvent.onTimeExerciseFinish(timeSetRemaining);
                    }
                    myBreakTimer = new MyTimerCountAsyncTask(context);
                    myBreakTimer.setTimerEvent(breakTimeEvent);
                    myBreakTimer.start((int) exerciseDetail.getTimeBreak());
                }
            }

            @Override
            public void onDelayTime(int timeRemaining) {
                myTimeRunAndBreakTimeEvent.onDelayTime(timeRemaining);
            }
        };


        breakTimeEvent = new MyTimerCountAsyncTask.TimerEvent() {
            @Override
            public void onFinish() {
                if(myTimeRunAndBreakTimeEvent!=null){
                    myTimeRunAndBreakTimeEvent.onTimeBreakFinish();
                }
                myExerciseTimer = new MyTimerCountAsyncTask(context);
                myExerciseTimer.setTimerEvent(exerciseTimeEvent);
                myExerciseTimer.start((int) exerciseDetail.getTimeExercise());
            }

            @Override
            public void onDelayTime(int timeRemaining) {
                myTimeRunAndBreakTimeEvent.onDelayTime(timeRemaining);
            }
        };

        myExerciseTimer.setTimerEvent(exerciseTimeEvent);
        myBreakTimer.setTimerEvent(breakTimeEvent);

    }

    public boolean start() {
        if (!isFinish)
            return false;
        if(exerciseDetail==null)
            return false;

        isFinish=false;
        timeSetRemaining = (int) exerciseDetail.getSet();
        myExerciseTimer.start((int) exerciseDetail.getTimeExercise());
        return true;
    }


}

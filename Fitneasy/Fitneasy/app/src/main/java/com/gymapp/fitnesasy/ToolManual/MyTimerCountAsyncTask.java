package com.gymapp.fitnesasy.ToolManual;

import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;



public class MyTimerCountAsyncTask extends AsyncTask<Integer,Integer,Void>
{
    public interface TimerEvent {
        public void onFinish();

        public void onDelayTime(int timeRemaining);

    }

    public void setTimerEvent(TimerEvent timerEvent) {
        this.timerEvent = timerEvent;
    }

    public void start(int deltaTime){
        finished=false;
        execute(deltaTime);
    }

    TimerEvent timerEvent;

    Context context;

    public boolean isFinished() {
        return finished;
    }

    boolean finished;

    public MyTimerCountAsyncTask(Context context) {
        finished=true;
        this.context = context;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        int timeRemainingCount = params[0];
        for(int i=timeRemainingCount;i>0;i--) {
            publishProgress(i);
            SystemClock.sleep(1000);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if(timerEvent!=null)
            timerEvent.onFinish();
        finished=true;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int timeShow = values[0];

        if(timerEvent!=null)
            timerEvent.onDelayTime(timeShow);

    }
}

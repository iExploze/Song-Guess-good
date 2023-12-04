package interface_adapter.timer;

import usecase.timer.TimeInputBoundary;
import usecase.timer.TimeInputData;

public class TimerController {
    private boolean paused;
    private final TimeInputBoundary timeInputBoundary;
    private final TimeInputData timeInputData;
    public TimerController(TimeInputBoundary timeInputBoundary, TimeInputData timeInputData)
    {
        this.paused = true;
        this.timeInputBoundary = timeInputBoundary;
        this.timeInputData = timeInputData;
    }

    public void startTimer()
    {
        this.paused = false;
        this.timeInputBoundary.startTimer();
    }

    public void updateTimerState()
    {// basically update the state of the timer being paused or unpause
        if(this.paused)
        {
            timeInputBoundary.unPauseTimer();
            this.paused = false;
        }
        else
        {
            timeInputBoundary.pauseTimer();
            this.paused = true;
        }
    }

    public void setTimer(int time)
    {
        this.timeInputData.setTimeInput(time);
        this.timeInputBoundary.setTimer();
    }
}

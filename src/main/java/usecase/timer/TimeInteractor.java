package usecase.timer;

import entities.Quiz;

import java.util.Timer;
import java.util.TimerTask;

public class TimeInteractor implements TimeInputBoundary {
    private final Quiz quiz;
    private final TimeOutputBoundary timeOutputBoundary;
    private final TimeOutputData timeOutputData;
    private Timer timer;

    private final TimeInputData timeInputData;

    public TimeInteractor(Quiz quiz, TimeOutputBoundary timeOutputBoundary, TimeInputData timeInputData, TimeOutputData timeOutputData) {
        this.quiz = quiz;
        this.timeInputData = timeInputData;
        this.timeOutputBoundary = timeOutputBoundary;
        this.timeOutputData = timeOutputData;
    }

    @Override
    public void startTimer() {
        if (timer != null) {
            timer.cancel(); // Ensure any existing timer is cancelled before starting a new one
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                quiz.decreaseTime();
                timeOutputData.setRemainingTime((int)quiz.getTimeLeft());
                timeOutputBoundary.displayTime(timeOutputData.getRemainingTime());
                // Check if the time has run out and stop the timer if it has
                if (quiz.getTimeLeft() <= 0) {
                    timer.cancel();
                    // Optionally, handle the case when time runs out (e.g., notify about quiz end)
                }
            }
        }, 0, 1000); // Schedule the task to run every 1000 milliseconds (1 second)
    }

    @Override
    public void setTimer() {
        pauseTimer(); // First, pause the current timer
        this.quiz.setTime(this.timeInputData.getTimeInput()); // Set the new time
        this.timeOutputData.setRemainingTime((int)this.quiz.getTimeLeft());
        this.timeOutputBoundary.displayTime(timeOutputData.getRemainingTime()); // Update the displayed time immediately
    }

    @Override
    public void pauseTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null; // Discard the old Timer object to avoid a cancelled Timer being reused
        }
    }

    @Override
    public void unPauseTimer() {
        startTimer(); // Simply call startTimer again to unpause
    }
}

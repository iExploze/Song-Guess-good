package interface_adapter.timer;

import interface_adapter.PlayViewModel;
import usecase.timer.TimeOutputBoundary;

public class TimerPresenter implements TimeOutputBoundary {

    private final PlayViewModel playViewModel;

    public TimerPresenter(PlayViewModel playViewModel) {
        this.playViewModel = playViewModel;
    }

    @Override
    public void displayTime(int time) {
        playViewModel.setTime(time); // Assuming there is a setTime method in PlayViewModel
        playViewModel.fireTimerChanged(); // Notify observers about the change
    }
}

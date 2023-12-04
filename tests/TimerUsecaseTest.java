import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import dataAccessObjects.UserStorage.InMemoryUserDataAccessObject;
import dataAccessObjects.UserStorage.UserDataAccessObject;
import entities.Users.CommonUserFactory;
import entities.*;
import entities.Users.User;
import entities.Users.UserFactory;
import interface_adapter.PlayViewModel;
import interface_adapter.timer.TimerPresenter;
import org.junit.Before;
import org.junit.Test;
import usecase.timer.*;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TimerUsecaseTest {

    private TimeInputBoundary timeInputBoundary;
    private TimeOutputBoundary timeOutputBoundary;
    private TimeInputData timeInputData;
    private TimeOutputData timeOutputData;
    private Player player;

    private User user;

    @Before
    public void init() throws IOException {
        CommonUserFactory commonUserFactory = new CommonUserFactory();
        this.user = commonUserFactory.createUser("a","b");
        this.player = new SinglePlayer(user);
        this.timeInputData = new TimeInputData();
        this.timeOutputData = new TimeOutputData();
        PlayViewModel playViewModel = new PlayViewModel();
        this.timeOutputBoundary = new TimerPresenter(playViewModel);
    }

    @Test
    public void testSetTime() throws IOException {
        this.timeOutputData = new TimeOutputData();
        this.timeInputBoundary = new TimeInteractor(new PlaylistQuiz(player), this.timeOutputBoundary, this.timeInputData, this.timeOutputData);

        this.timeInputData.setTimeInput(120);
        this.timeInputBoundary.setTimer();
        assert timeOutputData.getRemainingTime() == 120;
    }

    @Test
    public void testStartTimer() throws IOException {
        this.timeOutputData = new TimeOutputData();
        this.timeInputBoundary = new TimeInteractor(new PlaylistQuiz(player), this.timeOutputBoundary, this.timeInputData, this.timeOutputData);

        this.timeInputData.setTimeInput(120);
        this.timeInputBoundary.setTimer();
        this.timeInputBoundary.startTimer();
        try {
            // Pause for 5 seconds (5000 milliseconds)
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Handle the interruption exception
            e.printStackTrace();
        }
        assert timeOutputData.getRemainingTime() <= 118 || timeOutputData.getRemainingTime() >= 116;
    }

    @Test
    public void testPauseTimer() throws IOException {
        this.timeOutputData = new TimeOutputData();
        this.timeInputBoundary = new TimeInteractor(new PlaylistQuiz(player), this.timeOutputBoundary, this.timeInputData, this.timeOutputData);

        this.timeInputData.setTimeInput(120);
        this.timeInputBoundary.setTimer();
        this.timeInputBoundary.startTimer();
        try {
            // Pause for 5 seconds (5000 milliseconds)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Handle the interruption exception
            e.printStackTrace();
        }
        this.timeInputBoundary.pauseTimer();
        assert timeOutputData.getRemainingTime() <= 118 || timeOutputData.getRemainingTime() >= 116;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert timeOutputData.getRemainingTime() <= 118 || timeOutputData.getRemainingTime() >= 116;
    }

    @Test
    public void testUnpauseTimer() throws IOException {
        this.timeOutputData = new TimeOutputData();
        this.timeInputBoundary = new TimeInteractor(new PlaylistQuiz(player), this.timeOutputBoundary, this.timeInputData, this.timeOutputData);

        this.timeInputData.setTimeInput(120);
        this.timeInputBoundary.setTimer();
        this.timeInputBoundary.startTimer();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert timeOutputData.getRemainingTime() <= 118 || timeOutputData.getRemainingTime() >= 116;
        this.timeInputBoundary.pauseTimer();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert timeOutputData.getRemainingTime() <= 118 || timeOutputData.getRemainingTime() >= 116;
        this.timeInputBoundary.unPauseTimer();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert timeOutputData.getRemainingTime() <= 118-3 || timeOutputData.getRemainingTime() >= 116-3;
    }
}

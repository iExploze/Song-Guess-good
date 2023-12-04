package interface_adapter.guess;

import interface_adapter.signup.SignupController;
import org.junit.Test;
import usecase.SignUp.SignUpInputBoundary;
import usecase.SignUp.SignUpInputData;
import usecase.guess.GuessInputBoundary;
import usecase.guess.GuessInputData;

import java.io.IOException;

import static org.junit.Assert.*;

public class GuessControllerTest {
    @Test
    public void successTest(){
        GuessInputBoundary successInteractor = new GuessInputBoundary() {
            @Override
            public void execute(GuessInputData guessInputData) {
                assertEquals("guess", guessInputData.getGuess());
            }
        };

        GuessController guessController = new GuessController(successInteractor);
        guessController.execute("guess");
    }
}

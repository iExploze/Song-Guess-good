package interface_adapter.login;

import interface_adapter.login.LoginController;
import usecase.Login.*;

import org.junit.Test;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginControllerTest {

    @Test
    public void successTest() throws IOException {
        // test the controller pass on the input data when executed
        LoginInputBoundary successInteractor = new LoginInputBoundary() {
            @Override
            public void execute(LoginInputData loginInputData) throws IOException {
                assertEquals("Flora", loginInputData.getUsername());
                assertEquals("123", loginInputData.getPassword());
            }

            @Override
            public void switchSignUp() {
            }
        };

        LoginController loginController = new LoginController(successInteractor);
        loginController.execute("Flora", "123");
    }

}


package usecase.SignUp;

import dataAccessObjects.UserStorage.UserDataAccessObject;

import dataAccessObjects.spotifyAccessObjects.*;
import entities.Users.User;
import entities.Users.UserFactory;

import java.io.IOException;

public class SignUpInteractor implements SignUpInputBoundary {

    // we want this to pass to check the user details, see if it infringes on anything and if not create the user using
    // input data and saving it to our SignUpFactory database?
    UserFactory userFactory;
    UserDataAccessObject userDataAccessObject;
    SignUpOutputBoundary userPresenter;
    UserAuthenticationBuilder userAuthenticationBuilder;

    public SignUpInteractor(UserFactory userFactory, UserDataAccessObject userDataAccessObject,
                            SignUpOutputBoundary userPresenter) {
        this.userFactory = userFactory;
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }
    public void execute(SignUpInputData signUpInputData) throws IOException {
        String username = signUpInputData.getUsername();
        String password = signUpInputData.getPassword();
        String confirmPassword = signUpInputData.getConfirmPassword();

        if (userDataAccessObject.exists(username)) {
            userPresenter.prepareFailView("Username already exists!");
        }
        else if (!password.equals(confirmPassword)) {
            userPresenter.prepareFailView("Password does not match!");
        }
        else {
            User user = userFactory.createUser(username, password);

            UserTopTracks userTopTracksObject = new UserTopTracksObject(); // Here we don't actually care about top tracks
            // but it still does authentication for the user
            userTopTracksObject.getTopTracks(user);
            userDataAccessObject.save(user);

            SignUpOutputData signUpOutputData = new SignUpOutputData(username);
            userPresenter.prepareSuccessView(signUpOutputData);
        }

    }
}

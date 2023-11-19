package usecase.SignUp;

public class SignUpOutputData {
    // so far only give back username, can add more later for this view
    String username;


    public SignUpOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

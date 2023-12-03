package usecase.Login;

import entities.Users.User;

import java.util.List;

public class LoginOutputData {

    private final User user;
    private List suggestions;
    private String song;
    private boolean useCaseFailed;

    public LoginOutputData(User user, List suggestions, String strings, boolean useCaseFailed) {
        this.user = user;
        this.suggestions = suggestions;
        this.song = strings;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUser() {
        return user.getUsername();
    }

    public List getSuggestions() {
        return suggestions;
    }

    public String getSong() {
        return song;
    }
}

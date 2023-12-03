package usecase.SignUp;

import entities.Users.User;

import java.util.List;

public class SignUpOutputData {
    // so far only give back username, can add more later for this view
    User user;
    List suggestions;
    String song;


    public SignUpOutputData(User user, List suggestions, String song) {
        this.user = user;
        this.suggestions = suggestions;
        this.song = song;
    }

    public User getUsername() {
        return this.user;
    }

    public List getSuggestions() {
        return suggestions;
    }

    public String getSong() {
        return song;
    }
}

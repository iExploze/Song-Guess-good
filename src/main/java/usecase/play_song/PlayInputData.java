package usecase.play_song;

public class PlayInputData {
    final private String title;

    public PlayInputData(String title) {
        this.title = title;
    }

    String getTitle() { return title; }
}

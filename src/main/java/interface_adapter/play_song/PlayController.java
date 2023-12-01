package interface_adapter.play_song;

import usecase.play_song.PlayInputBoundary;

public class PlayController {

    private final PlayInputBoundary playInputBoundary;
    public PlayController(PlayInputBoundary playInputBoundary)
    {
        this.playInputBoundary = playInputBoundary;
    }
    public void PlaySong()
    {
        this.playInputBoundary.PlaySong();
    }

    public void StopSong()
    {
        this.playInputBoundary.StopSong();
    }
}

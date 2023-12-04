package usecase.guess;

import entities.Quiz;
import entities.Song;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GuessInteractorTest {

    private GuessOutputBoundary mockGuessPresenter;
    private Quiz mockQuiz;
    private GuessInputBoundary guessInteractor;

    @Before
    public void init() {
        this.mockGuessPresenter = Mockito.mock(GuessOutputBoundary.class);
        this.mockQuiz = mock(Quiz.class);
        this.guessInteractor = new GuessInteractor(mockGuessPresenter, mockQuiz);
    }

    @Test
    public void successTest() {
        GuessInputData inputData = new GuessInputData("song");
        Song mockedSong = mock(Song.class);
        when(mockQuiz.currentPlaying()).thenReturn(mockedSong);
        when(mockedSong.getSongName()).thenReturn("song");

        guessInteractor.execute(inputData);

        verify(mockQuiz, times(1)).addPoints();
        verify(mockGuessPresenter, times(1)).prepareSuccessView(any());
    }

    @Test
    public void failTest() {
        GuessInputData inputData = new GuessInputData("wrong guess");
        Song mockedSong = mock(Song.class);
        when(mockedSong.getSongName()).thenReturn("song");
        when(mockedSong.getURL()).thenReturn("url");
        when(mockQuiz.currentPlaying()).thenReturn(mockedSong);

        guessInteractor.execute(inputData);

        verify(mockGuessPresenter, times(1)).prepareFailView(anyString(), anyString(), anyInt());
    }
}

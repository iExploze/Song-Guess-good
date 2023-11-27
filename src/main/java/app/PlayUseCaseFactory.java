//package app;
//
//import entities.Song;
//import interface_adapter.ViewManagerModel;
//import interface_adapter.guess.GuessController;
//import interface_adapter.guess.GuessPresenter;
//import interface_adapter.play_song.PlayController;
//import interface_adapter.play_song.PlayPresenter;
//import interface_adapter.play_song.PlayViewModel;
//import usecase.guess.GuessInputBoundary;
//import usecase.guess.GuessInteractor;
//import usecase.guess.GuessOutputBoundary;
//import usecase.play_song.PlayOutputBoundary;
//import view.PlayView;
//
//import javax.swing.*;
//import java.io.IOException;
//
//public class PlayUseCaseFactory {
//    private PlayUseCaseFactory(){}
//
//    public static PlayView create(ViewManagerModel viewManagerModel,
//                                  PlayViewModel playViewModel,
//                                  Song song){
//
//        try{
//            GuessController guessController = createGuessUseCase(viewManagerModel, playViewModel, song);
//            //return new PlayView(playViewModel, playController, guessController);
//        } catch (IOException e){
//            JOptionPane.showMessageDialog(null, "Error");
//        }
//    }
//
//    public static GuessController createGuessUseCase(ViewManagerModel viewManagerModel, PlayViewModel playViewModel, Song song) throws IOException{
//        GuessOutputBoundary guessOutputBoundary = new GuessPresenter(viewManagerModel, playViewModel);
//
//        GuessInputBoundary guessInteractor = new GuessInteractor(guessOutputBoundary, song);
//
//        return new GuessController(guessInteractor);
//    }
//}

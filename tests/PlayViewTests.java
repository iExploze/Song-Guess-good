import app.LoginUseCaseFactory;
import app.SignupUseCaseFactory;
import view.TextFieldSuggestion;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import dataAccessObjects.UserStorage.FileUserDataAccessObject;
import entities.*;
import entities.Users.CommonUserFactory;
import entities.Users.User;
import interface_adapter.PlayState;
import interface_adapter.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.guess.GuessController;
import interface_adapter.guess.GuessPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import usecase.guess.GuessInputBoundary;
import usecase.guess.GuessInteractor;
import usecase.guess.GuessOutputBoundary;
import view.LoginView;
import view.PlayView;
import view.SignupView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PlayViewTests {
    // Interesting tests are not ones where I check if certain component exists.
    PlayView PV;
    PlayViewModel playViewModel;
    PlayState playState;
    Robot robot;
    JFrame application;
    private static List<HashMap<String, String>> convertStringToMap(String songs) {
        String[] mapStrings = songs.substring(1, songs.length() - 1).split("},\\s*\\{");

        // Initialize a list to store maps
        List<HashMap<String, String>> list = new ArrayList<>();

        // Process each map string
        for (String mapString : mapStrings) {
            // Initialize a map for each entry
            HashMap<String, String> map = new HashMap<>();

            // Split each entry into key-value pairs
            StringTokenizer tokenizer = new StringTokenizer(mapString, ", ");
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                int equalsIndex = token.indexOf("=");
                if (equalsIndex != -1) {
                    String key = token.substring(0, equalsIndex);
                    String value = token.substring(equalsIndex + 1);
                    map.put(key, value);
                }
            }

            // Add the map to the list
            list.add(map);
        }

        // Display the result
        return list;
    }
    public static java.util.List<HashMap<String, String>> topTracksResponseToNamePreviewUrl(String s) {
        Gson gson = new Gson();
        HashMap allData = gson.fromJson(s, HashMap.class);

        List<HashMap<String, String>> res = new ArrayList<>();
        for (Object SongData : (ArrayList) allData.get("items")) {
            LinkedTreeMap SongData1 = (LinkedTreeMap) SongData;
            HashMap<String, String> resultData = new HashMap();
            resultData.put("name", (String) SongData1.get("name"));
            if (SongData1.get("preview_url") == null) {
                continue;
            }
            resultData.put("preview_url", (String) SongData1.get("preview_url"));
            res.add(resultData);


        }
        return res;
    }
    @Before
    public void init() throws IOException {
        //Build model + get componenets I need
        application = new JFrame("Song Playback Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);


        CommonUserFactory commonUserFactory= new CommonUserFactory();
        User user = commonUserFactory.createUser("a","b");

        Player player = new SinglePlayer(user);
        Quiz quiz = new PlaylistQuiz(player);
        String songs = "[{preview_url=https://p.scdn.co/mp3-preview/ffa9d53ff1f83a322ac0523d7a6ce13b231e4a3a?cid=ee5a4dc5c931462e9e630c64a8aee5ac, name=STAR WALKIN' (League of Legends Worlds Anthem)}, " +
                "{preview_url=https://p.scdn.co/mp3-preview/644d4ce6d4a3afce512d54904ce5872ccfb94493?cid=ee5a4dc5c931462e9e630c64a8aee5ac, name=Legends Never Die}, " +
                "{preview_url=https://p.scdn.co/mp3-preview/d471032057f6ed603eab70d1bc7f93a472ff59a0?cid=ee5a4dc5c931462e9e630c64a8aee5ac, name=RISE}, " +
                "{preview_url=https://p.scdn.co/mp3-preview/18b3cbbad11e488c24c76d0c697cec8618c15f96?cid=ee5a4dc5c931462e9e630c64a8aee5ac, name=lovely (with Khalid)}, " +
                "{preview_url=https://p.scdn.co/mp3-preview/22c354ac8d2a9354509ecb517a26fc34069600c4?cid=ee5a4dc5c931462e9e630c64a8aee5ac, name=hot girl bummer}, " +
                "{preview_url=https://p.scdn.co/mp3-preview/619f4e362cdb72a2237b6c271178f02cc09d813d?cid=ee5a4dc5c931462e9e630c64a8aee5ac, name=Phoenix}, " +
                "{preview_url=https://p.scdn.co/mp3-preview/4aae7af14e3ffb58ab0499099fc8c9a6936152fe?cid=ee5a4dc5c931462e9e630c64a8aee5ac, name=@ my worst}, " +
                "{preview_url=https://p.scdn.co/mp3-preview/f6958f897e3817fcbb6647d62f66c71ee96d1981?cid=ee5a4dc5c931462e9e630c64a8aee5ac, name=THATS WHAT I WANT}]";

        SpotifyPlaylist spotifyPlaylist = new SpotifyPlaylist(convertStringToMap(songs));
        quiz.setQuiz(spotifyPlaylist);

        playViewModel = new PlayViewModel();

        GuessOutputBoundary guessOutputBoundary = new GuessPresenter(viewManagerModel, playViewModel);
        GuessInputBoundary guessInputBoundary = new GuessInteractor(guessOutputBoundary, quiz);
        GuessController guessController = new GuessController(guessInputBoundary);


        // Pass the timerController to the PlayView
        PV = new PlayView(playViewModel, guessController);
        playState = playViewModel.getState();




        LoginViewModel loginViewModel = new LoginViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }


        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, playViewModel, quiz);



        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, userDataAccessObject, playViewModel, signupViewModel, quiz);



        views.add(PV, PV.viewName);
        // Here, the viewName is a public static final String field in the PlayView class
        views.add(loginView, loginView.viewName);

        views.add(signupView, signupView.viewName);
        //viewManagerModel.setActiveView(UAuthView.viewName);
        viewManagerModel.setActiveView(PV.viewName);

        viewManagerModel.firePropertyChanged();

        application.pack();
        // Set the size of the window
        application.setLocationRelativeTo(null); // Center the window
        application.setVisible(true);
    }


//    public void getPlayView() {
//        //get the score label
//        Frame app = null;
//        Window[] windows = Window.getWindows();
//        for (Window window : windows) {
//            if (window instanceof JFrame) {
//                app = (JFrame) window;
//            }
//        }
//
//        assertNotNull(app); // found the window?
//
//        Component root = app.getComponent(0); // this should be the views componenet
//
//        Component cp = ((JRootPane) root).getContentPane();
//
//        JPanel jp = (JPanel) cp;
//        JPanel jp2 = (JPanel) jp.getComponent(0);
//        PV = (PlayView) jp2.getComponent(0); //first component of panel should be the playview.
//    }

    public JLabel getScore() {
        JPanel jp3 = (JPanel) PV.getComponent(0); //0th component is the scorepanel, 1st is guess, 2nd is progressbar

        JLabel scoreLabel = (JLabel) jp3.getComponent(0); // should be score label

        return scoreLabel;
    }
    public TextFieldSuggestion getTextField() {
        JPanel jp3 = (JPanel) PV.getComponent(1); //Guess info
        TextFieldSuggestion textField = (TextFieldSuggestion) jp3.getComponent(1); // should be  TextField
        return textField;
    }
    public JProgressBar getTimer() {
        JProgressBar timer = (JProgressBar) PV.getComponent(2); //Guess info
        return timer;

    }
    //Score updates
    @Test @SuppressWarnings({"null"})
    public void correctScoreInitilizationandIncrement() {
        JLabel label = getScore();
        assert(label.getText().equals("Score: 0"));
        playState.setScore(playState.getScore() + 1);
        playViewModel.setState(playState);
        playViewModel.firePropertyChanged();
        assert(label.getText().equals("Score: 1"));

    }
    // Suggestions Update
    @Test
    public void textFieldWorking() throws AWTException {
        robot = new Robot();
         // Delay for 1 second (adjust as needed)

        // Set focus on the JTextField
        // Simulate pressing and releasing the Enter keya

        TextFieldSuggestion textField = getTextField();
        System.out.println(textField.isVisible());
        textField.requestFocus();
        robot.delay(1000);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(1000);
        assertEquals(textField.getText(), "a");

    }
    @Test
    public void suggestionsUpdate() throws AWTException {
        robot = new Robot();
        // Delay for 1 second (adjust as needed)

        // Set focus on the JTextField
        // Simulate pressing and releasing the Enter keya

        TextFieldSuggestion textField = getTextField();
        System.out.println(textField.isVisible());
        textField.requestFocus();
        ArrayList<String> rs = new ArrayList<>();
        rs.add("Apple");
        rs.add("Banana");
        playState.setSuggestions(rs);
        playViewModel.setSuggestions(playState);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(3000);
        assertEquals(textField.getText(), "Apple");
    }


    // Timer updates
    @Test
    public void TimerCounts() throws AWTException {
        robot = new Robot();
        JProgressBar timer = getTimer();
        // Delay for 1 second (adjust as needed)

        // Set focus on the JTextField
        // Simulate pressing and releasing the Enter keya
        robot.delay(5000);
        assertEquals(5, timer.getValue());

    }
    @Test
    public void TimerUpdates() throws AWTException {
        robot = new Robot();
        JProgressBar timer = getTimer();
        // Delay for 1 second (adjust as needed)

        // Set focus on the JTextField
        // Simulate pressing and releasing the Enter keya
        robot.delay(5000);
        TextFieldSuggestion textField = getTextField();

        textField.requestFocus();
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(1000);
        assertEquals(0, timer.getValue());
    }

    // Song updates note bug because i never set the first song to play through login/signup interactors. However the second song does play.
    @Test
    public void SongUpdates() throws AWTException {
        robot = new Robot();
        // Delay for 1 second (adjust as needed)

        // Set focus on the JTextField
        // Simulate pressing and releasing the Enter keya
        robot.delay(1000);
        String firstSong = playState.getSong();
        System.out.println(firstSong);
        TextFieldSuggestion textField = getTextField();

        textField.requestFocus();
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(1000);
        String secondSong = playState.getSong();
        assertNotEquals(firstSong, secondSong);


    }
    @After
    public void cleanup() {
        application.dispose();
        robot.delay(3000);
    }
}

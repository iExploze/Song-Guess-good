package interface_adapter;

import interface_adapter.UAuthViewModel;
import interface_adapter.play_song.PlayViewModel;
import usecase.UserAuth.UAuthOutputBoundary;
import usecase.UserAuth.UAuthOutputData;

public class UAuthPresenter implements UAuthOutputBoundary {

    private PlayViewModel playViewModel;
    private UAuthViewModel uAuthViewModel;
    public String URL;
    public UAuthPresenter(UAuthOutputData uAuthOutputData, UAuthViewModel uAuthViewModel)
    {
        URL = uAuthOutputData.getURL();
        this.uAuthViewModel = uAuthViewModel;
        this.uAuthViewModel.setUrl(URL);
    }

    @Override
    public void prepareView() {

    }
}

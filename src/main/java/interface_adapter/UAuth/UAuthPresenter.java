package interface_adapter.UAuth;

import interface_adapter.play_song.PlayViewModel;
import usecase.UserAuth.UAuthOutputBoundary;
import usecase.UserAuth.UAuthOutputData;

public class UAuthPresenter implements UAuthOutputBoundary {

    private PlayViewModel playViewModel;
    private UAuthViewModel uAuthViewModel;

    private UAuthOutputData uAuthOutputData;
    public String URL;
    public UAuthPresenter(UAuthOutputData uAuthOutputData, UAuthViewModel uAuthViewModel)
    {
        this.uAuthOutputData = uAuthOutputData;
        this.uAuthViewModel = uAuthViewModel;
    }

    @Override
    public void prepareView() {
        this.uAuthViewModel.setUrl(this.uAuthOutputData.getURL());
    }
}

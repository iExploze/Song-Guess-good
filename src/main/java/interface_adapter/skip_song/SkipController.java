package interface_adapter.skip_song;

import usecase.Skip.SkipInputBoundary;

public class SkipController {

    final SkipInputBoundary userSkipInteractorUsecase;
    public SkipController(SkipInputBoundary userSkipInteractorUsecase)
    {
        this.userSkipInteractorUsecase = userSkipInteractorUsecase;
    }

    public void execute()
    {
        userSkipInteractorUsecase.execute();
    }
}

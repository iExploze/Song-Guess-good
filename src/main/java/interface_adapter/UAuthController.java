package interface_adapter;

import usecase.UserAuth.UAuthInputBoundary;

public class UAuthController {

    final UAuthInputBoundary uAuthInteractorUsecase;

    public UAuthController(UAuthInputBoundary uAuthInteractorUsecase) {
        this.uAuthInteractorUsecase = uAuthInteractorUsecase;
    }

    public void execute()
    {
        uAuthInteractorUsecase.execute();
        uAuthInteractorUsecase.getURL();
    }
}

package interface_adapter.UAuth;

import usecase.UserAuth.UAuthInputBoundary;

public class UAuthController {

    final UAuthInputBoundary uAuthInteractorUsecase;

    public UAuthController(UAuthInputBoundary uAuthInteractorUsecase) {
        this.uAuthInteractorUsecase = uAuthInteractorUsecase;
    }

    public void execute()
    {
        uAuthInteractorUsecase.execute();
    }
}

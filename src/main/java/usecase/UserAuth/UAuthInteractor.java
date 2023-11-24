package usecase.UserAuth;

import dataAccessObjects.RandomSecureS256Generator;
import dataAccessObjects.userOAuthObject;

public class UAuthInteractor implements UAuthInputBoundary{

    final UAuthOutputBoundary uAuthOutputBoundary;
    private String URL;
    final String redirectURL = "http://localhost/";

    final String client_id = "ee5a4dc5c931462e9e630c64a8aee5ac";

    private RandomSecureS256Generator randString;

    private String random;

    public UAuthInteractor(UAuthOutputBoundary uAuthOutputBoundary) {
        this.uAuthOutputBoundary = uAuthOutputBoundary;
        this.URL = "";

        randString = new RandomSecureS256Generator(43);
        randString.generateSHA256Hash(randString.generateRandomString());
    }

    @Override
    public void execute() {
        userOAuthObject o = new userOAuthObject();
        URL = o.requestUserAccessToken(client_id, redirectURL, "user-top-read", randString.toString());
    }

    @Override
    public String getURL() {
        return URL;
    }
}

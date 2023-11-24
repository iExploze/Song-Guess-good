package dataAccessObjects;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.Gson;


public class userOAuthObject {
    // gonna containing the logic of login in
    // scope: user-top-read
    // authURL: https://accounts.spotify.com/authorize

    public String requestUserAccessToken(String clientid, String redirectURL, String scope, String codeVerifier) {

        HashMap<String, String> params = new HashMap<>();
        params.put("response_type", "code");
        params.put("client_id", clientid);

        //{"response_type": "code", "client_id": clientid, scope, "code_challenge_method": "S256", "code_challenge": codeVerifier, "redirect_uri": redirectURL}

        //convert java object to JSON format
        String urlLinkWithParams = String.format("https://accounts.spotify.com/authorize?response_type=code&client_id=%s&%s&code_challenge_method=S256" +
                "&code_challenge=%s&redirect_uri=%s", clientid, scope, codeVerifier, redirectURL);
        try {
            URL url = new URL(urlLinkWithParams);

            //then we need to open a connection to this website have the user sign in. So first we need to send our link
            // to a webbrowser. To do this, we need to send the link to the home view model
        } catch (MalformedURLException e) {
            System.out.println("Malformed URL");
        }
        // going to assume this function gives me the result from authentication.
    }
}

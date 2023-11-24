package usecase.UserAuth;

public class UAuthOutputData {

    private final String URL;
    public UAuthOutputData(String url)
    {

        URL = url;
    }

    public String getURL()
    {
        return URL;
    }
}

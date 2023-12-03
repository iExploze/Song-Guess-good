import dataAccessObjects.getTop200SongNames;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestTop200SongNames {
    getTop200SongNames g;
    @Before
    public void init() {
        g = new getTop200SongNames();
    }
    @Test
    public void test() throws FileNotFoundException {
        List<String> a = g.top200("./top200SongsWeekly.csv");
        assertEquals(a.size(),200);
    }
}




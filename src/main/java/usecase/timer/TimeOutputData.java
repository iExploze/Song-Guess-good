package usecase.timer;

public class TimeOutputData {
    private int currTime;
    public TimeOutputData()
    {
        currTime = 0;
    }

    public TimeOutputData(int time)
    {
        this.currTime = time;
    }

    public void setRemainingTime(int amount)
    {
        this.currTime = amount;
    }

    public int getRemainingTime()
    {
        return this.currTime;
    }
}

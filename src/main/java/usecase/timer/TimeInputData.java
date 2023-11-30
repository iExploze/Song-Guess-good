package usecase.timer;

public class TimeInputData {
    private int time;

    public TimeInputData()
    {
        this.time = 0;
    }
    public TimeInputData(int time)
    {
        this.time = time;
    }

    public void setTimeInput(int time)
    {
        this.time = time;
    }

    public int getTimeInput()
    {
        return this.time;
    }
}

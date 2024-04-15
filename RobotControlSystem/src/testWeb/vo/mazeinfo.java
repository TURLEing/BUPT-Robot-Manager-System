package testWeb.vo;

public class mazeinfo {
    public int getMazeID() {
        return mazeID;
    }

    public void setMazeID(int mazeID) {
        this.mazeID = mazeID;
    }

    public int getTotExplored() {
        return totExplored;
    }

    public void setTotExplored(int totExplored) {
        this.totExplored = totExplored;
    }

    public float getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(float averageTime) {
        this.averageTime = averageTime;
    }

    private int mazeID;
    private int totExplored;
    private float averageTime;
}

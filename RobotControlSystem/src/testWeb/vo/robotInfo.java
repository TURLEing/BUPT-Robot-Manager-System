package testWeb.vo;

import java.util.Date;

public class robotInfo {
    private int robotId;
    private String robotName;
    private Date startTime;
    private int totTreasure;
    private int numExplore;

    public int getRobotId() {
        return robotId;
    }

    public void setRobotId(int robotId) {
        this.robotId = robotId;
    }
    public int getNumExplore() { return numExplore; }
    public void setNumExplore(int numExplore) {
        this.numExplore = numExplore;
    }
    public String getRobotName() { return robotName; }
    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }
    public Date getStartTime() { return startTime; }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public int getTotTreasure() { return totTreasure; }
    public void setTotTreasure(int totTreasure) {
        this.totTreasure = totTreasure;
    }

}

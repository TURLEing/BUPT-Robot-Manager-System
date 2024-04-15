package testWeb.vo.dto;

import java.util.Date;

public class robotDto {
    private String robotName;
    private String belongName;
    private int totTreasure;
    private int numExplore;
    private Date startTime;

    public String getRobotName() {
        return robotName;
    }

    public void setRobotName(String robotName) {
        this.robotName = robotName;
    }

    public String getBelongName() {
        return belongName;
    }

    public void setBelongName(String belongName) {
        this.belongName = belongName;
    }

    public int getTotTreasure() {
        return totTreasure;
    }

    public void setTotTreasure(int totTreasure) {
        this.totTreasure = totTreasure;
    }

    public int getNumExplore() {
        return numExplore;
    }

    public void setNumExplore(int numExplore) {
        this.numExplore = numExplore;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

}

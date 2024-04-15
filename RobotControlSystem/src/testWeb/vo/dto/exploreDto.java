package testWeb.vo.dto;

import java.sql.Timestamp;

public class exploreDto {

    public int getMazeId() {
        return mazeId;
    }

    public void setMazeId(int mazeId) {
        this.mazeId = mazeId;
    }

    public int getTreasureId() {
        return treasureId;
    }

    public void setTreasureId(int treasureId) {
        this.treasureId = treasureId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getExplorationId() {
        return explorationId;
    }

    public void setExplorationId(int explorationId) {
        this.explorationId = explorationId;
    }

    private int explorationId;
    private int mazeId;
    private int treasureId;
    private Timestamp startTime, endTime;

}

package testWeb.vo.dto;
import java.sql.Timestamp;

public class treasureDto {
    private int treasureId;
    private int belongMazeId;
    private Timestamp findTime;
    private String type;

    public int getTreasureId() {
        return treasureId;
    }

    public void setTreasureId(int treasureId) {
        this.treasureId = treasureId;
    }

    public int getBelongMazeId() {
        return belongMazeId;
    }

    public void setBelongMazeId(int belongMazeId) {
        this.belongMazeId = belongMazeId;
    }

    public Timestamp getFindTime() {
        return findTime;
    }

    public void setFindTime(Timestamp findTime) {
        this.findTime = findTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

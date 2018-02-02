package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class AllianceBuildingActivity extends Activity {
  public AllianceBuildingActivity(int userId, LocalDate date, LocalTime time, String activityName) {
    super(userId, date, time, Category.AllianceBuilding, activityName);
  }
}

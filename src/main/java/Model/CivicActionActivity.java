package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class CivicActionActivity extends Activity {
  public CivicActionActivity(int userId, LocalDate date, LocalTime time, String activityName) {
    super(userId, date, time, Category.CivicAction, activityName);
  }
}

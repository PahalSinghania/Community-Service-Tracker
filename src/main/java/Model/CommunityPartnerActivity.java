package Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class CommunityPartnerActivity extends Activity {
  private int hours;
  private Partner partner;
  private Location location;
  private String comments;

  public CommunityPartnerActivity(int userId, LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName) {
    super(userId, date, time, Category.CommunityPartner, activityName);
    this.hours = hours;
    this.partner = partner;
    this.location = location;
    this.comments = comments;
  }

  public int getHours() {
    return hours;
  }

  public void setHours(int hours) {
    this.hours = hours;
  }

  public Partner getPartner() {
    return partner;
  }

  public void setPartner(Partner partner) {
    this.partner = partner;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }
}

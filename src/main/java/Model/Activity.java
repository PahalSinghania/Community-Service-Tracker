package Model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a log for a single activity involving civic engagement.
 */
public abstract class Activity {
  protected int id;
  protected int userId;
  //Required fields
  protected LocalDate date;
  protected LocalTime time;
  protected String activityName;
  protected Category category;

  public Activity(int userId, LocalDate date, LocalTime time, Category category, String activityName) {
    validateDate(date);
    validateString(activityName);

    this.userId = userId;
    this.date = date;
    this.time = time;
    this.category = category;
    this.activityName = activityName;
  }

  public void setFields(LocalDate date, LocalTime time, Category category, String activityName) {
    this.date = date;
    this.time = time;
    this.category = category;
    this.activityName = activityName;
  }

  public int getUserId() {
    return userId;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public LocalTime getTime() {
    return time;
  }

  public void setTime(LocalTime time) {
    this.time = time;
  }

  public Category getCategory() {
    return category;
  }

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  private void validateDate(LocalDate date) {
    if (date.isBefore(LocalDate.of(2017, 1, 1))) {
      throw new IllegalArgumentException("The date, " + date + " should be 2017 or later.");
    }
  }

  private void validateString(String s) {
    if (s.equals("")) {
      throw new IllegalArgumentException(s + " should be non-empty.");
    }
  }
}

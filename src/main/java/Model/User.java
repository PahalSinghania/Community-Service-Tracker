package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

/**
 * A User is an actor on the system.
 */
public abstract class User {
  public int id;
  public AccessType accessType;
  public String firstName;
  public String lastName;
  public String username;
  public String email;

  protected int nextId = 0; //TEMPORARY until database implementation

  public User(int id, AccessType accessType, String firstName, String lastName,
              String username, String email) {
    this.id = id;
    this.accessType = accessType;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.email = email;
  }

  public abstract Activity createCAActivity(LocalDate date, LocalTime time, String activityName);

  public abstract Activity createCAActivityWithUserId(int userId, LocalDate date, LocalTime time, String activityName);

  public abstract Activity createABActivity(LocalDate date, LocalTime time, String activityName);

  public abstract Activity createABActivityWithUserId(int userId, LocalDate date, LocalTime time, String activityName);

  public abstract Activity createCPActivity(LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName);

  public abstract Activity createCPActivityWithUserId(int userId, LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName);

  public abstract void editCAActivity(int activityId, LocalDate date, LocalTime time, String activityName);

  public abstract void editCAActivityWithUserId(int userId, int activityId, LocalDate date, LocalTime time, String activityName);

  public abstract void editABActivity(int activityId, LocalDate date, LocalTime time, String activityName);

  public abstract void editABActivityWithUserId(int userId, int activityId, LocalDate date, LocalTime time, String activityName);

  public abstract void editCPActivity(int activityId, LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName);

  public abstract void editCPActivityWithUserId(int userId, int activityId, LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName);

  public abstract Activity getActivityById(int activityId);

  public abstract Activity getActivityByIdAndUserId(int userId, int activityId);

  public AccessType getAccessType() {
    return accessType;
  }

  public void setAccessType(AccessType accessType) {
    this.accessType = accessType;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public int getId() {
    return id;
  }

  public abstract Map<Integer, Activity> getActivities();

  public abstract Map<Integer, Activity> getActivitiesByUserId(int userId);
}

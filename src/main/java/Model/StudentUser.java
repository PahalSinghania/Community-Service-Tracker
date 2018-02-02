package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a user with student access.
 */
public class StudentUser extends User {
  public String communityPartner;
  public String communityPartnerLocation;
  public Map<Integer, Activity> activities;

  public StudentUser(int id, String firstName, String lastName,
                     String username, String email,
                     String communityPartner, String communityPartnerLocation) {
    super(id, AccessType.StudentAccess, firstName, lastName, username, email);
    this.communityPartner = communityPartner;
    this.communityPartnerLocation = communityPartnerLocation;
    this.activities = new HashMap<>();
  }

  public String getCommunityPartner() {
    return communityPartner;
  }

  public void setCommunityPartner(String communityPartner) {
    this.communityPartner = communityPartner;
  }

  public String getCommunityPartnerLocation() {
    return communityPartnerLocation;
  }

  public void setCommunityPartnerLocation(String communityPartnerLocation) {
    this.communityPartnerLocation = communityPartnerLocation;
  }

  @Override
  public Activity createCAActivity(LocalDate date, LocalTime time, String activityName) {
    Activity activity = new CivicActionActivity(id, date, time, activityName);

    //TODO - Add to database, get id from database
    int activityId = nextId++; //TEMPORARY until database implementation
    activity.setId(activityId);
    activities.put(activityId, activity);

    return activity;
  }

  @Override
  public Activity createCAActivityWithUserId(int userId, LocalDate date, LocalTime time, String activityName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Activity createABActivity(LocalDate date, LocalTime time, String activityName) {
    Activity activity = new AllianceBuildingActivity(id, date, time, activityName);

    //TODO - Add to database, get id from database
    int activityId = nextId++; //TEMPORARY until database implementation
    activity.setId(activityId);
    activities.put(activityId, activity);

    return activity;
  }

  @Override
  public Activity createABActivityWithUserId(int userId, LocalDate date, LocalTime time, String activityName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Activity createCPActivity(LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName) {
    Activity activity = new CommunityPartnerActivity(id, date, time, hours, partner, location, comments, activityName);

    //TODO - Add to database, get id from database
    int activityId = nextId++; //TEMPORARY until database implementation
    activity.setId(activityId);
    activities.put(activityId, activity);

    return activity;
  }

  @Override
  public Activity createCPActivityWithUserId(int userId, LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void editCAActivity(int activityId, LocalDate date, LocalTime time, String activityName) {
    Activity activity = activities.get(activityId);

    if (activity == null) {
      throw new IllegalArgumentException("Activity id " + activityId + " is incorrect");
    }

    Activity newActivity = new CivicActionActivity(id, date, time, activityName);
    newActivity.setId(activityId);
    activities.put(activityId, newActivity);
  }

  @Override
  public void editCAActivityWithUserId(int userId, int activityId, LocalDate date, LocalTime time, String activityName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void editABActivity(int activityId, LocalDate date, LocalTime time, String activityName) {
    Activity activity = activities.get(activityId);

    if (activity == null) {
      throw new IllegalArgumentException("Activity id " + activityId + " is incorrect");
    }

    Activity newActivity = new AllianceBuildingActivity(id, date, time, activityName);
    newActivity.setId(activityId);
    activities.put(activityId, newActivity);
  }

  @Override
  public void editABActivityWithUserId(int userId, int activityId, LocalDate date, LocalTime time, String activityName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void editCPActivity(int activityId, LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName) {
    Activity activity = activities.get(activityId);

    if (activity == null) {
      throw new IllegalArgumentException("Activity id " + activityId + " is incorrect");
    }

    Activity newActivity = new CommunityPartnerActivity(id, date, time, hours, partner, location, comments, activityName);
    newActivity.setId(activityId);
    activities.put(activityId, newActivity);
  }

  @Override
  public void editCPActivityWithUserId(int userId, int activityId, LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Activity getActivityById(int activityId) {
    return activities.get(activityId);
  }

  @Override
  public Activity getActivityByIdAndUserId(int userId, int activityId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Map<Integer, Activity> getActivities() {
    return activities;
  }

  @Override
  public Map<Integer, Activity> getActivitiesByUserId(int userId) {
    throw new UnsupportedOperationException();
  }
}

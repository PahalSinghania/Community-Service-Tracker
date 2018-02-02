package Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;

/**
 * Represents a user with admin access
 */
public class AdminUser extends User {
  private Map<Integer, Map<Integer, Activity>> activitiesByUserId;

  public AdminUser(int id, String firstName, String lastName,
                   String username, String email) {
    super(id, AccessType.AdminAccess, firstName, lastName, username, email);
    activitiesByUserId = new HashMap<>();
  }

  @Override
  public Activity createCAActivity(LocalDate date, LocalTime time, String activityName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Activity createCAActivityWithUserId(int userId, LocalDate date, LocalTime time, String activityName) {
    Activity activity = new CivicActionActivity(userId, date, time, activityName);

    //TODO - Add to database, get id from database
    int activityId = nextId++; //TEMPORARY until database implementation
    activity.setId(activityId);
    Map<Integer, Activity> activitiesForUser = activitiesByUserId.get(userId);
    if (activitiesForUser == null) {
      activitiesForUser = new HashMap<>();
    }

    activitiesForUser.put(activityId, activity);
    activitiesByUserId.put(userId, activitiesForUser);
    return activity;
  }

  @Override
  public Activity createABActivity(LocalDate date, LocalTime time, String activityName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Activity createABActivityWithUserId(int userId, LocalDate date, LocalTime time, String activityName) {
    Activity activity = new AllianceBuildingActivity(userId, date, time, activityName);

    //TODO - Add to database, get id from database
    int activityId = nextId++; //TEMPORARY until database implementation
    activity.setId(activityId);
    Map<Integer, Activity> activitiesForUser = activitiesByUserId.get(userId);
    if (activitiesForUser == null) {
      activitiesForUser = new HashMap<>();
    }

    activitiesForUser.put(activityId, activity);
    activitiesByUserId.put(userId, activitiesForUser);
    return activity;
  }

  @Override
  public Activity createCPActivity(LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Activity createCPActivityWithUserId(int userId, LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName) {
    Activity activity = new CommunityPartnerActivity(userId, date, time, hours, partner, location, comments, activityName);

    //TODO - Add to database, get id from database
    int activityId = nextId++; //TEMPORARY until database implementation
    activity.setId(activityId);
    Map<Integer, Activity> activitiesForUser = activitiesByUserId.get(userId);
    if (activitiesForUser == null) {
      activitiesForUser = new HashMap<>();
    }

    activitiesForUser.put(activityId, activity);
    activitiesByUserId.put(userId, activitiesForUser);
    return activity;
  }

  @Override
  public void editCAActivity(int activityId, LocalDate date, LocalTime time, String activityName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void editCAActivityWithUserId(int userId, int activityId, LocalDate date, LocalTime time, String activityName) {
    Map<Integer, Activity> activitiesForUser = activitiesByUserId.get(userId);
    if (activitiesForUser == null) {
      throw new IllegalArgumentException("User id " + userId + " has no activities");
    }

    Activity activity = activitiesForUser.get(activityId);
    if (activity == null) {
      throw new IllegalArgumentException("Activity id " + activityId + " is incorrect");
    }

    Activity newActivity = new CivicActionActivity(userId, date, time, activityName);
    newActivity.setId(activityId);
    //TODO - replace activity in database with new activity (based on the activity id)
    activitiesForUser.put(activityId, newActivity);
    activitiesByUserId.put(userId, activitiesForUser);
  }

  @Override
  public void editABActivity(int activityId, LocalDate date, LocalTime time, String activityName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void editABActivityWithUserId(int userId, int activityId, LocalDate date, LocalTime time, String activityName) {
    Map<Integer, Activity> activitiesForUser = activitiesByUserId.get(userId);
    if (activitiesForUser == null) {
      throw new IllegalArgumentException("User id " + userId + " has no activities");
    }

    Activity activity = activitiesForUser.get(activityId);
    if (activity == null) {
      throw new IllegalArgumentException("Activity id " + activityId + " is incorrect");
    }

    Activity newActivity = new AllianceBuildingActivity(userId, date, time, activityName);
    newActivity.setId(activityId);
    //TODO - replace activity in database with new activity (based on the activity id)
    activitiesForUser.put(activityId, newActivity);
    activitiesByUserId.put(userId, activitiesForUser);
  }

  @Override
  public void editCPActivity(int activityId, LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void editCPActivityWithUserId(int userId, int activityId, LocalDate date, LocalTime time, int hours, Partner partner, Location location, String comments, String activityName) {
    Map<Integer, Activity> activitiesForUser = activitiesByUserId.get(userId);
    if (activitiesForUser == null) {
      throw new IllegalArgumentException("User id " + userId + " has no activities");
    }

    Activity activity = activitiesForUser.get(activityId);
    if (activity == null) {
      throw new IllegalArgumentException("Activity id " + activityId + " is incorrect");
    }

    Activity newActivity = new CommunityPartnerActivity(userId, date, time, hours, partner, location, comments, activityName);
    newActivity.setId(activityId);
    //TODO - replace activity in database with new activity (based on the activity id)
    activitiesForUser.put(activityId, newActivity);
    activitiesByUserId.put(userId, activitiesForUser);
  }

  @Override
  public Activity getActivityById(int activityId) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Activity getActivityByIdAndUserId(int userId, int activityId) {
    Map<Integer, Activity> activitiesForUser = activitiesByUserId.get(userId);
    return activitiesForUser.get(activityId);
  }

  @Override
  public Map<Integer, Activity> getActivities() {
    throw new UnsupportedOperationException();
  }

  @Override
  public Map<Integer, Activity> getActivitiesByUserId(int userId) {
    return activitiesByUserId.get(userId);
  }

  public static void addUser(AccessType accessType, String firstName, String lastName, String userName,
                             String email) {
    try {
      // Register Driver
      Class.forName("com.mysql.jdbc.Driver");
      String myUrl = "jdbc:mysql://localhost:3306/nuACES";
      Connection connection = DriverManager.getConnection(myUrl, "root", ""); // Password for ubuntu root is teamgreat; input as second string
      PreparedStatement statement = connection.prepareStatement(
              "INSERT INTO `user`(`first_name`,`last_name`, `email`, `username`, `access_type`) VALUES (?, ?, ?, ?, ?)");
      statement.setString(1, firstName);
      statement.setString(2, lastName);
      statement.setString(3, email);
      statement.setString(4, userName);
      statement.setString(5, accessType.name());
      statement.executeUpdate();

    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.err.println("Problem Connecting!");
    }
  }

  // Add dummy values
  public static void main(String[] args) {
    addUser(AccessType.StudentAccess, "Matthew", "Thanos", "mthanos",
            "thanos.m@husky.neu.edu");
  }
}

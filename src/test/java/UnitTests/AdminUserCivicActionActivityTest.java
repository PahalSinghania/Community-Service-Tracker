package UnitTests;

import Model.*;
import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalTime;

import static UnitTests.ActivityTestHelper.*;

/**
 * Tests User logic.
 */
public class AdminUserCivicActionActivityTest extends TestCase {
  private User admin;

  public AdminUserCivicActionActivityTest(String s) {
    super(s);
  }

  protected void setUp() throws Exception {
    super.setUp();
    admin = new AdminUser(2, "First2", "Last2", "Username2", "Email2");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public final void testAdminCreateCAActivity() {
    LocalDate date = LocalDate.of(2017, 11, 1);
    LocalTime time = LocalTime.of(13, 0);
    String activityName = "ABC";
    int numActivities = admin.getActivitiesByUserId(1) == null ? 0 : admin.getActivitiesByUserId(1).size();

    //Null comments
    Activity studentActivity = admin.createCAActivityWithUserId(1, date, time, activityName);
    assertDate(studentActivity, date);
    assertTime(studentActivity, time);
    assertCategory(studentActivity, Category.CivicAction);
    assertActivityName(studentActivity, activityName);

    //Current date and time
    LocalDate curDate = LocalDate.now();
    LocalTime curTime = LocalTime.now();
    studentActivity = admin.createCAActivityWithUserId(1, curDate, curTime, activityName);
    assertDate(studentActivity, curDate);
    assertTime(studentActivity, curTime);

    //Check that all of the activities have been created
    int newNumActivities = admin.getActivitiesByUserId(1) == null ? 0 : admin.getActivitiesByUserId(1).size();
    assertEquals(newNumActivities, numActivities + 2);
    assertEquals(admin.getActivitiesByUserId(1).get(0).getUserId(), 1);

    //TODO - mock adding activity to db
  }

  public final void testCreateCAActivityExceptions() {
    LocalTime time = LocalTime.of(13, 0);
    String activityName = "ABC";
    int numActivities = admin.getActivitiesByUserId(2) == null ? 0 : admin.getActivitiesByUserId(2).size();

    try {
      admin.createCAActivityWithUserId(2, LocalDate.of(2016, 12, 31), time, activityName);

      //Didn't throw exception
      fail();
    } catch (IllegalArgumentException ex) {
      //pass
    }

    //Check that no activities have been created
    int newNumActivities = admin.getActivitiesByUserId(2) == null ? 0 : admin.getActivitiesByUserId(2).size();
    assertEquals(newNumActivities, numActivities);

    //TODO - mock activity not added to db
  }

  public final void testStudentEditCAActivity() {
    LocalDate date = LocalDate.of(2017, 11, 1);
    LocalDate newDate = LocalDate.of(2017, 12, 31);
    LocalTime time = LocalTime.of(13, 0);
    LocalTime newTime = LocalTime.of(1, 30);
    Category newCategory = Category.CivicAction;
    String activityName = "ABC";
    String newActivityName = "DEF";
    Activity activity = admin.createABActivityWithUserId(10, date, time, activityName);
    int activityId = activity.getId();

    //TODO - mock adding edited activity to db

    admin.editCAActivityWithUserId(10, activityId, newDate, newTime, newActivityName);
    Activity editedActivity = admin.getActivityByIdAndUserId(10, activityId);
    assertDate(editedActivity, newDate);
    assertTime(editedActivity, newTime);
    assertCategory(editedActivity, newCategory);
    assertActivityName(editedActivity, newActivityName);
  }

  public final void testEditCAActivityExceptions() {
    LocalDate date = LocalDate.of(2017, 11, 1);
    LocalTime time = LocalTime.of(13, 0);
    String activityName = "ABC";
    int numActivities = admin.getActivitiesByUserId(11) == null ? 0 : admin.getActivitiesByUserId(11).size();

    try {
      //activityId doesn't exist
      admin.editCAActivityWithUserId(11, 3000, date, time, activityName);

      //Didn't throw exception
      fail();
    } catch (IllegalArgumentException ex) {
      //pass
    }

    //Check that no activities have been created
    int newNumActivities = admin.getActivitiesByUserId(11) == null ? 0 : admin.getActivitiesByUserId(11).size();
    assertEquals(newNumActivities, numActivities);

    //TODO - mock activity not changed in db
  }
}

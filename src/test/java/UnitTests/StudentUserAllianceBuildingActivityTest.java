package UnitTests;

import Model.Activity;
import Model.Category;
import Model.StudentUser;
import Model.User;
import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalTime;

import static UnitTests.ActivityTestHelper.*;

/**
 * Tests User logic.
 */
public class StudentUserAllianceBuildingActivityTest extends TestCase {
  private User student;

  public StudentUserAllianceBuildingActivityTest(String s) {
    super(s);
  }

  protected void setUp() throws Exception {
    super.setUp();
    student = new StudentUser(1, "First", "Last", "Username", "Email", "AB", "Boston");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public final void testStudentCreateABActivity() {
    LocalDate date = LocalDate.of(2017, 11, 1);
    LocalTime time = LocalTime.of(13, 0);
    String activityName = "ABC";
    int numActivities = student.getActivities().size();

    //Null comments
    Activity studentActivity = student.createABActivity(date, time, activityName);
    assertDate(studentActivity, date);
    assertTime(studentActivity, time);
    assertCategory(studentActivity, Category.AllianceBuilding);
    assertActivityName(studentActivity, activityName);

    //Current date and time
    LocalDate curDate = LocalDate.now();
    LocalTime curTime = LocalTime.now();
    studentActivity = student.createABActivity(curDate, curTime, activityName);
    assertDate(studentActivity, curDate);
    assertTime(studentActivity, curTime);

    //Check that all of the activities have been created
    assertEquals(student.getActivities().size(), numActivities + 2);

    //TODO - mock adding activity to db
  }

  public final void testCreateABActivityExceptions() {
    LocalTime time = LocalTime.of(13, 0);
    String activityName = "ABC";
    int numActivities = student.getActivities().size();

    try {
      student.createABActivity(LocalDate.of(2016, 12, 31), time, activityName);

      //Didn't throw exception
      fail();
    } catch (IllegalArgumentException ex) {
      //pass
    }

    //Check that no activities have been created
    assertEquals(student.getActivities().size(), numActivities);

    //TODO - mock activity not added to db
  }

  public final void testStudentEditActivity() {
    LocalDate date = LocalDate.of(2017, 11, 1);
    LocalDate newDate = LocalDate.of(2017, 12, 31);
    LocalTime time = LocalTime.of(13, 0);
    LocalTime newTime = LocalTime.of(1, 30);
    Category newCategory = Category.AllianceBuilding;
    String activityName = "ABC";
    String newActivityName = "DEF";
    Activity activity = student.createCPActivity(date, time, 2, null, null, null, activityName);
    int activityId = activity.getId();

    //TODO - mock adding edited activity to db

    student.editABActivity(activityId, newDate, newTime, newActivityName);
    Activity editedActivity = student.getActivityById(activityId);
    assertDate(editedActivity, newDate);
    assertTime(editedActivity, newTime);
    assertCategory(editedActivity, newCategory);
    assertActivityName(editedActivity, newActivityName);
  }

  public final void testEditActivityExceptions() {
    LocalDate date = LocalDate.of(2017, 11, 1);
    LocalTime time = LocalTime.of(13, 0);
    String activityName = "ABC";
    int numActivities = student.getActivities().size();

    try {
      //activityId doesn't exist
      student.editABActivity(3000, date, time, activityName);

      //Didn't throw exception
      fail();
    } catch (IllegalArgumentException ex) {
      //pass
    }

    //Check that no activities have been created
    assertEquals(student.getActivities().size(), numActivities);

    //TODO - mock activity not changed in db
  }
}

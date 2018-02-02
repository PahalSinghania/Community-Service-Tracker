package UnitTests;

import Model.*;
import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalTime;

import static UnitTests.ActivityTestHelper.*;

/**
 * Tests User logic.
 */
public class StudentUserComPartnerActivityTest extends TestCase {
  private User student;

  public StudentUserComPartnerActivityTest(String s) {
    super(s);
  }

  protected void setUp() throws Exception {
    super.setUp();
    student = new StudentUser(1, "First", "Last", "Username", "Email", "CP", "Boston");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public final void testStudentCreateCPActivity() {
    LocalDate date = LocalDate.of(2017, 11, 1);
    LocalTime time = LocalTime.of(13, 0);
    int hours = 2;
    String activityName = "ABC";
    Partner partner = new Partner("partner A", 1);
    Location location = new Location("Boston", 1);
    String comments = "hi";
    int numActivities = student.getActivities().size();

    //Null comments
    Activity studentActivity = student.createCPActivity(date, time, hours, partner, location, null, activityName);
    assertDate(studentActivity, date);
    assertTime(studentActivity, time);
    assertCategory(studentActivity, Category.CommunityPartner);
    assertActivityName(studentActivity, activityName);
    assertPartner((CommunityPartnerActivity) studentActivity, partner);
    assertLocation((CommunityPartnerActivity) studentActivity, location);

    //Empty comments
    studentActivity = student.createCPActivity(date, time, hours, partner, location, "", activityName);
    assertComments((CommunityPartnerActivity) studentActivity, "");

    //Non-empty comments
    studentActivity = student.createCPActivity(date, time, hours, partner, location, comments, activityName);
    assertComments((CommunityPartnerActivity) studentActivity, comments);

    //Current date and time
    LocalDate curDate = LocalDate.now();
    LocalTime curTime = LocalTime.now();
    studentActivity = student.createCPActivity(curDate, curTime, hours, partner, location, comments, activityName);
    assertDate(studentActivity, curDate);
    assertTime(studentActivity, curTime);

    //Check that all of the activities have been created
    assertEquals(student.getActivities().size(), numActivities + 4);

    //TODO - mock adding activity to db
  }

  public final void testCreateCPActivityExceptions() {
    LocalTime time = LocalTime.of(13, 0);
    int hours = 2;
    String activityName = "ABC";
    Partner partner = new Partner("partner A", 1);
    Location location = new Location("Boston", 1);
    String comments = "hi";
    int numActivities = student.getActivities().size();

    try {
      student.createCPActivity(LocalDate.of(2016, 12, 31), time, hours, partner, location, comments, activityName);

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
    int newHours = 3;
    Category newCategory = Category.CommunityPartner;
    String activityName = "ABC";
    String newActivityName = "DEF";
    Partner newPartner = new Partner("partner A", 1);
    Location newLocation = new Location("Boston", 1);
    String newComments = "hi";
    Activity activity = student.createCAActivity(date, time, activityName);
    int activityId = activity.getId();

    //TODO - mock adding edited activity to db

    student.editCPActivity(activityId, newDate, newTime, newHours, newPartner, newLocation, newComments, newActivityName);
    Activity editedActivity = student.getActivityById(activityId);
    assertDate(editedActivity, newDate);
    assertTime(editedActivity, newTime);
    assertHours((CommunityPartnerActivity) editedActivity, newHours);
    assertCategory(editedActivity, newCategory);
    assertActivityName(editedActivity, newActivityName);
    assertPartner((CommunityPartnerActivity) editedActivity, newPartner);
    assertLocation((CommunityPartnerActivity) editedActivity, newLocation);
    assertComments((CommunityPartnerActivity) editedActivity, newComments);
  }

  public final void testEditActivityExceptions() {
    LocalDate date = LocalDate.of(2017, 11, 1);
    LocalTime time = LocalTime.of(13, 0);
    int hours = 2;
    String activityName = "ABC";
    Partner partner = new Partner("partner A", 1);
    Location location = new Location("Boston", 1);
    String comments = "hi";
    int numActivities = student.getActivities().size();

    try {
      //activityId doesn't exist
      student.editCPActivity(3000, date, time, hours, partner, location, comments, activityName);

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

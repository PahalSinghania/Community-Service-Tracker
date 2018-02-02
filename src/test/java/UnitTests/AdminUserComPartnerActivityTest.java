package UnitTests;

import Model.*;
import junit.framework.TestCase;

import java.time.LocalDate;
import java.time.LocalTime;

import static UnitTests.ActivityTestHelper.*;

/**
 * Tests User logic.
 */
public class AdminUserComPartnerActivityTest extends TestCase {
  private User admin;

  public AdminUserComPartnerActivityTest(String s) {
    super(s);
  }

  protected void setUp() throws Exception {
    super.setUp();
    admin = new AdminUser(2, "First2", "Last2", "Username2", "Email2");
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public final void testAdminCreateCPActivity() {
    LocalDate date = LocalDate.of(2017, 11, 1);
    LocalTime time = LocalTime.of(13, 0);
    int hours = 2;
    String activityName = "ABC";
    Partner partner = new Partner("partner A", 1);
    Location location = new Location("Boston", 1);
    String comments = "hi";
    int numActivities = admin.getActivitiesByUserId(1) == null ? 0 : admin.getActivitiesByUserId(1).size();

    //Null comments
    Activity studentActivity = admin.createCPActivityWithUserId(1, date, time, hours, partner, location, null, activityName);
    assertDate(studentActivity, date);
    assertTime(studentActivity, time);
    assertCategory(studentActivity, Category.CommunityPartner);
    assertActivityName(studentActivity, activityName);
    assertPartner((CommunityPartnerActivity) studentActivity, partner);
    assertLocation((CommunityPartnerActivity) studentActivity, location);

    //Empty comments
    studentActivity = admin.createCPActivityWithUserId(1, date, time, hours, partner, location, "", activityName);
    assertComments((CommunityPartnerActivity) studentActivity, "");

    //Non-empty comments
    studentActivity = admin.createCPActivityWithUserId(1, date, time, hours, partner, location, comments, activityName);
    assertComments((CommunityPartnerActivity) studentActivity, comments);

    //Current date and time
    LocalDate curDate = LocalDate.now();
    LocalTime curTime = LocalTime.now();
    studentActivity = admin.createCPActivityWithUserId(1, curDate, curTime, hours, partner, location, comments, activityName);
    assertDate(studentActivity, curDate);
    assertTime(studentActivity, curTime);

    //Check that all of the activities have been created
    int newNumActivities = admin.getActivitiesByUserId(1) == null ? 0 : admin.getActivitiesByUserId(1).size();
    assertEquals(newNumActivities, numActivities + 4);
    assertEquals(admin.getActivitiesByUserId(1).get(0).getUserId(), 1);

    //TODO - mock adding activity to db
  }

  public final void testCreateCPActivityExceptions() {
    LocalTime time = LocalTime.of(13, 0);
    int hours = 2;
    String activityName = "ABC";
    Partner partner = new Partner("partner A", 1);
    Location location = new Location("Boston", 1);
    String comments = "hi";
    int numActivities = admin.getActivitiesByUserId(2) == null ? 0 : admin.getActivitiesByUserId(2).size();

    try {
      //Date before 2017
      admin.createCPActivityWithUserId(2, LocalDate.of(2016, 12, 31), time, hours, partner, location, comments, activityName);

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

  public final void testStudentEditCPActivity() {
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
    Activity activity = admin.createCAActivityWithUserId(10, date, time, activityName);
    int activityId = activity.getId();

    //TODO - mock adding edited activity to db

    admin.editCPActivityWithUserId(10, activityId, newDate, newTime, newHours, newPartner, newLocation, newComments, newActivityName);
    Activity editedActivity = admin.getActivityByIdAndUserId(10, activityId);
    assertDate(editedActivity, newDate);
    assertTime(editedActivity, newTime);
    assertHours((CommunityPartnerActivity) editedActivity, newHours);
    assertCategory(editedActivity, newCategory);
    assertActivityName(editedActivity, newActivityName);
    assertPartner((CommunityPartnerActivity) editedActivity, newPartner);
    assertLocation((CommunityPartnerActivity) editedActivity, newLocation);
    assertComments((CommunityPartnerActivity) editedActivity, newComments);
  }

  public final void testEditCAPctivityExceptions() {
    LocalDate date = LocalDate.of(2017, 11, 1);
    LocalTime time = LocalTime.of(13, 0);
    int hours = 2;
    String activityName = "ABC";
    Partner partner = new Partner("partner A", 1);
    Location location = new Location("Boston", 1);
    String comments = "hi";
    Activity activity = admin.createCPActivityWithUserId(11, date, time, hours, partner, location, comments, activityName);
    int numActivities = admin.getActivitiesByUserId(11) == null ? 0 : admin.getActivitiesByUserId(11).size();

    try {
      //activityId doesn't exist
      admin.editCPActivityWithUserId(11, 3000, date, time, hours, partner, location, comments, activityName);

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

package UnitTests;

import Model.*;
import junit.framework.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class ActivityTestHelper extends TestCase {
  public ActivityTestHelper(String s) {
    super(s);
  }

  protected void setUp() throws Exception {
    super.setUp();
  }

  protected void tearDown() throws Exception {
    super.tearDown();
  }

  public final void test() {
    //Need this if extending TestCase
  }

  protected static void assertDate(Activity activity, LocalDate date) {
    assertEquals(activity.getDate(), date);
  }

  protected static void assertTime(Activity activity, LocalTime time) {
    assertEquals(activity.getTime(), time);
  }

  protected static void assertHours(CommunityPartnerActivity activity, int hours) {
    assertEquals(activity.getHours(), hours);
  }

  protected static void assertCategory(Activity activity, Category category) {
    assertEquals(activity.getCategory(), category);
  }

  protected static void assertActivityName(Activity activity, String activityName) {
    assertEquals(activity.getActivityName(), activityName);
  }

  protected static void assertPartner(CommunityPartnerActivity activity, Partner partner) {
    Partner activityPartner = activity.getPartner();
    assertEquals(activityPartner.getId(), partner.getId());
    assertEquals(activityPartner.getName(), partner.getName());
  }

  protected static void assertLocation(CommunityPartnerActivity activity, Location location) {
    Location activityLocation = activity.getLocation();
    assertEquals(activityLocation.getId(), location.getId());
    assertEquals(activityLocation.getName(), location.getName());
  }

  protected static void assertComments(CommunityPartnerActivity activity, String comments) {
    assertEquals(activity.getComments(), comments);
  }
}

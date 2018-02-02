package Controller;

import Model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.sql.*;

/**
 * Handles requests for students to create, edit or delete activities
 */
@Controller
public class StudentActivityController {
  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

  /** POST methods **/
  @RequestMapping(value = "/checkin/partner", method = RequestMethod.POST)
  @ResponseBody
  public Activity checkInComPartner(@RequestParam(value="userid", required=true) int userid,
                                    @RequestParam(value="date", required=true) String date,
                                    @RequestParam(value="time", required=true) String time,
                                    @RequestParam(value ="hours", required=true) int hours,
                                    @RequestParam(value="activityname", required=true) String activityName,
                                    @RequestParam(value="partner", required=true) String partner,
                                    @RequestParam(value="location", required=true) String location,
                                    @RequestParam(value="comments", required=false, defaultValue = "") String comments,
                                    Model model) {
    model.addAttribute("userid", userid);
    model.addAttribute("date", date);
    model.addAttribute("time", time);
    model.addAttribute("hours", hours);
    model.addAttribute("activityname", activityName);
    model.addAttribute("partner", partner);
    model.addAttribute("location", location);
    model.addAttribute("comments", comments);

    LocalDate localDate = LocalDate.parse(date, formatter);
    LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);

    // TODO - get User from database
    User student = new StudentUser(userid, "First", "Last", "Username", "email", "partner", "partner location");
    //TODO - get Partner from db
    Partner comPartner = new Partner(partner, 1);
    //TODO - get Location from db
    Location partnerLocation = new Location(location, 1);
    return student.createCPActivity(localDate, localTime, hours, comPartner, partnerLocation, comments, activityName);
  }

  @RequestMapping(value = "/editactivity/partner", method = RequestMethod.POST)
  @ResponseBody
  public void editComPartnerActivity(@RequestParam(value="userid", required=true) int userid,
                                        @RequestParam(value="activityid", required=true) int activityid,
                                        @RequestParam(value="date", required=true) String date,
                                        @RequestParam(value="time", required=true) String time,
                                     @RequestParam(value ="hours", required=true) int hours,
                                        @RequestParam(value="activityname", required=true) String activityName,
                                        @RequestParam(value="partner", required=true) String partner,
                                        @RequestParam(value="location", required=true) String location,
                                        @RequestParam(value="comments", required=false, defaultValue = "") String comments,
                                        Model model) {
    model.addAttribute("userid", userid);
    model.addAttribute("activityid", activityid);
    model.addAttribute("date", date);
    model.addAttribute("time", time);
    model.addAttribute("hours", hours);
    model.addAttribute("activityname", activityName);
    model.addAttribute("partner", partner);
    model.addAttribute("location", location);
    model.addAttribute("comments", comments);

    LocalDate localDate = LocalDate.parse(date, formatter);
    LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);

    // TODO - get User from database
    User student = new StudentUser(userid, "First", "Last", "Username", "email", "partner", "partner location");
    //TODO - get Partner from db
    Partner comPartner = new Partner(partner, 1);
    //TODO - get Location from db
    Location partnerLocation = new Location(location, 1);
    student.editCPActivity(activityid, localDate, localTime, hours, comPartner, partnerLocation, comments, activityName);
  }

  @RequestMapping(value = "/checkin/civicaction", method = RequestMethod.POST)
  @ResponseBody
  public Activity checkInCivicAction(@RequestParam(value="userid", required=true) int userid,
                          @RequestParam(value="date", required=true) String date,
                          @RequestParam(value="time", required=true) String time,
                          @RequestParam(value="activityname", required=true) String activityName,
                          Model model) {
    model.addAttribute("userid", userid);
    model.addAttribute("date", date);
    model.addAttribute("time", time);
    model.addAttribute("activityname", activityName);

    LocalDate localDate = LocalDate.parse(date, formatter);
    LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);

    // TODO - get user from database
    User student = new StudentUser(userid, "First", "Last", "Username", "email", "partner", "partner location");
    return student.createCAActivity(localDate, localTime, activityName);
  }

  @RequestMapping(value = "/editactivity/civicaction", method = RequestMethod.POST)
  @ResponseBody
  public void editCivicActionActivity(@RequestParam(value="userid", required=true) int userid,
                                     @RequestParam(value="activityid", required=true) int activityid,
                                     @RequestParam(value="date", required=true) String date,
                                     @RequestParam(value="time", required=true) String time,
                                     @RequestParam(value="activityname", required=true) String activityName,
                                     Model model) {
    model.addAttribute("userid", userid);
    model.addAttribute("activityid", activityid);
    model.addAttribute("date", date);
    model.addAttribute("time", time);
    model.addAttribute("activityname", activityName);

    LocalDate localDate = LocalDate.parse(date, formatter);
    LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);

    // TODO - get User from database
    User student = new StudentUser(userid, "First", "Last", "Username", "email", "partner", "partner location");
    student.editCAActivity(activityid, localDate, localTime, activityName);
  }

  @RequestMapping(value = "/checkin/alliancebuilding", method = RequestMethod.POST)
  @ResponseBody
  public Activity checkInAllianceBuilding(@RequestParam(value="userid", required=true) int userid,
                                          @RequestParam(value="date", required=true) String date,
                                          @RequestParam(value="time", required=true) String time,
                                          @RequestParam(value="activityname", required=true) String activityName,
                                          Model model) {
    model.addAttribute("userid", userid);
    model.addAttribute("date", date);
    model.addAttribute("time", time);
    model.addAttribute("activityname", activityName);

    LocalDate localDate = LocalDate.parse(date, formatter);
    LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);

    // TODO - get user from database
    User student = new StudentUser(userid, "First", "Last", "Username", "email", "partner", "partner location");
    return student.createABActivity(localDate, localTime, activityName);
  }

  @RequestMapping(value = "/editactivity/alliancebuilding", method = RequestMethod.POST)
  @ResponseBody
  public void editAllianceBuildingActivity(@RequestParam(value="userid", required=true) int userid,
                                      @RequestParam(value="activityid", required=true) int activityid,
                                      @RequestParam(value="date", required=true) String date,
                                      @RequestParam(value="time", required=true) String time,
                                      @RequestParam(value="activityname", required=true) String activityName,
                                      Model model) {
    model.addAttribute("userid", userid);
    model.addAttribute("activityid", activityid);
    model.addAttribute("date", date);
    model.addAttribute("time", time);
    model.addAttribute("activityname", activityName);

    LocalDate localDate = LocalDate.parse(date, formatter);
    LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);

    // TODO - get User from database
    User student = new StudentUser(userid, "First", "Last", "Username", "email", "partner", "partner location");
    student.editABActivity(activityid, localDate, localTime, activityName);
  }

  /** GET methods **/
  @RequestMapping(value = "/getactivity", method = RequestMethod.GET)
  @ResponseBody
  public Activity getActivity(@RequestParam(value="userid", required=true) int userid,
                                           @RequestParam(value="activityid", required=true) int activityid,
                                           Model model) {
    model.addAttribute("userid", userid);
    model.addAttribute("activityid", activityid);

    // TODO - get User from database
    User student = new StudentUser(userid, "First", "Last", "Username", "email", "partner", "partner location");
    return student.getActivityById(activityid);
  }

  @RequestMapping(value = "/getactivities", method = RequestMethod.GET)
  @ResponseBody
  public Map<Integer, Activity> getAllActivities(@RequestParam(value="userid", required=true) int userid,
                              Model model) {
    model.addAttribute("userid", userid);

    // TODO - get User from database
    User student = new StudentUser(userid, "First", "Last", "Username", "email", "partner", "partner location");
    return student.getActivities();
  }
}

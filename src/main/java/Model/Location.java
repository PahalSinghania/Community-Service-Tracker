package Model;

public class Location {
  private int id;
  private String name;

  public Location(String name, int id) {
    this.name = name;
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}

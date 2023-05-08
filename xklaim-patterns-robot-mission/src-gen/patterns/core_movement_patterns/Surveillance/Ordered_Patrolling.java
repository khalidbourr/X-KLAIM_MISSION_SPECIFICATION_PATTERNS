package patterns.core_movement_patterns.Surveillance;

import java.util.List;
import klava.Tuple;
import klava.topology.KlavaProcess;
import patterns.core_movement_patterns.Coverage.Ordered_Visit;
import utils.Location;
import xklaim.Navigation.RobotConstants;

/**
 * Sequence patrolling does not forbid to
 * visit a successor location before its predecessor. Ordered patrolling ensures that
 * (after a successor is visited) the successor is not visited (again) before its
 * predecessor.
 * 
 * @param robotId   the ID of the robot
 * @param locations the list of locations to visit
 */
@SuppressWarnings("all")
public class Ordered_Patrolling extends KlavaProcess {
  private String robotId;
  
  private List<Location> locations;
  
  public Ordered_Patrolling(final String robotId, final List<Location> locations) {
    this.robotId = robotId;
    this.locations = locations;
  }
  
  @Override
  public void executeProcess() {
    while (true) {
      {
        Ordered_Visit _ordered_Visit = new Ordered_Visit(this.robotId, this.locations);
        eval(_ordered_Visit, this.self);
        in(new Tuple(new Object[] {RobotConstants.ORDERED_VISIT_COMPLETED}), this.self);
      }
    }
  }
}

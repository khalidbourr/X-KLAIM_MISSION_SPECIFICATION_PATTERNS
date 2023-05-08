package patterns.core_movement_patterns.Surveillance;

import java.util.List;
import klava.Tuple;
import klava.topology.KlavaProcess;
import patterns.core_movement_patterns.Coverage.Visit;
import utils.Location;
import xklaim.Navigation.RobotConstants;

/**
 * Keep visiting a set of locations, but not
 * in a particular order.
 * 
 * @param robotId   the ID of the robot
 * @param locations the list of locations to visit
 * @param iterations the number of time the process should be executed
 */
@SuppressWarnings("all")
public class Patrolling extends KlavaProcess {
  private String robotId;
  
  private List<Location> locations;
  
  private Integer iterations;
  
  public Patrolling(final String robotId, final List<Location> locations, final Integer iterations) {
    this.robotId = robotId;
    this.locations = locations;
    this.iterations = iterations;
  }
  
  @Override
  public void executeProcess() {
    int count = 0;
    while ((count < (this.iterations).intValue())) {
      {
        Visit _visit = new Visit(this.robotId, this.locations);
        eval(_visit, this.self);
        in(new Tuple(new Object[] {RobotConstants.VISIT_COMPLETED}), this.self);
        count = (count + 1);
      }
    }
    out(new Tuple(new Object[] {RobotConstants.PATROLLING_COMPLETED}), this.self);
  }
}

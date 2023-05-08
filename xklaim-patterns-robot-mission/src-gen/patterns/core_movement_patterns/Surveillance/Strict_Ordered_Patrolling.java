package patterns.core_movement_patterns.Surveillance;

import java.util.List;
import klava.Tuple;
import klava.topology.KlavaProcess;
import patterns.core_movement_patterns.Coverage.Strict_Ordered_Visit;
import utils.Location;
import xklaim.Navigation.RobotConstants;

/**
 * Ordered patrolling pattern does not
 * avoid a predecessor location to be visited multiple times before its successor.
 * Strict Ordered Patrolling ensures that,
 * after a predecessor is visited, it is not
 * visited again before its successor.
 * 
 * @param robotId   the ID of the robot
 * @param locations the list of locations to visit
 * @param iterations the number of time the process should be executed
 */
@SuppressWarnings("all")
public class Strict_Ordered_Patrolling extends KlavaProcess {
  private String robotId;
  
  private List<Location> locations;
  
  private Integer iterations;
  
  public Strict_Ordered_Patrolling(final String robotId, final List<Location> locations, final Integer iterations) {
    this.robotId = robotId;
    this.locations = locations;
    this.iterations = iterations;
  }
  
  @Override
  public void executeProcess() {
    int count = 0;
    while ((count < (this.iterations).intValue())) {
      {
        Strict_Ordered_Visit _strict_Ordered_Visit = new Strict_Ordered_Visit(this.robotId, this.locations);
        eval(_strict_Ordered_Visit, this.self);
        in(new Tuple(new Object[] {RobotConstants.STRICT_ORDERED_VISIT_COMPLETED}), this.self);
        count = (count + 1);
      }
    }
    out(new Tuple(new Object[] {RobotConstants.STRICT_ORDERED_PATROLLING_COMPLETED}), this.self);
  }
}

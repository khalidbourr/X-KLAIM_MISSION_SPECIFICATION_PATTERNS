package patterns.core_movement_patterns.Coverage;

import java.util.ArrayList;
import java.util.List;
import klava.Tuple;
import klava.topology.KlavaProcess;
import org.eclipse.xtext.xbase.lib.InputOutput;
import utils.Location;
import utils.TraceGenerator;
import xklaim.Navigation.MoveTo;
import xklaim.Navigation.RobotConstants;

/**
 * Visit a set of locations in an unspecified order.
 * 
 * @param robotId   the ID of the robot
 * @param locations the list of locations to visit
 */
@SuppressWarnings("all")
public class Visit extends KlavaProcess {
  private String robotId;
  
  private List<Location> locations;
  
  public Visit(final String robotId, final List<Location> locations) {
    this.robotId = robotId;
    this.locations = locations;
  }
  
  @Override
  public void executeProcess() {
    final int n = this.locations.size();
    final int[] trace = TraceGenerator.createRandom(n);
    final ArrayList<String> visitedLocations = new ArrayList<String>();
    for (final int i : trace) {
      {
        final Location location = this.locations.get(i);
        visitedLocations.add(location.getName());
        String _name = location.getName();
        String _plus = ("Moving to location: " + _name);
        InputOutput.<String>println(_plus);
        MoveTo _moveTo = new MoveTo(this.robotId, Double.valueOf(location.x), Double.valueOf(location.y));
        eval(_moveTo, this.self);
        in(new Tuple(new Object[] {RobotConstants.MOVE_TO_COMPLETED}), this.self);
      }
    }
    out(new Tuple(new Object[] {RobotConstants.VISIT_COMPLETED}), this.self);
    InputOutput.<String>println(("Visited locations: " + visitedLocations));
  }
}

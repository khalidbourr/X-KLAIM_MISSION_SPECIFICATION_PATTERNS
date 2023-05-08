package patterns;

import java.util.Collections;
import java.util.List;
import klava.LogicalLocality;
import klava.PhysicalLocality;
import klava.topology.ClientNode;
import klava.topology.KlavaNodeCoordinator;
import klava.topology.LogicalNet;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.mikado.imc.common.IMCException;
import patterns.core_movement_patterns.Coverage.Strict_Ordered_Visit;
import utils.Location;

@SuppressWarnings("all")
public class RobotColl extends LogicalNet {
  private static final LogicalLocality Robot = new LogicalLocality("Robot");
  
  public static class Robot extends ClientNode {
    private static class RobotProcess extends KlavaNodeCoordinator {
      @Override
      public void executeProcess() {
        final String robotId = "robot1";
        final Location l1 = new Location("l1", (-9.0), 9.1);
        final Location l2 = new Location("l2", 9.0, (-9.1));
        final Location l3 = new Location("l3", (-9.0), (-9.1));
        final Location l4 = new Location("l4", 9.0, 9.1);
        final List<Location> locations = Collections.<Location>unmodifiableList(CollectionLiterals.<Location>newArrayList(l1, l2, l3, l4));
        Strict_Ordered_Visit _strict_Ordered_Visit = new Strict_Ordered_Visit(robotId, locations);
        eval(_strict_Ordered_Visit, this.self);
      }
    }
    
    public Robot() {
      super(new PhysicalLocality("localhost:9999"), new LogicalLocality("Robot"));
    }
    
    public void addMainProcess() throws IMCException {
      addNodeCoordinator(new RobotColl.Robot.RobotProcess());
    }
  }
  
  public RobotColl() throws IMCException {
    super(new PhysicalLocality("localhost:9999"));
  }
  
  public void addNodes() throws IMCException {
    RobotColl.Robot robot = new RobotColl.Robot();
    robot.addMainProcess();
  }
}

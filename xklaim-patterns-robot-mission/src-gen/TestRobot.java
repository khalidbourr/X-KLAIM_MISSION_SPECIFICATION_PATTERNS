import klava.LogicalLocality;
import klava.PhysicalLocality;
import klava.topology.ClientNode;
import klava.topology.KlavaNodeCoordinator;
import klava.topology.LogicalNet;
import messages.Twist;
import messages.XklaimToRosConnection;
import org.mikado.imc.common.IMCException;
import ros.Publisher;

@SuppressWarnings("all")
public class TestRobot extends LogicalNet {
  private static final LogicalLocality Robot = new LogicalLocality("Robot");
  
  public static class Robot extends ClientNode {
    private static class RobotProcess extends KlavaNodeCoordinator {
      @Override
      public void executeProcess() {
        final XklaimToRosConnection bridge = new XklaimToRosConnection("ws://0.0.0.0:9090");
        final Publisher pub = new Publisher("/cmd_vel", "geometry_msgs/Twist", bridge);
        final Twist vel_msg = new Twist();
        vel_msg.linear.x = 0.1;
        vel_msg.angular.z = 0.0;
        pub.publish(vel_msg);
      }
    }
    
    public Robot() {
      super(new PhysicalLocality("localhost:9999"), new LogicalLocality("Robot"));
    }
    
    public void addMainProcess() throws IMCException {
      addNodeCoordinator(new TestRobot.Robot.RobotProcess());
    }
  }
  
  public TestRobot() throws IMCException {
    super(new PhysicalLocality("localhost:9999"));
  }
  
  public void addNodes() throws IMCException {
    TestRobot.Robot robot = new TestRobot.Robot();
    robot.addMainProcess();
  }
}

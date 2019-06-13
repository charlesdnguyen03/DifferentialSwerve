package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.SwerveModule;

public class ClosedLoop extends Command {

  int modNum;
  double[] speed = new double[4];
  double[] angle = new double[4];
  SwerveModule[] swerveMods;
  double str,fwd,rot;

  public ClosedLoop(int modNum) {
    requires(Robot.sModule);
    requires(Robot.gyroSwerveDrive);
    this.modNum = modNum;
    for(int i = 0; i < modNum; i++) swerveMods[i] = new SwerveModule(i+1, false);
  }

  @Override
  protected void initialize() {
    SmartDashboard.putNumber("str", 0);
    SmartDashboard.putNumber("fwd", 0);
    SmartDashboard.putNumber("rot", 0);
  }

  @Override
  protected void execute() {
    str = SmartDashboard.getNumber("str", 0);
    fwd = SmartDashboard.getNumber("fwd", 0);
    rot = SmartDashboard.getNumber("rot", 0);

    Robot.gyroSwerveDrive.computeSwerveInputs(str, fwd, rot);

    for(int i = 0; i<modNum; i++){
      RobotMap.swerveMod[i].drive(speed[i],angle[i]);
    }
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}

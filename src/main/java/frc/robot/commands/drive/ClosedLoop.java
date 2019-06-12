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
  double strI,fwdI,rotI,str,fwd,rot;

  public ClosedLoop(int modNum) {
    requires(Robot.sModule);
    requires(Robot.gyroSwerveDrive);
    this.modNum = modNum;
    for(int i = 0; i < modNum; i++) swerveMods[i] = new SwerveModule(i+1, false);
  }

  @Override
  protected void initialize() {
    strI=0; fwdI=0; rotI=0;
    SmartDashboard.putNumber("str", strI);
    SmartDashboard.putNumber("fwd", fwdI);
    SmartDashboard.putNumber("rot", rotI);
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

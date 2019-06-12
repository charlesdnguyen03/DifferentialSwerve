package frc.robot.commands.drive;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.math.MathUtil;

public class GyroSwerveDriveCommand extends Command {
  public GyroSwerveDriveCommand () {
    requires(Robot.gyroSwerveDrive);
  }

  
  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    if(Robot.m_oi.joystick1.getPOV() == 180) RobotMap.gyro.reset();

    double axis0 = Robot.m_oi.joystick1.getRawAxis(0);
    double axis1 = Robot.m_oi.joystick1.getRawAxis(1);
    double axis4 = Robot.m_oi.joystick1.getRawAxis(4);
    double axis5 = Robot.m_oi.joystick1.getRawAxis(5);
    if (MathUtil.outOfDeadband(axis0, axis1)) { axis0 = 0.0; axis1 = 0.0; }
    if (MathUtil.outOfDeadband(axis4, axis5)) { axis4 = 0.0; axis5 = 0.0; }

    double mult = 0.8;
    double rotMult = 0.45;

    if (Robot.m_oi.dterribleRight.get()) rotMult = 0.8;
    if (Robot.m_oi.dterribleLeft.get()) mult = 1.0;

    if(Robot.m_oi.dbumperLeft.get()) {
      mult = 0.2;
      rotMult = 0.2;
    }

    if(axis0 != 0 || axis1 != 0 || axis4 != 0) Robot.gyroSwerveDrive.gyroDrive(axis0*mult, axis1*mult, axis4*rotMult);
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

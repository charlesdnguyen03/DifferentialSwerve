package frc.robot.commands.drive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class OpenLoop extends Command {

  public CANSparkMax motor1;
  public CANSparkMax motor2;
  public double vel, rot;

  public OpenLoop() {
    // requires(SwerveModule);
  }

  @Override
  protected void initialize() {
    motor1 = new CANSparkMax(1, MotorType.kBrushless);
    motor2 = new CANSparkMax(2, MotorType.kBrushless);
  }

  @Override
  protected void execute() {
    vel = Robot.m_oi.joystick1.getRawAxis(0);
    rot = Robot.m_oi.joystick1.getRawAxis(1);
    double Rad1 = Math.sqrt(Math.pow(vel, 2) + Math.pow(rot, 2));
    if (Rad1 < RobotMap.JOYSTICK_DEADBAND) { vel = 0.0; rot = 0.0; }

    double p1 = vel + rot;
    double p2 = vel - rot;

    motor1.set(p1);    
    motor2.set(p2);

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

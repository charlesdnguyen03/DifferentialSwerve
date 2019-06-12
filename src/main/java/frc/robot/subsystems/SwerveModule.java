package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANPIDController;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class SwerveModule extends Subsystem {
	private final double[] pidConstants;
	public double error;
	public double output;

	public CANSparkMax motor1;
	public CANSparkMax motor2;
	public PIDController pidController1;
	public PIDController pidController2;
	public AnalogInput encoder;
	public CANPIDController canController1;


	public SwerveModule(int swerveModIndex, boolean... angleMotorInverted) {
		motor1 = new CANSparkMax(swerveModIndex*2-1, MotorType.kBrushless);
		motor2 = new CANSparkMax(swerveModIndex*2, MotorType.kBrushless);
		motor1.setInverted(angleMotorInverted[0]);
		motor2.setInverted(angleMotorInverted[1]);
		encoder = new AnalogInput(swerveModIndex-1);

		pidConstants = RobotMap.SWERVE_PID_CONSTANTS[swerveModIndex-1];
		// pidController1 = new PIDController(pidConstants[0], pidConstants[1], pidConstants[2], encoder, motor1, RobotMap.SWERVE_LOOP_TIME);

		// pidController1.setInputRange(0.0, RobotMap.SWERVE_ENC_CIRC);
		// pidController1.setOutputRange(-1.0, 1.0);
		// pidController1.setContinuous(true); /* */
		// pidController1.setAbsoluteTolerance(RobotMap.SWERVE_PID_TOLERANCE);
		// pidController1.enable();

		// canController1 = new CANPIDController(motor1);
		canController1 = motor1.getPIDController();
		canController1.setP(pidConstants[0]); canController1.setI(pidConstants[1]); canController1.setD(pidConstants[2]); canController1.setFF(pidConstants[3]);
		canController1.setOutputRange(-1.0, 1.0);
		canController1.setSmartMotionAllowedClosedLoopError(RobotMap.SWERVE_PID_TOLERANCE, 0);

		// pidController2 = new PIDController(pidConstants[0], pidConstants[1], pidConstants[2], encoder, motor2, RobotMap.SWERVE_LOOP_TIME);

		// pidController2.setInputRange(0.0, RobotMap.SWERVE_ENC_CIRC);
		// pidController2.setOutputRange(-1.0, 1.0);
		// pidController2.setContinuous(true); /* */
		// pidController2.setAbsoluteTolerance(RobotMap.SWERVE_PID_TOLERANCE);
		// pidController2.enable();

	}


	// angle and speed should be from -1.0 to 1.0, like a joystick input
	public void drive (double speed, double angle) {
		// pidController1.setSetpoint(angle);
		canController1.setReference(angle, ControlType.kPosition, 0);

		// angle =
		// pidController2.setSetpoint(-angle); //could work but process unknown

		// motor2.follow(motor1,true); //this just makes it go the wheel dir.

		// motor2.set(pidController1.get()*speed); //best guess for now
		motor2.set(motor1.get()*speed); //maybe?


		// error1 = pidController1.getError();
		// output1 = pidController1.get();
	}

    public void initDefaultCommand() {
			// NOTE: no default command unless running swerve modules seperately
    }
}
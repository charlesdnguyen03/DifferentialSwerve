package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
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


	public SwerveModule(int swerveModIndex, boolean... angleMotorInverted) {
		motor1 = new CANSparkMax(swerveModIndex*2-1, MotorType.kBrushless);
		motor2 = new CANSparkMax(swerveModIndex*2, MotorType.kBrushless);
		motor1.setInverted(angleMotorInverted[0]);
		motor2.setInverted(angleMotorInverted[1]);
		encoder = new AnalogInput(swerveModIndex-1);

		pidConstants = RobotMap.SWERVE_PID_CONSTANTS[swerveModIndex-1];
		// pidController = new PIDController(pidConstants[0], pidConstants[1], pidConstants[2], encoder, motor2, RobotMap.SWERVE_LOOP_TIME);
		pidController1 = new PIDController(pidConstants[0], pidConstants[1], pidConstants[2], encoder, motor1, RobotMap.SWERVE_LOOP_TIME);

		pidController1.setInputRange(0.0, RobotMap.SWERVE_ENC_CIRC);
		pidController1.setOutputRange(-1.0, 1.0);
		pidController1.setContinuous(true); /* */
		pidController1.setAbsoluteTolerance(RobotMap.SWERVE_PID_TOLERANCE);
		pidController1.enable();

		pidController2 = new PIDController(pidConstants[0], pidConstants[1], pidConstants[2], encoder, motor2, RobotMap.SWERVE_LOOP_TIME);

		pidController2.setInputRange(0.0, RobotMap.SWERVE_ENC_CIRC);
		pidController2.setOutputRange(-1.0, 1.0);
		pidController2.setContinuous(true); /* */
		pidController2.setAbsoluteTolerance(RobotMap.SWERVE_PID_TOLERANCE);
		pidController2.enable();

	}


	// angle and speed should be from -1.0 to 1.0, like a joystick input
	public void drive (double speed, double angle) {
		pidController1.setSetpoint(angle);
		// angle =
		pidController2.setSetpoint(-angle);
		
		error = pidController1.getError();
		output = pidController1.get();
	}

    public void initDefaultCommand() {
			// NOTE: no default command unless running swerve modules seperately
    }
}
package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import frc.robot.subsystems.SwerveModule;

public class RobotMap {
	public static double kP = 1;
	public static double SWERVE_ENC_CIRC = 4.927;
	public static final double[] SWERVE_SETPOINT_OFFSET = {0.7, 4.72, 3.2825, 3.456};
	public static double[][] SWERVE_PID_CONSTANTS = {{kP, 0.0, 0.0}, {kP, 0.0, 0}, {kP, 0.0, 0}, {kP, 0.0, 0}};
	public static boolean[] ANGLE_MOTOR_INVERTED = {true, false, false, false};
	//Constants
	public static double JOYSTICK_DEADBAND = 0.05;
	public static double TRIGGER_DEADBAND = 0.2;

	//swerve
	public static SwerveModule[] swerveMod;
	public static double MAX_VEL = 10;
    public static double MAX_ACC = 5;
	public static double WHEELBASE_WIDTH = 0.8;
	public static double WHEELBASE_LENGTH = 0.8;
	public static double SWERVE_WHEEL_DIAMETER = 0.05; // in m?
	public static int SWERVE_MAX_CURRENT = 30; // in amps
	public static int SWERVE_CURRENT_DUR = 100; // in ms
	public static double SWERVE_LENGTH = 21.5;
	public static double SWERVE_WIDTH = 21.5;
	public static double SWERVE_RADIUS = Math.sqrt(Math.pow(SWERVE_LENGTH, 2) + Math.pow(SWERVE_WIDTH, 2));
	public static double SWERVE_LOOP_TIME = 0.100; // in ms (50 ms default)
	public static double SWERVE_PID_TOLERANCE = SWERVE_ENC_CIRC / 100.0 / 10.0; // .25% .1%
	
	public static AHRS gyro;

	public static void init() {
		// FR = 0, BR = 1, BL = 2, FL = 3
		swerveMod = new SwerveModule[4];
		for(int i = 0; i < 4; i++) swerveMod[i] = new SwerveModule(i+1, ANGLE_MOTOR_INVERTED[i]);

		gyro = new AHRS(SPI.Port.kMXP);
	}
}
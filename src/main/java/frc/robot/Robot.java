package frc.robot;

// package org.usfirst.frc.team2557.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.drive.OpenLoop;
import frc.robot.subsystems.GyroSwerveDrive;
import frc.robot.subsystems.SwerveModule;

public class Robot extends TimedRobot {
	public static OI m_oi;
	public static GyroSwerveDrive gyroSwerveDrive;
	public static SwerveModule sModule;
	
	public static OpenLoop oL;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser;

	@Override
	public void robotInit() {
		RobotMap.init();

		gyroSwerveDrive = new GyroSwerveDrive();

		oL = new OpenLoop();

		// NOTE: oi MUST be constructed after subsystems
		m_oi = new OI();
		m_chooser = new SendableChooser<>();

		m_chooser.addOption("Default Auto", null);
		// m_chooser.addOption("My Auto", new Segment1());
		SmartDashboard.putData("Auto mode", m_chooser);

	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {

		for(int i = 0; i < 4; i++) RobotMap.swerveMod[i].encoder.pidGet();
		RobotMap.gyro.reset();
		
		m_autonomousCommand = m_chooser.getSelected();
		if (m_autonomousCommand != null) m_autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		smartdashboarding();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		if (m_autonomousCommand != null) m_autonomousCommand.cancel();
		oL.start();
	}

	@Override
	public void teleopPeriodic() {
		smartdashboarding();
		Scheduler.getInstance().run();
	}



	public void smartdashboarding(){
		for (int i = 0; i < 4; i++) {
			SmartDashboard.putNumber("SwerveMod" + i, RobotMap.swerveMod[i].encoder.pidGet());
		}
	}

	@Override
	public void testPeriodic() {
	}
}

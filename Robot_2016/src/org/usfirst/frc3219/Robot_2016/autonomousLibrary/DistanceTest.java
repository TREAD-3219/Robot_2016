package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DistanceTest extends Command {
	private static final double DISTANCE_TEST_DEFAULT_DISTANCE = 48.0;
	private static final double DISTANCE_TEST_DEFAULT_SPEED = 24.0;
	private static final String DISTANCE_TEST_DISTANCE_IN = "Distance Test Distance (In)";
	private static final String DISTANCE_TEST_SPEED_IPS = "Distance Test Speed (IPS)";
	private static final double DISTANCE_TEST_TIMEOUT = 3.0;
	
	private double speed;
	private double distance;
	private double startDistance = 0.0;
	private double startTime = 0.0;
	
	public DistanceTest() {
		super("DistanceTest", DISTANCE_TEST_TIMEOUT);
		speed = DISTANCE_TEST_DEFAULT_SPEED;
		distance = DISTANCE_TEST_DEFAULT_DISTANCE;
		SmartDashboard.putNumber(DISTANCE_TEST_SPEED_IPS, speed);
		SmartDashboard.putNumber(DISTANCE_TEST_DISTANCE_IN, distance);
		startDistance = 0.0;
		startTime = 0.0;
	}
	
	@Override
	protected void initialize() {
		speed = SmartDashboard.getNumber(DISTANCE_TEST_SPEED_IPS, DISTANCE_TEST_DEFAULT_SPEED);
		distance = SmartDashboard.getNumber(DISTANCE_TEST_DISTANCE_IN, DISTANCE_TEST_DEFAULT_DISTANCE);
		startDistance = Robot.drive.getAvgEncoderDist();
		startTime = Timer.getFPGATimestamp();
		this.setTimeout(DISTANCE_TEST_TIMEOUT);
		Robot.drive.driveSpeed(speed, 0.0);
	}

	@Override
	protected void execute() {
		
		Robot.drive.driveSpeed(speed, 0.0);
	}

	@Override
	protected boolean isFinished() {
		double distanceNow = Robot.drive.getAvgEncoderDist();
		double deltaDistance = Math.abs(distanceNow - startDistance);
		return deltaDistance > distance || this.isTimedOut();
	}

	@Override
	protected void end() {
		Robot.drive.driveValues(0.0, 0.0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}

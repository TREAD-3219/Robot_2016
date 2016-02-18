package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class AutoMoat extends Command {
	double aveDistI = 0.0;
	double aveDistF = 0.0;
	private static final double MIN_ENCODER_DISTANCE = 60;

	@Override
	protected void end() {
		//RobotMap.driveLeftDrive.enableBrakeMode(true);
		//RobotMap.driveRightDrive.enableBrakeMode(true);
		
	}

	@Override
	protected void execute() {
		Robot.drive.driveValues(0.6, 0.0);
	}

	@Override
	protected void initialize() {
		double encoderInitialLeft = RobotMap.driveEncoderLeft.getDistance();
		double encoderInitialRight = RobotMap.driveEncoderRight.getDistance();
		aveDistI = (encoderInitialRight + encoderInitialLeft) / 2;
		Robot.drive.driveValues(0.6, 0.0);

	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		double encoderFinalLeft = RobotMap.driveEncoderLeft.getDistance();
		double encoderFinalRight = RobotMap.driveEncoderRight.getDistance();
		aveDistF = (encoderFinalRight + encoderFinalLeft) / 2;
		if(Robot.sensors.getTip() <= 3.5 && aveDistF - aveDistI >= MIN_ENCODER_DISTANCE) {
			return true;
		} else
		return false;
	}
}

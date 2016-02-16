package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
public class AutoRWall extends Command {
	double InitialEncoderAVG = 0.0;
	double FinalEncoderAVG = 0.0;

	@Override
	protected void end() {
		if (isFinished()) {
			Robot.drive.driveValues(0.0, 0.0);
		}
	}

	@Override
	protected void execute() {
		Robot.drive.driveValues(.4, 0.0);
		
	}

	@Override
	protected void initialize() {
		double EncoderInitialRight = RobotMap.driveEncoderRight.getDistance();
		double EncoderInitialLeft = RobotMap.driveEncoderLeft.getDistance();
		InitialEncoderAVG = ((EncoderInitialLeft + EncoderInitialRight) / 2);
		Robot.drive.driveValues(.4, 0.0);
		
	}

	@Override
	protected void interrupted() {
		this.end();
		
	}

	@Override
	protected boolean isFinished() {
		double EncoderFinalRight = RobotMap.driveEncoderRight.getDistance();
		double EncoderFinalLeft = RobotMap.driveEncoderLeft.getDistance();
		FinalEncoderAVG = ((EncoderFinalLeft + EncoderFinalRight) / 2);
	if (Robot.sensors.getTip() > Math.abs(3.0) && FinalEncoderAVG < 3);
		return false;
	}
}

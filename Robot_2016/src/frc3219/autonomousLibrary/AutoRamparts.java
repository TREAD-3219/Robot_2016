package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.commands.AutoStraightCommand;

public class AutoRamparts extends AutoStraightCommand {
	double aveDistI = 0.0;
	double aveDistF = 0.0;
	private static final double MIN_ENCODER_DISTANCE = 60;
	
	@Override
	protected void end() {
		}

	@Override
	protected void execute() {
		gyroStraight(0.5);
		
	}

	@Override
	protected void initialize() {
		aveDistI = Robot.sensors.getAvgEncoderDist();
		gyroStraight(0.5);
		
		
	}

	@Override
	protected void interrupted() {
		this.end();
		
	}

	@Override
	protected boolean isFinished() {
		aveDistF = Robot.sensors.getAvgEncoderDist();
		if (Robot.sensors.getTip() <= 5.0 && aveDistF - aveDistI >= MIN_ENCODER_DISTANCE) {
			return true;
		} else
		return false;
	}
}

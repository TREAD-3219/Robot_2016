package org.usfirst.frc3219.Robot_2016.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SallyDoorOpen extends Command{
	double avDistI = 0.0;
	double avDistF = 0.0;
	private static final double MIN_ENCODER_DISTANCE = -30;
	//rand will touch the sallyport
	//sallyport command 1
	//lift arms up a little after this
	private static final double SALLYBACKSPEED = -0.45;
	private static final double SALLYTURNSPEED = -0.49;
	
	@Override
	protected void end() {
		Robot.drive.setBrakesOn();
		Robot.drive.driveValues(0, 0);
	}

	@Override
	protected void execute() {
		Robot.drive.driveValues(SALLYBACKSPEED, SALLYTURNSPEED);
	}

	@Override
	protected void initialize() {
		avDistI = Robot.drive.getAvgEncoderDist();
		Robot.drive.driveValues(SALLYBACKSPEED, SALLYTURNSPEED);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		avDistF = Robot.drive.getAvgEncoderDist();
		if(avDistF - avDistI <= MIN_ENCODER_DISTANCE) {
			return true;
		}		
		
		return false;
	}
}

package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class SallyDoorOpen extends Command{
	double avDistI = 0.0;
	double avDistF = 0.0;
	private static final double MIN_ENCODER_DISTANCE = -30;
	//rand will touch the sallyport
	//sallyport command 1
	//lift arms up a little after this
	
	@Override
	protected void end() {
		RobotMap.driveLeftDriveA.enableBrakeMode(true);
		RobotMap.driveLeftDriveB.enableBrakeMode(true);
		RobotMap.driveRightDriveA.enableBrakeMode(true);
		RobotMap.driveRightDriveB.enableBrakeMode(true);
		Robot.drive.driveValues(0, 0);
	}

	@Override
	protected void execute() {
		Robot.drive.driveValues(-0.45, -0.49);
	}

	@Override
	protected void initialize() {
		avDistI = Robot.sensors.aveDistEncoders();
		Robot.drive.driveValues(-0.45, -0.49);
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		avDistF = Robot.sensors.aveDistEncoders();
		if(avDistF - avDistI <= MIN_ENCODER_DISTANCE) {
			return true;
		}		
		
		return false;
	}
}

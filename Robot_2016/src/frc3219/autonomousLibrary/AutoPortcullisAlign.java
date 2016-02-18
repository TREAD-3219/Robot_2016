package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

/**
 *
 */
public class AutoPortcullisAlign extends AutoStraightCommand {
	double initAccelX;
	double initAccelY;
	public static final double JERK_THRESHOLD = 0.5;
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	gyroStraight(0.4);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gyroStraight(0.4);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double finAccelX = Robot.sensors.navx.getWorldLinearAccelX();
		double finAccelY = Robot.sensors.navx.getWorldLinearAccelY();
		double xJerk = Math.abs(finAccelX - initAccelX);
		double yJerk = Math.abs(finAccelY - initAccelY);
		if(xJerk > JERK_THRESHOLD || yJerk > JERK_THRESHOLD) {
			return true;
		} else {
			return false;
		}
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveLeftDrive.stopMotor();
    	RobotMap.driveRightDrive.stopMotor();
    	gyroStraight(0.0);
    	//not sure if stopmotors applies brakes
    	//if it does not, change it to applying brakes
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}

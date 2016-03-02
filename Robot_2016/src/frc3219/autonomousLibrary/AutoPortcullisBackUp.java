package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

/**
 *
 */
public class AutoPortcullisBackUp extends AutoStraightCommand {

	public static final double BACKUPSPEED = -0.2;
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	gyroStraight(BACKUPSPEED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	gyroStraight(BACKUPSPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.multiTool.readUpperMultiToolLimitSwitch() || (Robot.sensors.aveDistEncoders() > 32)){
    		return true;
    	} else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveDriveMotors.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}

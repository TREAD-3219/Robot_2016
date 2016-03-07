package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
//forward then lower multi-tool, move forward with multi-tool under the portcullis
//raise portcullis, then drive under
public class AutoPortcullisRaise extends Command { 
	//while finishing the raise, then drive forward and let the portcullis slide along the top of the frame

	private static final double SPEED_DELTA = 0.1;
	private static final double UPPER_SPEED_LIMIT = 0.5;
	private static final double LOW_SPEED_LIMIT = 0.3;
	static final String ARM_RAISE_SPEED = "Arm Move Speed";
	static final double ARMSPEED = 0.3;
	double currentArmSpeed = ARMSPEED;
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	currentArmSpeed = ARMSPEED;
		Robot.multiTool.driveArmUpDown(currentArmSpeed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber(ARM_RAISE_SPEED, Robot.multiTool.armEncoderSpeed());
    	if (Robot.multiTool.armEncoderSpeed() < LOW_SPEED_LIMIT) {
    		currentArmSpeed += SPEED_DELTA;
    	} else if (Robot.multiTool.armEncoderSpeed() > UPPER_SPEED_LIMIT) {
    		currentArmSpeed -= SPEED_DELTA;
    	}
    	
    	Robot.multiTool.driveArmUpDown(currentArmSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.multiTool.getUpperLimitSwitch();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.multiTool.driveArmHold();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}

package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
//forward then lower multi-tool, move forward with multi-tool under the portcullis
//raise portcullis, then drive under
public class AutoPortcullisRaise extends Command { 
	//while finishing the raise, then drive forward and let the portcullis slide along the top of the frame

	public static final double ARMSPEED = 0.3;
	
    // Called just before this Command runs the first time
    protected void initialize() {
		Robot.multiTool.driveArmUpDown(ARMSPEED);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.multiTool.driveArmUpDown(ARMSPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.multiTool.readUpperMultiToolLimitSwitch()){
    		return true;
    	}else{
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveMultiToolArmMotor.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}

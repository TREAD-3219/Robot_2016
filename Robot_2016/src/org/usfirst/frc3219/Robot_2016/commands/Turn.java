// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3219.Robot_2016.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3219.Robot_2016.Robot;

/**
 *
 */
public class Turn extends Command {
    private double m_Angle;
    private double speed;

    public Turn(double Angle, double turnSpeed) {
    	speed = turnSpeed;
        m_Angle = Angle;
        requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.driveSpeed(0.0, speed);
    	double timeout = Math.abs(m_Angle / speed);
    	this.setTimeout(timeout);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	boolean timedOut = this.isTimedOut();
        return timedOut;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.driveSpeed(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

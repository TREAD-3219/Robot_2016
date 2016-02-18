package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DropArms extends Command {
	
	double P = 0.1;
	double I = 0.001;
	double D = 0.0;

	@Override
	protected void end() {
		Robot.multiTool.getPIDController().disable();
		Robot.multiTool.driveArmUpDown(0.0);

	}

	@Override
	protected void execute() {

	}

	@Override
	protected void initialize() {
		this.setTimeout(1.5); // Calibrate!!!
		Robot.multiTool.armSetPoint("DRAWBRIDGE_START");
		Robot.multiTool.getPIDController().enable();
		Robot.multiTool.getPIDController().setPID(P, I, D);
		Robot.multiTool.setPercentTolerance(5);

	}

	@Override
	protected void interrupted() {
		this.end();

	}

	@Override
	protected boolean isFinished() {
		if (this.isTimedOut() || Robot.multiTool.getPIDController().onTarget()) {
			return true;
		} else {
			return false;
		}
	}
}

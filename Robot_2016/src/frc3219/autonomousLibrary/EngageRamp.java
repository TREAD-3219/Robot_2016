package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

public class EngageRamp extends AutoStraightCommand {

	String whichArmPreset;

	@Override
	protected void end() {
		Robot.multiTool.getPIDController().disable();
	}

	@Override
	protected void execute() {
		gyroStraight(0.6);
		if (whichArmPreset.equalsIgnoreCase("Rough Terrain")) {
			Robot.multiTool.armSetPoint("STOW");
		} else if (whichArmPreset.equalsIgnoreCase("Rock Wall")) {
			Robot.multiTool.armSetPoint("STOW");
		} else if (whichArmPreset.equalsIgnoreCase("Portcullis")) {
			Robot.multiTool.armSetPoint("PORTCULLIS_START");
		} else if (whichArmPreset.equalsIgnoreCase("Moat")) {
			Robot.multiTool.armSetPoint("STOW");
		} else if (whichArmPreset.equalsIgnoreCase("Ramparts")) {
			Robot.multiTool.armSetPoint("STOW");
		} else if (whichArmPreset.equalsIgnoreCase("Sally Port")) {
			Robot.multiTool.armSetPoint("STOW");
		} else if (whichArmPreset.equalsIgnoreCase("Drawbridge")) {
			Robot.multiTool.armSetPoint("DRAWBRIDGE_START");
		} else if (whichArmPreset.equalsIgnoreCase("Chival de Frise")) {
			Robot.multiTool.armSetPoint("CHIVAL_DE_FRISE_START");
		}
	}

	@Override
	protected void initialize() {
		//Robot.multiTool.getPIDController().enable();
		gyroStraight(0.6);
		whichArmPreset = (String) Robot.oi.autoDefenseChooser.getSelected();
		if (whichArmPreset.equalsIgnoreCase("Rough Terrain")) {
			Robot.multiTool.armSetPoint("STOW");
		} else if (whichArmPreset.equalsIgnoreCase("Rock Wall")) {
			Robot.multiTool.armSetPoint("STOW");
		} else if (whichArmPreset.equalsIgnoreCase("Portcullis")) {
			Robot.multiTool.armSetPoint("PORTCULLIS_START");
		} else if (whichArmPreset.equalsIgnoreCase("Moat")) {
			Robot.multiTool.armSetPoint("STOW");
		} else if (whichArmPreset.equalsIgnoreCase("Ramparts")) {
			Robot.multiTool.armSetPoint("STOW");
		} else if (whichArmPreset.equalsIgnoreCase("Sally Port")) {
			Robot.multiTool.armSetPoint("STOW");
		} else if (whichArmPreset.equalsIgnoreCase("Drawbridge")) {
			Robot.multiTool.armSetPoint("DRAWBRIDGE_START");
		} else if (whichArmPreset.equalsIgnoreCase("Chival de Frise")) {
			Robot.multiTool.armSetPoint("CHIVAL_DE_FRISE_START");
		}
	}

	@Override
	protected void interrupted() {
		this.end();
	}

	@Override
	protected boolean isFinished() {
		if (Robot.sensors.getTip() >= 5.0) {
			return true;
		} else
			return false;
	}
}

package org.usfirst.frc3219.Robot_2016.commands;

import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoCenterToGoal;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// Counter does not work for this command, only for ManualShoot and RunShooter.
public class AutoShoot extends CommandGroup {
	// This is for the full feed and shooting mechanisms
	
	public static final String AUTO_SHOOT_START_TAG = "AutoShootStart";

	public AutoShoot() {
		createShooterCommands();
	}
	
	public void createShooterCommands() {
		//this.addParallel(new SetMultiToolPoint(MultiTool.SHOOT_POSITION));
		this.addSequential(new SetVelocity());
		this.addParallel(new AutoCenterToGoal());
		this.addParallel(new ShootBoulder());

	}
	
	@Override
	protected void initialize() {
		super.initialize();
		SmartDashboard.putBoolean(AUTO_SHOOT_START_TAG, true);
	}
}
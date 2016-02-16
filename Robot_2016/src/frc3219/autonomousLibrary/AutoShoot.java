package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import org.usfirst.frc3219.Robot_2016.commands.FeedShooter;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoShoot extends CommandGroup { //This is for the full feed and shooting mechanisms
	
	public AutoShoot() {
		requires(Robot.shooter);
		createShooterCommands();
	}
	
	public void createShooterCommands() {
		this.addSequential(new RunShooter(1.0));
		this.addSequential(new FeedShooter());
		this.addSequential(new StopShooter());
	}
	
}

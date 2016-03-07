package org.usfirst.frc3219.Robot_2106.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;


   // Parameters: Starting Position, Defense directing in front of Robot.

public class AutonomousMain extends CommandGroup {
	// Break Defense methods.
	public AutonomousMain() {
		
	Robot.drive.setBrakesOff();

	//this.addSequential(new AutoDrive(100.0, SmartDashboard.getNumber("AUTO_SPEED_ENGAGE_RAMP", 0.0))); // From edge of starting line to edge of front defense.
	this.addSequential(new EngageRamp());
	this.addSequential(new AutoRWall());
	//this.addSequential(new AutoDrive(3, 0.5));
	
	//this.addSequential(new OnRamp());
	//this.addSequential(new AutoStraighten()); NEEDS TESTING, DOES NOT WORK YET
	
	Robot.drive.setBrakesOn();
	
	// FOR DRAWBRIDGE:
	// First EngageDrawbridgeRamp --> parallel(EngageDrawBridge/dropArms) --> parallel(DrawbridgeExecuteArms/DrawbridgePID_Back) --> DriveOverDrawbridge --> ??.
	
	// FOR MOAT:
	// First EngageRamp --> Moat --> ??.
	
	// FOR AUTOROUGH:
	// First EngageRamp --> AutoRough --> ??
	
	// FOR AUTORAMPARTS:
	// First EngageRamp --> AutoRampart --> ??
	
	// FOR AUTORWALL:
	// First EngageRamp --> AutoRWall --> ??
	}
}

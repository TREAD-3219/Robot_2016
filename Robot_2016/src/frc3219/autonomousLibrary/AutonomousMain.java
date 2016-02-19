package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



   // Parameters: Starting Position, Defense directing in front of Robot.

public class AutonomousMain extends CommandGroup {
	// Break Defense methods.
	public AutonomousMain() {
		
	org.usfirst.frc3219.Robot_2016.Robot.drive.setBreaksOff();

	//this.addSequential(new AutoDrive(100.0, SmartDashboard.getNumber("AUTO_SPEED_ENGAGE_RAMP", 0.0))); // From edge of starting line to edge of front defense.
	this.addSequential(new EngageRamp());
	this.addSequential(new AutoRamparts());
	//this.addSequential(new AutoDrive(3, 0.5));
	
	//this.addSequential(new OnRamp());
	//this.addSequential(new AutoStraighten()); NEEDS TESTING, DOES NOT WORK YET
	
	org.usfirst.frc3219.Robot_2016.Robot.drive.setBreaksOn();
	
	// FOR DRAWBRIDGE:
	// First EngageDrawbridgeRamp --> parallel(EngageDrawBridge/dropArms) --> parallel(DrawbridgeExecuteArms/DrawbridgePID_Back) --> DriveOverObstacle --> AutoTurnAndShoot.

	// FOR MOAT:
	// First EngageRamp --> Moat --> AutoTurnAndShoot
	
	// FOR AUTOROUGH:
	// First EngageRamp --> AutoRough --> AutoTurnAndShoot
	
	// FOR AUTORAMPARTS:
	// First EngageRamp --> AutoRampart --> AutoTurnAndShoot
	
	// FOR AUTORWALL:
	// First EngageRamp --> AutoRWall --> AutoTurnAndShoot

	// FOR PORTCULLIS:
	// First EngagePortcullisRamp --> AutoPortcullisAlign --> parallel(AutoPortcullisRaise/AutoPortcullisBackUp) --> DriveOverObstacle --> AutoTurnAndShoot.

	}
}

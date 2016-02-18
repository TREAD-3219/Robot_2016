package frc3219.autonomousLibrary;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc3219.Robot;
import frc3219.RobotMap;


   // Parameters: Starting Position, Defense directing in front of Robot.

public class AutonomousMain extends CommandGroup {
	// Break Defense methods.
	public AutonomousMain() {
//	RobotMap.driveLeftDrive.enableBrakeMode(false);
//	RobotMap.driveRightDrive.enableBrakeMode(false);

	//this.addSequential(new AutoDrive(100.0, SmartDashboard.getNumber("AUTO_SPEED_ENGAGE_RAMP", 0.0))); // From edge of starting line to edge of front defense.
	this.addSequential(new EngageRamp());
	this.addSequential(new AutoRamparts());
	//this.addSequential(new AutoDrive(3, 0.5));
	
	//this.addSequential(new OnRamp());
	//this.addSequential(new AutoStraighten()); NEEDS TESTING, DOES NOT WORK YET
	
//	RobotMap.driveLeftDrive.enableBrakeMode(true);
//	RobotMap.driveRightDrive.enableBrakeMode(true);
	
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

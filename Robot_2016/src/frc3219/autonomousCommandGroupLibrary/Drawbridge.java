package frc3219.autonomousCommandGroupLibrary;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoShoot;
import frc3219.autonomousLibrary.AutoTurnAndShoot;
import frc3219.autonomousLibrary.DrawbridgeExecuteArms;
import frc3219.autonomousLibrary.DrawbridgePID_Back;
import frc3219.autonomousLibrary.DriveOverObstacle;
import frc3219.autonomousLibrary.DropArms;
import frc3219.autonomousLibrary.EngageDrawbridge;
import frc3219.autonomousLibrary.EngageRamp;

/**
 *
 */
public class Drawbridge extends CommandGroup {
    
    public  Drawbridge() {
    	this.addSequential(new EngageRamp());
    	this.addSequential(new EngageDrawbridge());
    	this.addParallel(new DropArms());
    	this.addSequential(new DrawbridgeExecuteArms());
    	this.addParallel(new DrawbridgePID_Back());
    	this.addSequential(new DriveOverObstacle());
        this.addSequential(new AutoTurnAndShoot());
    	this.addSequential(new AutoShoot());
    }
}
//FOR DRAWBRIDGE:
	// First EngageRamp --> parallel(EngageDrawBridge/DropArms) --> parallel(DrawbridgeExecuteArms/DrawbridgePID_Back) --> DriveOverObstacle --> AutoTurnAndShoot
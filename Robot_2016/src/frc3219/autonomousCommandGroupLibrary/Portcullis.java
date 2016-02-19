package frc3219.autonomousCommandGroupLibrary;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoPortcullisAlign;
import frc3219.autonomousLibrary.AutoPortcullisBackUp;
import frc3219.autonomousLibrary.AutoPortcullisRaise;
import frc3219.autonomousLibrary.DriveOverObstacle;
import frc3219.autonomousLibrary.EngageRamp;

/**
 *
 */
public class Portcullis extends CommandGroup {
    
    public  Portcullis() {
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoPortcullisAlign());
    	this.addSequential(new AutoPortcullisRaise());
    	this.addParallel(new AutoPortcullisBackUp());
    	this.addSequential(new DriveOverObstacle());
    	//this.addSequential(new AutoTurnAndShoot());
    }
}

//FOR PORTCULLIS:
	// First EngageRamp --> AutoPortcullisAlign --> parallel(AutoPortcullisRaise/AutoPortcullisBackUp) --> DriveOverObstacle --> AutoTurnAndShoot
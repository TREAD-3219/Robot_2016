package frc3219.autonomousCommandGroupLibrary;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.AutoChivalDeFrise;
import frc3219.autonomousLibrary.AutoRotate;
import frc3219.autonomousLibrary.AutoShoot;
import frc3219.autonomousLibrary.AutoTurnAndShoot;
import frc3219.autonomousLibrary.DriveOverObstacle;
import frc3219.autonomousLibrary.DropArms;
import frc3219.autonomousLibrary.EngageRamp;

/**
 *
 */
public class ChivalDeFrise extends CommandGroup {
    
    public  ChivalDeFrise() { //uses dead reckoning for AutoChivalDeFrise
    	this.addSequential(new EngageRamp());
    	this.addSequential(new DropArms());
    	this.addSequential(new AutoChivalDeFrise());
    	this.addSequential(new AutoRotate());
    	this.addSequential(new AutoTurnAndShoot());
    	this.addSequential(new AutoShoot());
    }
}

// FOR CHIVAL DE FRISE:
// First EngageRamp --> AutoChivalDeFrise --> DriveOverObstacle --> AutoTurnAndShoot
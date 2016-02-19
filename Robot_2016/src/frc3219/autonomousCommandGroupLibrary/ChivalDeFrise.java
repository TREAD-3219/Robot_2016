package frc3219.autonomousCommandGroupLibrary;

import org.usfirst.frc3219.Robot_2016.commands.AutoChivalDeFrise;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc3219.autonomousLibrary.DriveOverObstacle;
import frc3219.autonomousLibrary.EngageRamp;

/**
 *
 */
public class ChivalDeFrise extends CommandGroup {
    
    public  ChivalDeFrise() { //uses dead reckoning for AutoChivalDeFrise
    	this.addSequential(new EngageRamp());
    	this.addSequential(new AutoChivalDeFrise());
    	this.addSequential(new DriveOverObstacle());
    	//this.addSequential(new AutoTurnAndShoot());
    }
}

// FOR CHIVAL DE FRISE:
// First EngageRamp --> AutoChivalDeFrise --> DriveOverObstacle --> AutoTurnAndShoot
package frc3219.autonomousLibrary;



import edu.wpi.first.wpilibj.command.Command;



/**
 *
 */
//forward then lower multi-tool, move forward with multi-tool under the portcullis
//raise portcullis, then drive under
public class AutoPortcullisRaise extends Command {
	
    public AutoPortcullisRaise() {
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		//pid controller with "PORTCULLIS" as variable
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//pid controller with "PORTCULLIS" as variable
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//if hits upper limit switch return true else return false
    		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//call pid controller with 0
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}

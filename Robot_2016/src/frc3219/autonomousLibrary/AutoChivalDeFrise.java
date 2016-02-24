package frc3219.autonomousLibrary;

import org.usfirst.frc3219.Robot_2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoChivalDeFrise extends Command {
	double invert;

	@Override
	protected void end() {

	}

	@Override
	protected void execute() {

	}

	@Override
	protected void initialize() {
		invert = 1;
		System.out.print("init.ChevelDeFrise");
		// TODO set up all of these values to be okay.
		Robot.drive.driveValues(0.2 * invert, 0);// move forward a little
		this.setTimeout(0.2);// wait
		Robot.drive.driveValues(0, 0);// stop
		Robot.multiTool.driveArmUpDown(0.5 * invert);// move the arm down a little FINAL
		this.setTimeout(0.2);// wait
		Robot.multiTool.driveArmUpDown(0);// stop
		this.setTimeout(0.2);//wait
		Robot.drive.driveValues(0.6, 0); // move across the ramp
		Robot.multiTool.driveArmUpDown(-0.3 * invert);// raise le arm
		this.setTimeout(0);// wait
		Robot.drive.driveValues(0, 0);//stop
		Robot.multiTool.driveArmUpDown(0);//stop

		// positive makes the arm lower, and vice versa.

		// Robot.multiTool.driveArmUpDown(-0.5 * invert); **Might need to use a
		// little bit of reverse, or stop early
		// Robot.drive.driveValues(forward, turnRate);
		/*
		 *drive a little bit more forward
		 *bring down arm onto p
		 *move forward
		 *bring up arm a small amount
		 *move forward a little more
		 *bring arm up all the way
		 *shift center of balance forward so far that you tilt forward "slowly" (subjective)
		 *once the plate hits the ground, move forward
		 *sense when you get level
		 *le done
		 *nice meme
		 */
	}

	@Override
	protected void interrupted() {

	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}

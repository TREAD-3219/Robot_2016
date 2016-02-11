// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3219.Robot_2016;

import org.usfirst.frc3219.Robot_2016.commands.AutoCenterToGoal;
import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;
import org.usfirst.frc3219.Robot_2016.commands.Autonomous;
import org.usfirst.frc3219.Robot_2016.commands.FeedShooter;
import org.usfirst.frc3219.Robot_2016.commands.RollerMotor;
import org.usfirst.frc3219.Robot_2016.commands.Shooting_GC;
import org.usfirst.frc3219.Robot_2016.commands.Shooting_Joy;
import org.usfirst.frc3219.Robot_2016.commands.Turn;
import org.usfirst.frc3219.Robot_2016.commands.goFoward;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	// IMPORTANT! controls for robot:
	// Left joystick - multiTool down/up
	// right joystick - Shooter speed
	// A button - feed shooter a ball
	// B button - activate multiTool roller
	// X button - shoot wheel with a single button
	private static boolean RollerMotorIsFinished;
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public Joystick driveStick;
	public JoystickButton shoot;
	public Joystick joystick;
	public JoystickButton shoot_Alt;
	public Joystick gameController;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public OI() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		driveStick = new Joystick(0);
		gameController = new Joystick(1);
		joystick = new Joystick(0);

		shoot_Alt = new JoystickButton(gameController, 1);
		shoot_Alt.whileHeld(new Shooting_GC());

		Button buttonB = new JoystickButton(gameController, 2);
		Button buttonA = new JoystickButton(gameController, 1);
		Button buttonX = new JoystickButton(gameController, 3);
		Button leftTrigger = new JoystickButton(gameController, 7); // Test

		shoot = new JoystickButton(joystick, 1);
		shoot.whileHeld(new Shooting_Joy());
		// buttonB.whileHeld(new RollerMotor());
		buttonA.whileHeld(new FeedShooter());
		buttonX.whenPressed(new AutoShoot());
		leftTrigger.whenPressed(new AutoCenterToGoal());

		// SmartDashboard Buttons
		SmartDashboard.putData("Shooting_Joy", new Shooting_Joy());
		SmartDashboard.putData("Shooting_GC", new Shooting_GC());
		SmartDashboard.putData("Turn: Default", new Turn(90, 10));
		SmartDashboard.putData("Autonomous", new Autonomous());
		SmartDashboard.putData("goFoward: goFoward", new goFoward(1, 10, 10));

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
	public Joystick getJoystick() {
		return joystick;
	}

	public Joystick getGameController() {
		return gameController;
	}

	public Joystick getDriveStick() {
		return driveStick;
	}

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

package org.usfirst.frc3219.Robot_2016;

import org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary.ChevalDeFrise;
import org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary.Drawbridge;
import org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary.Moat;
import org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary.NoDefense;
import org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary.Portcullis;
import org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary.Ramparts;
import org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary.RockWall;
import org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary.RoughTerrain;
import org.usfirst.frc3219.Robot_2016.autonomousCommandGroupLibrary.SallyPort;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoCenterToGoal;
import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;
import org.usfirst.frc3219.Robot_2016.commands.IntakeBall;
import org.usfirst.frc3219.Robot_2016.commands.ManualFeed;
import org.usfirst.frc3219.Robot_2016.commands.ManualShoot;
import org.usfirst.frc3219.Robot_2016.commands.ResetArm;
import org.usfirst.frc3219.Robot_2016.commands.ReverseCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	private static final String AUTO_START_POSITION = "Auto Start Position";
    public static final String DEFENSE_CHOOSER = "Defense Chooser";
	public static final String CHEVAL_DE_FRISE = "Cheval de Frise";
	public static final String DRAWBRIDGE = "Drawbridge";
	public static final String MOAT = "Moat";
	public static final String NO_DEFENSE = "No Defense";
	public static final String PORTCULLIS = "Portcullis";
	public static final String RAMPARTS = "Ramparts";
	public static final String ROCK_WALL = "Rock Wall";
	public static final String ROUGH_TERRAIN = "Rough Terrain";
	public static final String SALLY_PORT = "Sally Port";
	
	public static final String POSITION_A = "Position A";
	public static final String POSITION_B = "Position B";
	public static final String POSITION_C = "Position C";
	public static final String POSITION_D = "Position D";


	public Joystick joystick;
	public Joystick gameController;
	public JoystickButton intake;
	public JoystickButton shoot;
	public JoystickButton reverse;
	public Button buttonY;
	public Button buttonStart;
	public JoystickButton autoShoot;
	public JoystickButton spitOut;
	public JoystickButton resetArm;
	public SendableChooser autoDefenseChooser;
	public SendableChooser autoStartPosition;

	public OI() {
		joystick = new Joystick(0);
		gameController = new Joystick(1);
		shoot = new JoystickButton(joystick, 1);
		shoot.whileHeld(new ManualShoot());

		reverse = new JoystickButton(joystick, 12);
		reverse.whenPressed(new ReverseCommand());

		intake = new JoystickButton(gameController, 3);
		intake.whileHeld(new IntakeBall());
		
		autoShoot = new JoystickButton(gameController, 6);
		autoShoot.whenPressed(new AutoShoot());
		
		JoystickButton centerToGoal = new JoystickButton(gameController, 1);
		centerToGoal.whenPressed(new AutoCenterToGoal());
		// should shoot be a whileHeld?
		// where are the manual shoot controls?
		// we need those too for when the autoShoot fails.
		
		
		spitOut = new JoystickButton(gameController, 2);
		spitOut.whileHeld(new IntakeBall(-1));

		// JoystickButton reverseIntake = new JoystickButton(gameController, 2);
		JoystickButton manualFeed = new JoystickButton(gameController, 5);
		manualFeed.whileHeld(new ManualFeed());

		buttonY = new JoystickButton(gameController, 4);
		buttonStart = new JoystickButton(gameController, 8);
		Command servoSafety = new ServoControllerSafetyPressed();
		buttonStart.whileHeld(servoSafety);
		
		// intake.whenReleased(new Interrupt());
				
		// start button
		
		// buttonY.whileHeld(servoSafety); // test enabling this if there is time.

		// pick an appropriate button - gameController?
		resetArm = new JoystickButton(joystick, 8);
		resetArm.whenPressed(new ResetArm());
		
    	autoStartPosition = new SendableChooser();
		autoStartPosition.addDefault(POSITION_A, POSITION_A);
		autoStartPosition.addObject(POSITION_B, POSITION_B);
		autoStartPosition.addObject(POSITION_C, POSITION_C);
		autoStartPosition.addObject(POSITION_D, POSITION_D);
		SmartDashboard.putData(AUTO_START_POSITION, autoStartPosition);

		autoDefenseChooser = new SendableChooser();
		autoDefenseChooser.addDefault(ROUGH_TERRAIN, new RoughTerrain());
		autoDefenseChooser.addObject(ROCK_WALL, new RockWall());
		autoDefenseChooser.addObject(PORTCULLIS, new Portcullis());
		autoDefenseChooser.addObject(MOAT, new Moat());
		autoDefenseChooser.addObject(RAMPARTS, new Ramparts());
		autoDefenseChooser.addObject(SALLY_PORT, new SallyPort());
		autoDefenseChooser.addObject(DRAWBRIDGE, new Drawbridge());
		autoDefenseChooser.addObject(CHEVAL_DE_FRISE, new ChevalDeFrise());
		autoDefenseChooser.addObject(NO_DEFENSE, new NoDefense());
		SmartDashboard.putData(DEFENSE_CHOOSER, autoDefenseChooser);
	}
}

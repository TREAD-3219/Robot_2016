package org.usfirst.frc3219.Robot_2016;


import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;
import org.usfirst.frc3219.Robot_2016.commands.IntakeBall;
import org.usfirst.frc3219.Robot_2016.commands.ManualFeed;
import org.usfirst.frc3219.Robot_2016.commands.ManualShoot;
import org.usfirst.frc3219.Robot_2016.commands.ReverseCommand;
import org.usfirst.frc3219.Robot_2016.commands.ServoControllerSafetyPressed;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc3219.autonomousLibrary.AutoCenterToGoal;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
	private static final String AUTO_START_POSITION = "Auto Start Position";
	private static final String AUTO_DEFENSE_CHOOSER = "autoDefenseChooser";
	
    public Joystick joystick;
    public Joystick gameController;
    public JoystickButton intake;
    public JoystickButton shoot;
    public JoystickButton reverse;
    public Button buttonY;
    public Button buttonStart;
    public JoystickButton autoShoot;
    public JoystickButton spitOut;
    public SendableChooser autoDefenseChooser;
    public SendableChooser autoStartPosition;
    
    public OI(){
    	joystick = new Joystick(0);
    	gameController = new Joystick(1);
    	shoot = new JoystickButton(joystick, 1);
    	shoot.whileHeld(new ManualShoot());
    	reverse = new JoystickButton(joystick, 12);
    	reverse.whenPressed(new ReverseCommand());
    	intake = new JoystickButton(gameController, 3);
    	autoShoot = new JoystickButton(gameController, 6);
    	JoystickButton centerToGoal = new JoystickButton(gameController, 1);
    	centerToGoal.whenPressed(new AutoCenterToGoal());
    	spitOut = new JoystickButton(gameController, 2);
    	spitOut .whileHeld(new IntakeBall(-1));

    	//JoystickButton reverseIntake = new JoystickButton(gameController, 2);
    	JoystickButton manualFeed = new JoystickButton(gameController, 5);
    	manualFeed.whileHeld(new ManualFeed());
    	
    	buttonY = new JoystickButton(gameController, 4);
    	buttonStart = new JoystickButton(gameController, 8);
    	intake.whenPressed(new IntakeBall());
    	autoShoot.whileHeld(new AutoShoot());
    	//intake.whenReleased(new Interrupt());
    	
    	//start button
    	buttonStart.whileHeld(new ServoControllerSafetyPressed());
    	//buttonY.whileHeld(new ServoControllerSafetyPressed());
    	
    		autoStartPosition = new SendableChooser();//
		autoStartPosition.addDefault(AUTO_START_POSITION, "Position A");//
		autoStartPosition.addObject(AUTO_START_POSITION, "Position B");//
		autoStartPosition.addObject(AUTO_START_POSITION, "Position C");//
		autoStartPosition.addObject(AUTO_START_POSITION, "Position D");//
		SmartDashboard.putData("Start Chooser", autoStartPosition);

		autoDefenseChooser = new SendableChooser();//
		autoDefenseChooser.addDefault(AUTO_DEFENSE_CHOOSER, "Rough Terrain");//
		autoDefenseChooser.addObject(AUTO_DEFENSE_CHOOSER, "Rock Wall");//
		autoDefenseChooser.addObject(AUTO_DEFENSE_CHOOSER, "Portcullis");//
		autoDefenseChooser.addObject(AUTO_DEFENSE_CHOOSER, "Moat");//
		autoDefenseChooser.addObject(AUTO_DEFENSE_CHOOSER, "Ramparts");//
		autoDefenseChooser.addObject(AUTO_DEFENSE_CHOOSER, "Sally Port");//
		autoDefenseChooser.addObject(AUTO_DEFENSE_CHOOSER, "Drawbridge");//
		autoDefenseChooser.addObject(AUTO_DEFENSE_CHOOSER, "Chival de Frise");//
		SmartDashboard.putData("Defense Chooser", autoDefenseChooser);
    	
    	
    	
    }
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
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}


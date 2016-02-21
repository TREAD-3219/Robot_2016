
package org.usfirst.frc3219.Robot_2016;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc3219.Robot_2016.commands.DeadReckoningChecks;
import org.usfirst.frc3219.Robot_2016.commands.EnableClimberButtons;
import org.usfirst.frc3219.Robot_2016.commands.JoystickDrive;
import org.usfirst.frc3219.Robot_2016.commands.MultiToolMover;
import org.usfirst.frc3219.Robot_2016.commands.ServoController;
import org.usfirst.frc3219.Robot_2016.commands.WatchSensors;
import org.usfirst.frc3219.Robot_2016.subsystems.Camera;
import org.usfirst.frc3219.Robot_2016.subsystems.Climber;
import org.usfirst.frc3219.Robot_2016.subsystems.Drive;
import org.usfirst.frc3219.Robot_2016.subsystems.FeedMech;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public static Drive drive;
	public static Sensors sensors;
	public static Climber climber;
	public static Shooter shooter;
	public static Camera camera;
	public static OI oi;
	public static FeedMech feedMech;
	public static MultiTool multiTool;
	public static Navigation navigation;
    Command autonomousCommand;
    SendableChooser chooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
    	drive = new Drive();
    	shooter = new Shooter();
    	sensors = new Sensors();
    	feedMech = new FeedMech();
    	multiTool = new MultiTool();
    	climber = new Climber();
    	navigation = new Navigation();
    	camera = new Camera();
		oi = new OI();
        chooser = new SendableChooser();
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        SmartDashboard.putNumber(Shooter.TOPSHOOTER, 0.7);
        SmartDashboard.putNumber(Shooter.BOTTOMSHOOTER, 1.0);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	Scheduler.getInstance().add(new WatchSensors());
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Scheduler.getInstance().add(new DeadReckoningChecks());
        Scheduler.getInstance().add(new MultiToolMover());
        Scheduler.getInstance().add(new ServoController());
        Scheduler.getInstance().add(new EnableClimberButtons());
        Scheduler.getInstance().add(new JoystickDrive());
        Scheduler.getInstance().add(new WatchSensors());
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}


package org.usfirst.frc3219.Robot_2016;

import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoCenterToGoal;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoRotate;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.AutoRough;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.EngageRamp;
import org.usfirst.frc3219.Robot_2016.autonomousLibrary.StopRobotDrive;
import org.usfirst.frc3219.Robot_2016.commands.AutoCenterPID;
import org.usfirst.frc3219.Robot_2016.commands.AutoShoot;
import org.usfirst.frc3219.Robot_2016.commands.DedReckoningChecks;
import org.usfirst.frc3219.Robot_2016.commands.EnableClimberButtons;
import org.usfirst.frc3219.Robot_2016.commands.JoystickDrive;
import org.usfirst.frc3219.Robot_2016.commands.MultiToolMover;
import org.usfirst.frc3219.Robot_2016.commands.WatchSensors;
import org.usfirst.frc3219.Robot_2016.subsystems.Camera;
import org.usfirst.frc3219.Robot_2016.subsystems.Climber;
import org.usfirst.frc3219.Robot_2016.subsystems.Drive;
import org.usfirst.frc3219.Robot_2016.subsystems.FeedMech;
import org.usfirst.frc3219.Robot_2016.subsystems.MultiTool;
import org.usfirst.frc3219.Robot_2016.subsystems.Navigation;
import org.usfirst.frc3219.Robot_2016.subsystems.Sensors;
import org.usfirst.frc3219.Robot_2016.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public enum Position {
		Unknown,
		A,
		B,
		C,
		D
	}

	public enum Defense {
		Unknown,
		ChevalDeFrise,
		Drawbridge,
		Moat,
		NoDefense,
		Portcullis,
		Ramparts,
		RockWall,
		RoughTerrain,
		SallyPort
	}

	// subsystems
	public static Drive drive;
	public static Sensors sensors;
	public static Climber climber;
	public static Shooter shooter;
	public static Camera camera;
	public static OI oi;
	public static FeedMech feedMech;
	public static MultiTool multiTool;
	public static Navigation navigation;
	
	public static Defense defense;
	public static Position position;
	
	WatchSensors sensorsCommand;
	DedReckoningChecks dedReckonCommand;
	
    Command autonomousCommand = null;
 
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

        smartDashboardInit();
		       
        sensorsCommand = new WatchSensors();
        dedReckonCommand = new DedReckoningChecks();
    }

    /**
     * This function is for initializing any SmartDashboard variables that
     * should be set before anyone tries a getXXX or some other reason.
     * 
     */
	public void smartDashboardInit() {
		SmartDashboard.putNumber(Shooter.TOPSHOOTER, Shooter.TOP_SHOOTER_SPEED);
        SmartDashboard.putNumber(Shooter.BOTTOMSHOOTER, Shooter.BOTTOM_SHOOTER_SPEED);
    	SmartDashboard.putNumber(Shooter.DEFAULT_SHOOTING_DISTANCE_TAG, 100);
    	SmartDashboard.putNumber(Shooter.SHOOT_MIN_DISTANCE, 48);
    	SmartDashboard.putNumber(Shooter.SHOOT_MAX_DISTANCE, 60);
    	SmartDashboard.putBoolean(Shooter.SHOOTER_DISTANCE_GOOD, false);
		SmartDashboard.putBoolean(Shooter.TARGET_NOT_VISIBLE, false);
        SmartDashboard.putNumber(Shooter.CENTER_POINT, Camera.IMAGE_CENTER);
		SmartDashboard.putBoolean(Climber.CLIMBER_RELEASED_TAG, false);
		SmartDashboard.putBoolean(Climber.CLIMBER_RESET_TAG, false);
		SmartDashboard.putNumber(MultiTool.DRIVE_ARM_SPEED_TAG, 0.0);
    	SmartDashboard.putBoolean(EngageRamp.ENGAGE_RAMP_FINISH_TAG, false);
    	SmartDashboard.putBoolean(AutoRough.AUTO_ROUGH_FINISH_TAG, false);
    	SmartDashboard.putBoolean(AutoRotate.AUTO_ROTATE_FINISH_TAG, false);
    	SmartDashboard.putBoolean(EngageRamp.DROP_ARMS_START_TAG, false);
    	SmartDashboard.putBoolean(EngageRamp.DROP_ARMS_FINISH_TAG, false);
    	SmartDashboard.putBoolean(StopRobotDrive.STOP_ROBOT_DRIVE_FINISH_TAG, false);
    	SmartDashboard.putBoolean(AutoShoot.AUTO_SHOOT_START_TAG, false);
    	SmartDashboard.putNumber(AutoCenterToGoal.TURN_RATE_FOR_AUTOCENTER, 0.8);
    	SmartDashboard.putNumber(AutoCenterToGoal.OVERLAP_FOR_AUTOCENTER, 8.0);
		SmartDashboard.putNumber(AutoCenterPID.AUTO_CENTER_PID_DRIVE_POWER, 0.0);
		SmartDashboard.putBoolean(AutoCenterPID.AUTO_CENTER_PID_STARTED, true);
	}
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
		Robot.sensors.sensorReset();
    	if (sensorsCommand != null) {
    		sensorsCommand.start();
    	}
    	
    	if (dedReckonCommand != null) {
    		dedReckonCommand.start();
    	}
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
     	Robot.climber.resetClimber(); // ensure servo's in correct position
    	
     	autonomousCommand = (Command) Robot.oi.autoDefenseChooser.getSelected();
     	String positionName = (String) Robot.oi.autoStartPosition.getSelected();
     	
     	if (positionName.equals(OI.POSITION_A)) {
     		position = Position.A;
     	} else if (positionName.equals(OI.POSITION_B)) {
     		position = Position.B;
     	} else if (positionName.equals(OI.POSITION_C)) {
     		position = Position.C;
     	} else if (positionName.equals(OI.POSITION_D)) {
     		position = Position.D;
     	} else {
     		position = Position.Unknown;
     		System.out.println("autonomousInit: Unknown Position!! " + positionName);
     	}
    
         if (autonomousCommand != null) {
        	autonomousCommand.start();
        }
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
        Scheduler.getInstance().add(new MultiToolMover());
        Scheduler.getInstance().add(new EnableClimberButtons());
        Scheduler.getInstance().add(new JoystickDrive());
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

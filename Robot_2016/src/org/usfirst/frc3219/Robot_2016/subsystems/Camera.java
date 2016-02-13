package org.usfirst.frc3219.Robot_2016.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
public class Camera {

	// Basic statistics:
/*	public double getFPS() {
		return SmartDashboard.getNumber("FPS", -1.0);
	}*/
	// Blob Analysis
/*	public double getShape_Orientation() {
		return SmartDashboard.getNumber("SHAPE_ORIENTATION", -1.0);
	}

	// ----
	public double getBottom_Left_Y() {
		return SmartDashboard.getNumber("LEFT_BOTTOM_Y", -1.0);
	}

	public double getBottom_Right_Y() {
		return SmartDashboard.getNumber("RIGHT_BOTTOM_Y", -1.0);
	}

	public double getBottom_Right_X() {
		return SmartDashboard.getNumber("RIGHT_BOTTOM_X", -1.0);
	}

	public double getBottom_Left_X() {
		return  SmartDashboard.getNumber("LEFT_BOTTOM_X", -1.0);
	}

	public double getTop_Left_Y() {
		return SmartDashboard.getNumber("LEFT_TOP_Y", -1.0);
	}

	public double getTop_Right_Y() {
		return SmartDashboard.getNumber("RIGHT_TOP_Y", -1.0);
	}

	public double getTop_Left_X() {
		return SmartDashboard.getNumber("LEFT_TOP_X", -1.0);
	}

	public double getTop_Right_X() {
		return SmartDashboard.getNumber("RIGHT_TOP_X", -1.0);
	}
*/
	public double getCOG_X() {
		return SmartDashboard.getNumber("COG_X", -1.0);
	}

/*	public double getCOG_Y() {
		return SmartDashboard.getNumber("COG_Y", -1.0);
	}
*/
	public String getFileName() {
		return SmartDashboard.getString("SHAPE_FILENAME", "unknown");
	}

	// differences:

	// MAY WANT TO PUT BACK TO ABSOLUTE VALUES

	// Default value is most likely 0, but be worried about a return of -1 or
	// less.
//	public double leftSideHeight() {
//		return (bottom_Left_Y - top_Left_Y);
//	}
//
//	public double rightSideHeight() {
//		return (bottom_Right_Y - top_Right_X);
//	}
//
//	public double width() {
//		return (bottom_Left_X - bottom_Right_X);
//	}
//
//	// Probably don't need...
//	public double top_width() {
//		return (top_Left_X - top_Right_X);
//	}
}

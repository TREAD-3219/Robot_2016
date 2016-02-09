package org.usfirst.frc3219.Robot_2016.subsystems;

import org.usfirst.frc3219.Robot_2016.RobotMap;

import Robot_2016.utility.Utility;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Sensors extends Subsystem {

	

	private static final byte LIDAR_1_ADDR = 0x62;
	private static final byte LIDAR_READ_START =(byte) 0x8F;
	private static final int START_MEASUREMENT = 0x04;
	private static final int READ_CONTROL_REGISTER = 0x00;
	public static final String LINE_SEEKER_TAG = "Line Seeker";
	
	PowerDistributionPanel pdp = new PowerDistributionPanel();
	I2C lidar1 = new I2C(I2C.Port.kMXP, LIDAR_1_ADDR);
	double lastLidar1Read = 0.0;
	private int totalPackets = 0;
	
	AnalogInput ultrasonic1 = RobotMap.sensorsUltraSonic1;
	
	private void startLidarMeasurement(){
		boolean res = lidar1.write(READ_CONTROL_REGISTER, START_MEASUREMENT);
		SmartDashboard.putBoolean("Start Lidar", res);
	}
	
	public double readLidar1(){
		byte[] bytes = new byte[2];
		double res = -1.0;
		//read the data from the last measure command
		if (lidar1.read(LIDAR_READ_START, 2, bytes)){
			
			int cms = Utility.getShort(bytes, 0);
			res = cms / 2.54;
			if (res != 0){
				lastLidar1Read = res;
			}else {
				res = lastLidar1Read;
			}
			startLidarMeasurement();
		}
		
		return res;
	}
	//public double readShooterCounter() {
		//return RobotMap.normalCounter.getPeriod();
		
	// public void gettingCounterValue() {
		// int count = RobotMap.normalCounter.get();
		// double distance = RobotMap.normalCounter.getDistance();
		// double period = RobotMap.normalCounter.getPeriod();
		// double rate = RobotMap.normalCounter.getRate();
		// boolean direction = RobotMap.normalCounter.getDirection();
		// boolean stopped = RobotMap.normalCounter.getStopped();
		
	//}
	
	//public boolean readLineSeeker() {
		//return RobotMap.lineSeekerInput.get();
	//}
	
	public double readUltraSonic1(){
		double ultraValue1 = ultrasonic1.getVoltage() * 0.00003842519685;
		return ultraValue1;
	}
	
	public int readShooterCounter(){
		return RobotMap.normalCounter.get();
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}

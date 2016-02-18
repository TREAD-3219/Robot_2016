package org.usfirst.frc3219.Robot_2016.subsystems;

import edu.wpi.first.wpilibj.Encoder;

public class Encoders implements Robot_2016.utility.RotationDetector {
	private static final double RADIANS_TO_DEGREES = 180 / Math.PI;
	private static final double TICKS_PER_REVOLUTION = 360.0;
	private static final double DISTANCE_PER_PULSE = Drive.WHEEL_CIRCUMFERENCE
			/ TICKS_PER_REVOLUTION;
	private static final double LEFT_CALIBRATION = -1.0;
	private static final double RIGHT_CALIBRATION = 1.0;
	private static final double LEFT_DISTANCE_PER_PULSE = DISTANCE_PER_PULSE
			* LEFT_CALIBRATION;
	private static final double RIGHT_DISTANCE_PER_PULSE = DISTANCE_PER_PULSE
			* RIGHT_CALIBRATION;

	private Encoder rightEncoder;
	private Encoder leftEncoder;
	private EncoderData rightData = new EncoderData(rightEncoder);
	private EncoderData leftData = new EncoderData(leftEncoder);
	private double maxTheta;

	private void initialize() {
		leftEncoder.setDistancePerPulse(LEFT_DISTANCE_PER_PULSE);
		rightEncoder.setDistancePerPulse(RIGHT_DISTANCE_PER_PULSE);		
	}
	
	public Encoders(Encoder rightEncoder, Encoder leftEncoder, double maxTheta) {
		this.rightEncoder = rightEncoder;
		this.leftEncoder = leftEncoder;
		this.maxTheta = maxTheta;
		this.initialize();
	}

	public void reset() {
		leftEncoder.reset();
		rightEncoder.reset();
		leftData.reset();
		rightData.reset();
		this.maxTheta = 0.0;
	}

	public int leftEncoderRaw() {
		return leftEncoder.getRaw() / leftEncoder.getEncodingScale();
	}

	public int rightEncoderRaw() {
		return rightEncoder.getRaw() / rightEncoder.getEncodingScale();
	}

	public double leftEncoderDistance() {
		double res = leftEncoder.getDistance();
		return res;
	}

	public double rightEncoderDistance() {
		double res = rightEncoder.getDistance();
		return res;
	}

	public double leftEncoderSpeed() {
		double res = leftEncoder.getRate();
		return res;
	}

	public double rightEncoderSpeed() {
		double res = rightEncoder.getRate();
		return res;
	}

	public double getDistance() {
		return (leftEncoderDistance() + rightEncoderDistance()) / 2.0;
	}

	public double getVelocity() {
		return (leftEncoderSpeed() + rightEncoderSpeed()) / 2.0;
	}

	public void thetaReset() {
		leftData.reset();
		rightData.reset();
	}

	public double getTheta() {
		double lRDelta = leftData.getDeltaDistance() - rightData.getDeltaDistance();
		double theta = lRDelta / Drive.WHEEL_BASE;
		double finalTheta = theta * RADIANS_TO_DEGREES;
		maxTheta = Math.max(maxTheta, Math.abs(finalTheta));
		return finalTheta;
	}

	public double getThetaDot() {
		double lRDelta = leftData.getDeltaVelocity()
				- rightData.getDeltaVelocity();
		double theta = lRDelta / Drive.WHEEL_BASE;
		return theta * RADIANS_TO_DEGREES;
	}

	public double getMaxTheta() {
		return maxTheta;
	}

	@Override
	public double currentRotation() {
		return this.getTheta();
	}

	@Override
	public void setZeroPoint() {
		this.thetaReset();
	}

	private class EncoderData {
		Encoder _myEncoder;
		double _baseDistance;
		double _baseVelocity;

		public EncoderData(Encoder e) {
			_myEncoder = e;
			this.reset();
		}

		public void reset() {
			_baseDistance = _myEncoder.getDistance();
			_baseVelocity = _myEncoder.getRate();
		}

		public double getDeltaDistance() {
			double deltaD = _myEncoder.getDistance() - _baseDistance;
			return deltaD;
		}

		public double getDeltaVelocity() {
			double deltaV = _myEncoder.getRate() - _baseVelocity;
			return deltaV;
		}
	}
}
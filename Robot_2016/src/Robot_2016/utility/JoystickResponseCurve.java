package Robot_2016.utility;

public class JoystickResponseCurve {
	private double minThrottle;
	private double powerInfluence;
	private double power;
	private double deadzone;
	
	public JoystickResponseCurve(double min, double influence, double power, double dead) {
		minThrottle = min;
		powerInfluence = influence;
		this.power = power;
		deadzone = dead;
	}
	
	public double transform(double input, double throttle) {
		if (Math.abs(input) < deadzone) {
			return 0.0;
		}
		
		// scale throttle from minThrottle to 1.0;
		double throttled = (throttle * (1 - minThrottle)) - minThrottle;
		double powered = Math.pow(input, this.power);
		double output = throttled * ((powerInfluence * powered) + (1-powerInfluence) * input);
		return output;
	}
}

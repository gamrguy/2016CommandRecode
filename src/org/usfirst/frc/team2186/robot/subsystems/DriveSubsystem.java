package org.usfirst.frc.team2186.robot.subsystems;

import org.usfirst.frc.team2186.robot.RobotMap;
import org.usfirst.frc.team2186.robot.utilities.ShiftingSpeedController;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveSubsystem extends Subsystem {
    
	class DriveTypes {
		public static final int TANK_DRIVE = 0;
		public static final int ARCADE_DRIVE = 1;
	}
	
	public static final double MAX_SPEED = .75;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	ShiftingSpeedController m_left;
	ShiftingSpeedController m_right;
	//private Drive() {
		//m_left = new ShiftingSpeedController(RobotMap.DriveTrain.LEFT_FORWARD, RobotMap.DriveTrain.LEFT_REVERSE, RobotMap.DriveTrain.LEFT_ENCODER , RobotMap.DriveTrain.LEFT);
		//m_right = new ShiftingSpeedController(RobotMap.DriveTrain.RIGHT_FORWARD, RobotMap.DriveTrain.RIGHT_REVERSE, RobotMap.DriveTrain.RIGHT_ENCODER, RobotMap.DriveTrain.RIGHT);
	//}
	
	public void setRight(double val) {
		m_right.set(val);
	}
	
	public void setLeft(double val) {
		m_left.set(val);
	}
	
	public void set(double left, double right) {
		setLeft(left);
		setRight(right);
	}
	
	public void goDistance(double dist, double speed) {
		double m_left_dist = 0, m_right_dist = 0;
		
		if(dist < 0)
		{
			set(-speed, -speed);
			dist = -dist;
		}
		else
			set(speed, speed);
		
		m_left.getEncoder().reset();
		m_right.getEncoder().reset();
		
		while(Math.abs(m_left_dist) <= dist || Math.abs(m_right_dist) <= dist) {
			m_left_dist = m_left.getEncoder().getDistance();
			m_right_dist = m_right.getEncoder().getDistance();
		}
		stop();
	}
	
	public void stop()
	{
		set(0, 0);
	}
	
	public void turnAngle(double speed, double degrees)
	{
		if(degrees == 0)
			return;
		
		//Turn right on positive angle, left on negative
		if(degrees > 0)
			set(speed, -speed);
		else
		{
			degrees = -degrees; //can't be havin negative degrees now!
			set(-speed, speed);
		}
		m_left.getEncoder().reset(); //resetti be mad nao mwahahahaha
		m_right.getEncoder().reset();
		
		//just keep turnin until done turnin! looks stupid, but trust me IT ISN'T.
		while(RobotMap.DriveTrain.TURNING_DEGREES_PER_PULSE*m_left.getEncoder().getRaw() < degrees ||
			  RobotMap.DriveTrain.TURNING_DEGREES_PER_PULSE*m_right.getEncoder().getRaw() < degrees);
		
		//stop turnin.
		stop();
	}
}



package org.usfirst.frc.team2186.robot.subsystems;

import org.usfirst.frc.team2186.robot.Robot;
import org.usfirst.frc.team2186.robot.utilities.ShiftingSpeedController;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearboxSubsystem extends Subsystem {
    private int state;
    private ShiftingSpeedController right = Robot.driveSubsystem.m_right;
    private ShiftingSpeedController left = Robot.driveSubsystem.m_left;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
    //start in selected gear
    public GearboxSubsystem(int gear)
    {
    	if(!(gear == 1 || gear == 0))
    		gear = 0;
    	shift(gear);
    	state = gear;
    }
    
    //default to current gear (preference to right motor's current gear)
    public GearboxSubsystem()
    {
    	if(right.getState() == Value.kForward)
    	{
    		shift(ShiftingSpeedController.Gear.UP);
    		state = ShiftingSpeedController.Gear.UP;
    	} else if (right.getState() == Value.kReverse || right.getState() == Value.kOff)
    	{
    		shift(ShiftingSpeedController.Gear.DOWN);
    		state = ShiftingSpeedController.Gear.DOWN;
    	}
    		
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	public void shiftLeft(int gear) {
		left.shift(gear);
	}
	
	public void shiftRight(int gear) {
		right.shift(gear);
	}
	
	public void shift(int gear) {
		shiftLeft(gear);
		shiftRight(gear);
		state = gear;
	}
	
	public int getState(){ return state; }
}


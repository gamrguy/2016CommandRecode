
package org.usfirst.frc.team2186.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2186.robot.Robot;

/**
 *	This command is used to shift the gearboxes.
 */
public class ShiftCommand extends Command {
    
    public ShiftCommand()
    {
    	// Use requires() here to declare subsystem dependencies
    	requires(Robot.gearboxSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.gearboxSubsystem.getState() == 0)
    		Robot.gearboxSubsystem.shift(1);
    	else
    		Robot.gearboxSubsystem.shift(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

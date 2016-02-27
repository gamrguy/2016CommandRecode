
package org.usfirst.frc.team2186.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2186.robot.Robot;
import org.usfirst.frc.team2186.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team2186.robot.utilities.Utils;

/**
 *
 */
public class TeleopCommand extends Command {
	Joystick controller = Robot.oi.controller;
	public static final int TANK_DRIVE = 0;
	public static final int ARCADE_DRIVE = 1;
	
    public TeleopCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double left, right, x, y;
		int driveType = (int) SmartDashboard.getNumber("DriveType", 0);
		if(driveType == TANK_DRIVE) {
			left = Utils.deadzone(controller.getRawAxis(1));
			right = -Utils.deadzone(controller.getRawAxis(3));
		} else {
			x = controller.getRawAxis(0);
			y = controller.getRawAxis(1);
			
			left = y + x;
			right = y - x;
		}
		Robot.driveSubsystem.set(left*DriveSubsystem.MAX_SPEED, right*DriveSubsystem.MAX_SPEED);
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

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANDrivetrain;

public class DriveForTime extends Command{
    
    private final CANDrivetrain m_CanDrivetrain;
    private final double speed;
    private final double rotation;

    private double target = 0.0;
    private double counter = 0.0;

    public DriveForTime(CANDrivetrain m_CanDrivetrain, double speed, double rotation, double seconds){
        this.m_CanDrivetrain = m_CanDrivetrain;
        this.speed = speed;
        this.rotation = rotation;
        target = seconds * 50;

        addRequirements(m_CanDrivetrain);
    }

    public void execute(){
        if(counter < target){
            counter++;
        }

        m_CanDrivetrain.arcadeDrive(speed, rotation);
    }

    public void end(boolean interrupted){
        m_CanDrivetrain.arcadeDrive(0, 0);
    }
    public boolean isFinished(){
        return counter >= target;
    }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANLauncher;

public class ShootForTime extends Command{
    
    private final CANLauncher m_CanLauncher;
    private final double speedL;

    private double target = 0.0;
    private double counter = 0.0;

    public ShootForTime(CANLauncher m_CanLauncher, double speedL, double seconds){
        this.m_CanLauncher = m_CanLauncher;
        this.speedL = speedL;
        target = seconds * 50;

        addRequirements(m_CanLauncher);
    }

    public void execute(){
        if(counter < target){
            counter++;
        }
        
        m_CanLauncher.setLaunchWheel(speedL);
        if(counter > 25){
            m_CanLauncher.setFeedWheel(speedL);
        }
    }

    public void end(boolean interrupted){
        m_CanLauncher.setLaunchWheel(0);
    }
    public boolean isFinished(){
        return counter >= target;
    }
}

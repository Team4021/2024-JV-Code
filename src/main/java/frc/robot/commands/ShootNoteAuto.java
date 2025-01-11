package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.LauncherConstants;
import frc.robot.subsystems.CANLauncher;

public class ShootNoteAuto extends Command{

    private final CANLauncher m_CANLauncher;
    private final double speed;

    private double target = 0.0;
    private double counter = 0.0;
    
    public ShootNoteAuto(CANLauncher m_CANLauncher, double speed, double seconds){
        this.m_CANLauncher = m_CANLauncher;
        this.speed = speed;
        target = seconds * 50;

        addRequirements(m_CANLauncher);
    }

    public void execute (){
        if(counter < target){
            counter++;
        }

        new PrepareLaunch(m_CANLauncher)
        .withTimeout(LauncherConstants.kLauncherDelay)
        .andThen(new LaunchNote(m_CANLauncher, 12))
        .handleInterrupt(() -> m_CANLauncher.stop());
    }
    public boolean isFinished(){
        return counter >= target;
    }
}

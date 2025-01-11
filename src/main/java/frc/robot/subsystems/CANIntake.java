package frc.robot.subsystems;





import static frc.robot.Constants.LauncherConstants.kIntakeFeederSpeed;
import static frc.robot.Constants.LauncherConstants.kIntakeLauncherSpeed;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.core.CoreTalonFX;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LauncherConstants.*;

public class CANIntake extends SubsystemBase{
    CoreTalonFX m_intakewheel;
    VictorSPX m_intakewheel2;
    final VoltageOut m_request = new VoltageOut(0);

    public CANIntake() {
        m_intakewheel = new CoreTalonFX(8);
        m_intakewheel2 = new VictorSPX(9);
    }
public Command intakeCommand() {
    return this.startEnd(
        // When the command is initialized, set the wheels to the intake speed values
        () -> {
          setIntakeWheel(6);
        },
        // When the command stops, stop the wheels
        () -> {
          stop();
        });
}


public void setIntakeWheel(double speed) {
    m_intakewheel.setControl(m_request.withOutput(speed));
    m_intakewheel2.set(ControlMode.PercentOutput, speed);
}

public void stop() {
    m_intakewheel.setControl(m_request.withOutput(0));
    m_intakewheel2.set(ControlMode.PercentOutput, 0);
}
}

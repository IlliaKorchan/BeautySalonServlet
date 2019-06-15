package model.entities;

import java.util.List;

public class MasterAppointmentsDto {
    private UserDto master;
    private List<WorkingDay> workingDays;
    private List<Appointment> masterAppointments;

    public MasterAppointmentsDto(UserDto master, List<WorkingDay> workingDays, List<Appointment> masterAppointments) {
        this.master = master;
        this.workingDays = workingDays;
        this.masterAppointments = masterAppointments;
    }

    public MasterAppointmentsDto() {
    }

    public UserDto getMaster() {
        return master;
    }

    public void setMaster(UserDto master) {
        this.master = master;
    }

    public List<WorkingDay> getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(List<WorkingDay> workingDays) {
        this.workingDays = workingDays;
    }

    public List<Appointment> getMasterAppointments() {
        return masterAppointments;
    }

    public void setMasterAppointments(List<Appointment> masterAppointments) {
        this.masterAppointments = masterAppointments;
    }
}

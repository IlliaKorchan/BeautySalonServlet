package model.services.impl;

import model.services.WorkingDayTimeProcessor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static string.containers.ConstantsContainer.WORKING_DAY_END;
import static string.containers.ConstantsContainer.WORKING_DAY_START;

public class DayTimeService implements WorkingDayTimeProcessor {
    public List<LocalTime> getWorkingHours() {
        List<LocalTime> workingHours = new ArrayList<>();
        LocalTime time = WORKING_DAY_START;

        while (time.getHour() < WORKING_DAY_END.getHour()) {
            workingHours.add(time);
            time = time.plusHours(1);
        }

        return workingHours;
    }


}

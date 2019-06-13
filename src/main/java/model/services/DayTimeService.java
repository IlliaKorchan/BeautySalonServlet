package model.services;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static string.containers.Constants.WORKING_DAY_END;
import static string.containers.Constants.WORKING_DAY_START;

public class DayTimeService {
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

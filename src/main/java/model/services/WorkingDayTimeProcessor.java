package model.services;

import java.time.LocalTime;
import java.util.List;

public interface WorkingDayTimeProcessor {
    List<LocalTime> getWorkingHours();
}

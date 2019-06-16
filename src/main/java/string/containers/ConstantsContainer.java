package string.containers;

import java.time.LocalTime;

public interface ConstantsContainer {
    LocalTime WORKING_DAY_START = LocalTime.of(9, 0);
    LocalTime WORKING_DAY_END = LocalTime.of(19, 0);
    Integer PENNY_TO_UAH_DIVIDE = 100;
}

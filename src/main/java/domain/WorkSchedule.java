package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by schna_000 on 16.03.14.
 * version 1.0
 * Initial creation
 */
public class WorkSchedule {

    private Person employee;

    public WorkSchedule(Person employee) {
        this.employee = employee;
    }

    private List<Date> dates = new ArrayList<>();

    public void addDay(Date day) {
        dates.add(day);
    }

    public void addDay(long day) {
        dates.add(new Date(day));
    }

    public boolean isAvailableOn(Date day) {
        return dates.contains(day);
    }

}

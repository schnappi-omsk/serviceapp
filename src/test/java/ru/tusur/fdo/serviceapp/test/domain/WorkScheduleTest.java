package ru.tusur.fdo.serviceapp.test.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static java.time.Month.*;
import static org.mockito.Mockito.*;

import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.WorkSchedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Test suite for WorkScedule class
 * Created by schnappi on 26.04.14.
 */
public class WorkScheduleTest {

    private WorkSchedule schedule;

    private List<LocalDate> dates;

    @Before
    public void setTestData() {
        schedule = new WorkSchedule( mock(Person.class) );
        dates = new ArrayList<LocalDate>();
        dates.add(LocalDate.of(2014, JANUARY, 13));
        dates.add(LocalDate.of(2014, JANUARY, 14));
        dates.add(LocalDate.of(2014, JANUARY, 17));
        dates.add(LocalDate.of(2014, JANUARY, 18));
        dates.add(LocalDate.of(2014, JANUARY, 21));
        dates.add(LocalDate.of(2014, JANUARY, 22));
        LocalTime start = LocalTime.of(8, 0);
        LocalTime end = LocalTime.of(17, 0);
        dates.stream().forEach(d -> schedule.addWorkingDay(d, start, end));
    }

    @Test
    public void CheckWorkingDayTest() {
        assertTrue(schedule.isWorkingDay(LocalDate.of(2014, JANUARY, 13)));
        assertTrue(schedule.isWorkingDay(LocalDate.of(2014, JANUARY, 14)));
        assertFalse(schedule.isWorkingDay(LocalDate.of(2014, JANUARY, 15)));
        assertFalse(schedule.isWorkingDay(LocalDate.of(2014, JANUARY, 16)));
    }

    @Test
    public void CheckWorkingTimeTest() {
        assertTrue(schedule.isWorkingTime(LocalDate.of(2014, JANUARY, 13), LocalTime.of(14, 35)));
        assertFalse(schedule.isWorkingTime(LocalDate.of(2014, JANUARY, 14), LocalTime.of(18, 05)));
        assertFalse(schedule.isWorkingTime(LocalDate.of(2014, JANUARY, 15), LocalTime.of(12, 00)));
        assertFalse(schedule.isWorkingTime(LocalDate.of(2014, JANUARY, 16), LocalTime.of(13, 00)));
    }

    @Test
    public void CheckWorkingDatesTest() {
        Set<LocalDate> dateSet = new HashSet<>();
        dates.stream().forEach(dateSet::add);
        Set<LocalDate> scheduleSet = schedule.workingDates();
        for (LocalDate date : scheduleSet) {
            assertTrue(dateSet.contains(date));
        }
    }

}

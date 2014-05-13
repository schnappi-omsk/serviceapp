package ru.tusur.fdo.serviceapp.test.domain;

import static org.junit.Assert.*;
import org.junit.Test;
import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.Request;
import static org.hamcrest.CoreMatchers.*;
import static ru.tusur.fdo.serviceapp.domain.RequestStatus.*;

/**
 * Created by schnappi on 13.05.14.
 */
public class RequestFlowTest {

    @Test
    public void requestStatusTest() {
        Person employee = new Person();
        Request request = new Request();
        assertThat(request.getStatus(), is(NEW));
        request.assignTo(employee);
        assertThat(request.getStatus(), is(ASSIGNED));
        request.resolve();
        assertThat(request.getStatus(), is(COMPLETED));
        request.reopen();
        assertThat(request.getStatus(), is(ASSIGNED));
        request.reject();
        assertThat(request.getStatus(), is(REJECTED));
    }



}

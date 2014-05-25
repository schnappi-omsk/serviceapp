package ru.tusur.fdo.serviceapp.test.domain;

import static org.junit.Assert.*;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import ru.tusur.fdo.serviceapp.domain.Contact;
import ru.tusur.fdo.serviceapp.domain.Person;
import ru.tusur.fdo.serviceapp.domain.Request;
import static org.hamcrest.CoreMatchers.*;
import static ru.tusur.fdo.serviceapp.domain.RequestStatus.*;

/**
 * ${PROJECT_NAME}
 * ${PACKAGE_NAME}
 * by Oleg Alekseev
 * 13.05.14.
 */
public class RequestFlowTest {

    @Test
    public void requestStatusTest() {
        Person employee = new Person();
        Request request = new Request();
        assertThat(request.getStatus(), is(NEW));
        assertFalse(request.closed());
        assertTrue(request.inWork());
        request.assignTo(employee);
        assertThat(request.getStatus(), is(ASSIGNED));
        assertFalse(request.closed());
        assertTrue(request.inWork());
        request.resolve();
        assertThat(request.getStatus(), is(COMPLETED));
        assertFalse(request.inWork());
        assertTrue(request.closed());
        request.reopen();
        assertThat(request.getStatus(), is(ASSIGNED));
        assertFalse(request.closed());
        assertTrue(request.inWork());
        request.reject();
        assertThat(request.getStatus(), is(REJECTED));
        assertFalse(request.inWork());
        assertTrue(request.closed());
    }

    @Test
    public void createRequestAndNotifyTest() {
/*        Person employee = new Person();
        employee.setFirstName("John Doe");
        Contact email = new Email();
        email.setValue("schnappi@xakep.ru");*/
    }

}

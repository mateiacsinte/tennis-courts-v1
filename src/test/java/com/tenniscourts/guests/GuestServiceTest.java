package com.tenniscourts.guests;

import com.tenniscourts.guests.Guest;
import com.tenniscourts.schedules.Schedule;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.Optional;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = GuestService.class)

public class GuestServiceTest {

    @Mock
    GuestRepository guestRepository;

    GuestService guestService;

    GuestMapper guestMapper;

    static final LocalDateTime dateTime = LocalDateTime.now();

    @Test
    public void retrieveTheGuestById(){

        //setup

        configureGuestServiceForTest();

        final Long GUEST_ID = 1L;

        when(guestRepository.findById(GUEST_ID)).thenReturn(Optional.of(createTestGuest()));

        //execute
        GuestDTO guestDTO = guestService.findGuest(GUEST_ID);

        //verify
        Assertions.assertAll(
                () -> Assertions.assertEquals("John", guestDTO.getName()),
                () -> Assertions.assertEquals(dateTime, guestDTO.getDateCreate())
        );

    }

    @Test
    public void retrieveTheGuestByName(){

        //setup

        configureGuestServiceForTest();

        final String NAME = "John";

        when(guestRepository.findGuestByName(NAME)).thenReturn(createTestGuest());

        //execute
        GuestDTO guestDTO = guestService.findGuest(NAME);

        //verify
        Assertions.assertAll(
                () -> Assertions.assertEquals("John", guestDTO.getName()),
                () -> Assertions.assertEquals(dateTime, guestDTO.getDateCreate())
        );

    }

    private Guest createTestGuest(){
        Guest guest = new Guest();
        guest.setName("John");
        guest.setDateCreate(dateTime);

        return guest;
    }

    private void configureGuestServiceForTest(){

        guestMapper = new GuestMapperImpl();

        guestService = new GuestService(guestRepository, guestMapper);
    }
}

package com.tenniscourts.reservations;

import com.tenniscourts.guests.Guest;
import com.tenniscourts.schedules.Schedule;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;

import static com.tenniscourts.reservations.ReservationStatus.READY_TO_PLAY;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = ReservationService.class)
public class ReservationServiceTest {

    @Mock
    ReservationRepository reservationRepository;

    ReservationService reservationService;

    ReservationMapper reservationMapper;


    @Test
    public void getRefundValueFullRefund() {

        //setup

        reservationService = new ReservationService(reservationRepository, reservationMapper);

        Schedule schedule = new Schedule();

        LocalDateTime startDateTime = LocalDateTime.now().plusDays(2);

        schedule.setStartDateTime(startDateTime);

        //execute
        BigDecimal refundValue = reservationService
                .getRefundValue(Reservation.builder().schedule(schedule).value(new BigDecimal(10L)).build());

        //verify
        Assert.assertEquals(refundValue, new BigDecimal(10));
    }

    @Test
    public void returnNewReservationIfCreateReservationIsCalled(){

        //setup

        final long SCHEDULE_ID = 1L;

        final long GUEST_ID = 3L;

        CreateReservationRequestDTO createReservationRequestDTO = new CreateReservationRequestDTO(GUEST_ID, SCHEDULE_ID);

        ReservationMapper reservationMapper = new ReservationMapperImpl();

        reservationService = new ReservationService(reservationRepository, reservationMapper);

        when(reservationRepository.createReservation(GUEST_ID,SCHEDULE_ID)).thenReturn(generateReservationForTest());

        //execute

        ReservationDTO reservationDTO = reservationService.bookReservation(createReservationRequestDTO);

        //verify
        Assert.assertEquals(READY_TO_PLAY.name(), reservationDTO.getReservationStatus());

    }

    private Reservation generateReservationForTest(){
        return Reservation.builder()
                .reservationStatus(READY_TO_PLAY)
                .guest(new Guest("John"))
                .build();
    }

}
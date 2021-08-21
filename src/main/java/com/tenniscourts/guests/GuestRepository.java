package com.tenniscourts.guests;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    Guest findGuestById(Long guestId);

    Guest findGuestByName(String name);

    Guest createGuest(String name);

    Guest updateGuest(Guest guest);

    Guest deleteGuest(Guest guest);
}

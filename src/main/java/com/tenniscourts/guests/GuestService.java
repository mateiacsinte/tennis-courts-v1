package com.tenniscourts.guests;

import com.tenniscourts.exceptions.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    private final GuestMapper guestMapper;

    public GuestDTO findGuest(Long guestId) {
        return guestRepository.findById(guestId)
                .map(guestMapper::map)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Reservation not found.");
                });
    }

    public GuestDTO findGuest(String guestName) {
        return Optional.of(guestRepository.findGuestByName(guestName))
                .map(guestMapper::map)
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("Reservation not found.");
                });
    }

    public GuestDTO createGuest(String name){
        Guest newGuest = new Guest();
        newGuest.setName(name);
        newGuest.setDateCreate(LocalDateTime.now());

        return guestMapper.map(guestRepository.save(newGuest));
    }

    public GuestDTO updateGuest(GuestDTO guest){
        return guestMapper.map(guestRepository.updateGuest(guestMapper.map(guest)));
    }

    public GuestDTO deleteGuest(GuestDTO guest){
        return guestMapper.map(guestRepository.deleteGuest(guestMapper.map(guest)));
    }
}

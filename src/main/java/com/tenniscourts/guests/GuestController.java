package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
public class GuestController extends BaseRestController {

    private final GuestService guestService;

    @GetMapping("api/v1/guests")
    @ResponseBody
    public ResponseEntity<GuestDTO> findGuest(@RequestParam Long guestId){
        return ResponseEntity.ok(guestService.findGuest(guestId));
    }

    @GetMapping("api/v1/guests")
    @ResponseBody
    public ResponseEntity<GuestDTO> findGuest(@RequestParam String guestName){
        return ResponseEntity.ok(guestService.findGuest(guestName));
    }

    @PostMapping("api/v1/guests")
    @ResponseBody
    public ResponseEntity<GuestDTO> createGuest(@RequestBody GuestDTO guest){
        return ResponseEntity.ok(guestService.createGuest(guest.getName()));
    }

    @PutMapping("api/v1/guests")
    @ResponseBody
    public ResponseEntity<GuestDTO> updateGuest(@RequestBody GuestDTO guest){
        return ResponseEntity.ok(guestService.updateGuest(guest));
    }

    @DeleteMapping("api/v1/guests")
    @ResponseBody
    public ResponseEntity<GuestDTO> deleteGuest(@RequestBody GuestDTO guestDTO){
        return ResponseEntity.ok(guestService.deleteGuest(guestDTO));
    }
}

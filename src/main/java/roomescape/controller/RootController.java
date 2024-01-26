package roomescape.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationAddRequest;
import roomescape.service.ReservationService;

@Controller
public class RootController {

    private final ReservationService reservationService;

    public RootController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/reservation")
    public String reservationPage() {
        return "new-reservation";
    }

    @GetMapping("/time")
    public String timePage() {
        return "time";
    }

    @ResponseBody
    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> reservations() {
        final List<Reservation> reservations = reservationService.findReservationsAll();

        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> addReservation(@RequestBody final ReservationAddRequest request) {
        final Reservation reservation = reservationService.bookReservation(request);

        return ResponseEntity.created(URI.create("/reservations/" + reservation.getId()))
                .body(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Reservation> removeReservation(@PathVariable final Long id) {
        reservationService.deleteReservation(id);

        return ResponseEntity.noContent().build();
    }
}

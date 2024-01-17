package roomescape.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import roomescape.domain.Reservation;
import roomescape.view.ReservationAddRequest;

@Controller
public class RootController {

    private final Map<Long, Reservation> reservations;
    private final AtomicLong index = new AtomicLong(1);

    public RootController() {
        this.reservations = new HashMap<>();
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/reservation")
    public String reservationPage() {
        return "reservation";
    }

    @ResponseBody
    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> reservations() {
        final List<Reservation> reservationList = reservations.values()
                .stream()
                .toList();

        return ResponseEntity.ok(reservationList);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> addReservation(@RequestBody final ReservationAddRequest request) {
        final Reservation reservation = new Reservation(
                index.getAndIncrement(),
                request.getName(),
                request.getDate(),
                request.getTime()
        );
        reservations.put(reservation.getId(), reservation);

        return ResponseEntity.created(URI.create("/reservations/" + reservation.getId()))
                .body(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Reservation> removeReservation(@PathVariable final Long id) {
        final Reservation foundReservation = reservations.get(id);
        if (foundReservation == null) {
            throw new IllegalArgumentException();
        }
        reservations.remove(id);

        return ResponseEntity.noContent().build();
    }
}

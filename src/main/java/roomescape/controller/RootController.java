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
import roomescape.domain.ReservationDao;

@Controller
public class RootController {

    private final ReservationDao reservationDao;

    public RootController(final ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
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
        final List<Reservation> reservationList = reservationDao.getReservations();

        return ResponseEntity.ok(reservationList);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> addReservation(@RequestBody final Reservation reservation) {
        final Long reservationId = reservationDao.insertReservation(reservation);

        return ResponseEntity.created(URI.create("/reservations/" + reservationId))
                .body(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Reservation> removeReservation(@PathVariable final Long id) {
        reservationDao.removeReservation(id);

        return ResponseEntity.noContent().build();
    }
}

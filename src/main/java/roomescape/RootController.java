package roomescape;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {

    private final List<Reservation> reservations;
    private final AtomicLong index = new AtomicLong(1);

    public RootController() {
        this.reservations = new ArrayList<>();
//        addTestData();
    }

    private void addTestData() {
        reservations.add(new Reservation(index.incrementAndGet(), "테스트 예약1", "2023-10-23", "10:00"));
        reservations.add(new Reservation(index.incrementAndGet(), "테스트 예약2", "2023-10-24", "10:00"));
        reservations.add(new Reservation(index.incrementAndGet(), "테스트 예약3", "2023-10-25", "10:00"));
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
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/reservations")
    public ResponseEntity<Reservation> addReservation(@RequestBody final ReservationAddRequest request) {
        final Reservation reservation = new Reservation(
                index.getAndIncrement(),
                request.getName(),
                request.getDate(),
                request.getTime()
        );
        reservations.add(reservation);

        return ResponseEntity.created(URI.create("/reservations/" + reservation.getId()))
                .body(reservation);
    }
}

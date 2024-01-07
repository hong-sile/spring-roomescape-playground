package roomescape;

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

@Controller
public class RootController {

    private final Map<Long, Reservation> reservations;
    private final AtomicLong index = new AtomicLong(1);

    public RootController() {
        this.reservations = new HashMap<>();
//        addTestData();
    }

    private void addTestData() {
        final Reservation testData1 = new Reservation(index.incrementAndGet(), "테스트 예약1", "2023-10-23", "10:00");
        final Reservation testData2 = new Reservation(index.incrementAndGet(), "테스트 예약2", "2023-10-24", "10:00");
        final Reservation testData3 = new Reservation(index.incrementAndGet(), "테스트 예약3", "2023-10-25", "10:00");

        reservations.put(testData1.getId(), testData1);
        reservations.put(testData2.getId(), testData2);
        reservations.put(testData3.getId(), testData3);
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
    public ResponseEntity<Reservation> removeReservation(@PathVariable Long id) {
        reservations.remove(id);

        return ResponseEntity.noContent().build();
    }
}

package roomescape;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {

    private final List<Reservation> reservations;

    public RootController() {
        this.reservations = new ArrayList<>();

        reservations.add(new Reservation(1L, "테스트 예약1", "2023-10-23", "10:00"));
        reservations.add(new Reservation(2L, "테스트 예약2", "2023-10-24", "10:00"));
        reservations.add(new Reservation(3L, "테스트 예약3", "2023-10-25", "10:00"));
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
}

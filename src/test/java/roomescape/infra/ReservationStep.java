package roomescape.infra;

import org.springframework.jdbc.core.JdbcTemplate;
import roomescape.domain.Reservation;

public class ReservationStep {

    public static final Reservation 브라운_예약 = new Reservation("브라운", "2023-08-05", "15:40");

    public static void 예약_추가(final JdbcTemplate jdbcTemplate, final Reservation reservation) {
        final String sql = "INSERT INTO reservation (name, date, time) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, reservation.getName(), reservation.getDate(), reservation.getTime());
    }
}

package roomescape.infra;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import roomescape.domain.Reservation;

public class ReservationStep {

    private static final RowMapper<Reservation> ROW_MAPPER = (rs, rowNum) -> {
        final long id = rs.getLong("id");
        final String name = rs.getString("name");
        final String date = rs.getString("date");
        final String time = rs.getString("time");
        return new Reservation(id, name, date, time);
    };

    public static final Reservation 브라운_예약 = new Reservation("브라운", "2023-08-05", "15:40");

    public static void 예약_추가(final JdbcTemplate jdbcTemplate, final Reservation reservation) {
        final String sql = "INSERT INTO reservation (name, date, time) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, reservation.getName(), reservation.getDate(), reservation.getTime());
    }

    public static List<Reservation> 예약_조회(final JdbcTemplate jdbcTemplate) {
        final String sql = "select * from reservation";
        return jdbcTemplate.query(sql, ROW_MAPPER);
    }
}

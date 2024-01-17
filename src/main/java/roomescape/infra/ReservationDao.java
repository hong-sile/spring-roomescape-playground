package roomescape.infra;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import roomescape.domain.Reservation;

@Repository
public class ReservationDao {

    private static final RowMapper<Reservation> ROW_MAPPER = (rs, rowNum) -> {
        final long id = rs.getLong("id");
        final String name = rs.getString("name");
        final String date = rs.getString("date");
        final String time = rs.getString("time");
        return new Reservation(id, name, date, time);
    };

    private final JdbcTemplate jdbcTemplate;

    public ReservationDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Reservation> getReservations() {
        final String sql = "select * from reservation";
        return jdbcTemplate.query(sql, ROW_MAPPER);
    }
}

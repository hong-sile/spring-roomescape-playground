package roomescape.dao;

import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import roomescape.domain.Time;

@Repository
public class TimeDao {

    private static final RowMapper<Time> ROW_MAPPER = (rs, rowNum) -> {
        final long id = rs.getLong("id");
        final String time = rs.getString("time");
        return new Time(id, time);
    };

    private final JdbcTemplate jdbcTemplate;

    public TimeDao(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long save(final Time time) {
        final String sql = "INSERT INTO time (time) VALUES (?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, time.getTime());
            return preparedStatement;
        }, keyHolder);

        return (Long) keyHolder.getKey();
    }

    public List<Time> findTimes() {
        final String sql = "select * from time";
        return jdbcTemplate.query(sql, ROW_MAPPER);
    }

    public void removeTime(final Long id) {
        final String sql = "DELETE FROM time WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Time findById(final Long timeId) {
        final String sql = "select * from time where id = ?";
        return jdbcTemplate.queryForObject(sql, ROW_MAPPER, timeId);
    }
}

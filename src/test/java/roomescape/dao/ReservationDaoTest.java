package roomescape.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import roomescape.domain.Reservation;
import roomescape.domain.Time;

@SpringBootTest
@Transactional
class ReservationDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private TimeDao timeDao;

    @Test
    void 예약을_정상적으로_조회할_수_있다() {
        //given --> 사전세팅
        final Time time = 시간_저장();
        final Reservation reservation = 예약_저장(time);

        //when --> 검증해야할 로직을 실행
        final List<Reservation> actual = reservationDao.getReservations();

        //then --> 검증을 합니다.
        assertThat(actual)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrder(reservation);
    }

    private Reservation 예약_저장(final Time time) {
        final Reservation reservation = new Reservation("예약1번", "01.26", time);
        final Long id = reservationDao.insertReservation(reservation);
        return new Reservation(id, reservation.getName(), reservation.getDate(), reservation.getTime());
    }

    private Time 시간_저장() {
        final Time time = new Time("10:00");
        final Long id = timeDao.save(time);
        return Time.toEntity(id, time);
    }

}

package roomescape.infra;

import static org.assertj.core.api.Assertions.assertThat;
import static roomescape.infra.ReservationStep.브라운_예약;
import static roomescape.infra.ReservationStep.예약_추가;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import roomescape.domain.Reservation;

@SpringBootTest
class ReservationDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ReservationDao reservationDao;

    @Test
    void 예약정보_전체_조회() {
        //given
        예약_추가(jdbcTemplate, 브라운_예약);
        //when
        final List<Reservation> actual = reservationDao.getReservations();

        //then
        assertThat(actual)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrder(브라운_예약);
    }

}

package roomescape.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import roomescape.dao.ReservationDao;

@SpringBootTest
@Transactional
class ReservationDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ReservationDao reservationDao;

//    @Test
//    void 예약정보_전체_조회_테스트() {
//        //given
//        reservationDao.insertReservation(브라운_예약);
//        //when
//        final List<Reservation> actual = reservationDao.getReservations();
//
//        //then
//        assertThat(actual)
//                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
//                .containsExactlyInAnyOrder(브라운_예약);
//    }
//
//    @Test
//    void 예약_추가_테스트() {
//        //when
//        reservationDao.insertReservation(브라운_예약);
//
//        //then
//        final List<Reservation> actual = reservationDao.getReservations();
//        assertThat(actual)
//                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
//                .containsExactlyInAnyOrder(브라운_예약);
//    }
//
//    @Test
//    void 예약_삭제_테스트() {
//        //given
//        final Long id = reservationDao.insertReservation(브라운_예약);
//        //when
//        reservationDao.removeReservation(id);
//        //then
//        assertThat(reservationDao.getReservations())
//                .isEmpty();
//    }
}

package roomescape.service;

import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.dao.ReservationDao;
import roomescape.dao.TimeDao;
import roomescape.domain.Reservation;
import roomescape.domain.Time;
import roomescape.dto.ReservationAddRequest;

@Service
public class ReservationService {

    private final ReservationDao reservationDao;
    private final TimeDao timeDao;

    public ReservationService(final ReservationDao reservationDao, final TimeDao timeDao) {
        this.reservationDao = reservationDao;
        this.timeDao = timeDao;
    }

    public Reservation bookReservation(final ReservationAddRequest request) {
        final Long timeId = Long.valueOf(request.getTime());
        final Time time = timeDao.findById(timeId);

        final Reservation reservation = new Reservation(request.getName(), request.getDate(), time);
        final Long reservationId = reservationDao.insertReservation(reservation);

        return Reservation.toEntity(reservationId, reservation);
    }

    public List<Reservation> findReservationsAll() {
        return reservationDao.getReservations();
    }

    public void deleteReservation(final Long id) {
        reservationDao.removeReservation(id);
    }
}

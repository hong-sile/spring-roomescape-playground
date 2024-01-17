package roomescape.view;

public class ReservationAddRequest {

    private final String name;
    private final String date;
    private final String time;

    public ReservationAddRequest(final String name, final String date, final String time) {
        validateReservation(name, date, time);
        this.name = name;
        this.date = date;
        this.time = time;
    }

    private void validateReservation(final String name, final String date, final String time) {
        if (date.isEmpty() || name.isEmpty() || time.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}

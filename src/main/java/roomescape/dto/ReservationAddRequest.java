package roomescape.dto;

public class ReservationAddRequest {

    private final String date;
    private final String name;
    private final String time;

    private ReservationAddRequest() {
        this(null, null, null);
    }

    public ReservationAddRequest(final String date, final String name, final String time) {
        this.date = date;
        this.name = name;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
}

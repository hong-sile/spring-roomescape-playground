package roomescape.domain;

public class Reservation {

    private final Long id;
    private final String name;
    private final String date;
    private final Time time;

    public static Reservation toEntity(final Long id, final Reservation reservation) {
        return new Reservation(id, reservation.getName(), reservation.getDate(), reservation.getTime());
    }

    public Reservation(final Long id, final String name, final String date, final Time time) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public Reservation(final String name, final String date, final Time time) {
        this(null, name, date, time);
    }

    private Reservation() {
        this(null, null, null, null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}

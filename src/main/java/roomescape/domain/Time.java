package roomescape.domain;

public class Time {

    private final Long id; // -> DB에서 저장되었을 때 식별자
    private final String time;

    public static Time toEntity(final Long id, final Time time) {
        return new Time(id, time.getTime());
    }

    private Time() {
        this(null, null);
    }

    public Time(final Long id, final String time) {
        this.id = id;
        this.time = time;
    }

    public Time(final String time) {
        this(null, time);
    }

    public Long getId() {
        return id;
    }

    public String getTime() {
        return time;
    }
}

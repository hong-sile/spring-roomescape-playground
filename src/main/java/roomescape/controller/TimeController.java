package roomescape.controller;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.dao.TimeDao;
import roomescape.domain.Time;

@RestController
public class TimeController {

    private final TimeDao timeDao;

    public TimeController(final TimeDao timeDao) {
        this.timeDao = timeDao;
    }

    @PostMapping("/times")
    public ResponseEntity<Time> addTime(@RequestBody final Time time) {
        final Long id = timeDao.save(time);
        final Time timeEntity = Time.toEntity(id, time);

        return ResponseEntity.created(URI.create("/times/" + timeEntity.getId()))
                .body(timeEntity);
    }

    @GetMapping("/times")
    public ResponseEntity<List<Time>> getTimes() {
        final List<Time> foundTimes = timeDao.findTimes();
        return ResponseEntity.ok(foundTimes);
    }

    @DeleteMapping("/times/{id}")
    public ResponseEntity<Void> deleteTime(@PathVariable final Long id) {
        timeDao.removeTime(id);
        return ResponseEntity.noContent().build();
    }
}

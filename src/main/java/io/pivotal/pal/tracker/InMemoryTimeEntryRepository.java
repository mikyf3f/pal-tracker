package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

//@Component
public class InMemoryTimeEntryRepository implements  TimeEntryRepository {

    private long currentId = 0;
    Map<Long, TimeEntry> map = new HashMap<Long, TimeEntry>();

    public TimeEntry create(TimeEntry timeEntry) {
        long key = getKey();
        timeEntry.setId(key);
        map.put(key, timeEntry);
        return timeEntry;
    }

    public TimeEntry find(Long id) {
        return map.get(id);
    }

    public List<TimeEntry> list() {
        return map.values().stream().collect(Collectors.toList());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {
        if (find(id) == null) {
            return null;
        }
        timeEntry.setId(id);
        map.put(id, timeEntry);
        return timeEntry;
    }

    public void delete(Long id) {
        map.remove(id);
    }

    private synchronized long getKey() {
        currentId++;
        return currentId;
    }
}

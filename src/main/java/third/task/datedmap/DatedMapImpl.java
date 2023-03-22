package third.task.datedmap;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class DatedMapImpl implements  DatedMap {
    HashMap<String,String> map = new HashMap<>();
    HashMap<String,Date> date = new HashMap<>();
    @Override
    public void put(String key, String value) {
        map.put(key, value);
        date.put(key, new Date());
    }

    @Override
    public String get(String key) {
        return map.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    @Override
    public void remove(String key) {
        map.remove(key);
        date.remove(key);
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Date getKeyLastInsertionDate(String key) {
        return date.get(key);
    }
}

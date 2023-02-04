package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class InMemoryKV implements KeyValueStorage {

    private Map<String, String> storage;

    public InMemoryKV(Map<String, String> storage) {
        this.storage = storage.entrySet()
                              .stream()
                              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public void set(String key, String value) {
        this.storage.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.storage.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return this.storage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return this.storage.entrySet()
                           .stream()
                           .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
// END

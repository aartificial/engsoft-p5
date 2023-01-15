package event;

import java.util.HashMap;
import java.util.Map;

public class EventBus {
    private static final Map<String, Map<String, EventListener>> events = new HashMap<>();

    public static void subscribe(String event, String subscriber, EventListener listener) {
        if (events.containsKey(event)) {
            Map<String, EventListener> e = events.get(event);
            e.putIfAbsent(subscriber, listener);
        }
    }

    public static void unsubscribe(String event, String subscriber) {
        if (events.containsKey(event)) {
            Map<String, EventListener> e = events.get(event);
            e.remove(subscriber);
        }
    }

    public static void notify(String event, String message) {
        if (!events.containsKey(event)) return;
        var set = events.get(event).entrySet();
        for (var entry : set)
            entry.getValue().listen(message);
    }

    public static void create(String event) {
        events.put(event, new HashMap<>());
    }
}

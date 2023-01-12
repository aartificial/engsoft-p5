package event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventBus {
    private static final Map<String, List<EventListener>> events = new HashMap<>();

    public static void subscribe(String event, EventListener listener) {
        events.get(event).add(listener);
    }

    public static void unsubscribe(String event, EventListener listener) {
        events.get(event).remove(listener);
    }

    public static void notify(String event, String message) {
        List<EventListener> listeners = events.get(event);
        for (EventListener listener :
                listeners) {
            listener.listen(message);
        }
    }

    public static void create(String event) {
        events.put(event, new ArrayList<>());
    }
}

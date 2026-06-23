package cc.maicra999.vaccinium.event;

import cc.maicra999.vaccinium.Vaccinium;
import cc.maicra999.vaccinium.event.impl.WorldEventListener;
import java.util.ArrayList;
import java.util.List;

public class EventListenerRegistry {

    private final List<EventListener> listeners = new ArrayList<>();

    public EventListenerRegistry(Vaccinium vaccinium) {
        add(new WorldEventListener(vaccinium));
    }

    private void add(EventListener eventListener) {
        listeners.add(eventListener);
    }

    public void registerAll() {
        for (EventListener listener : listeners) {
            listener.register();
        }
    }
}

package cc.maicra999.vaccinium.event;

import cc.maicra999.vaccinium.Vaccinium;
import org.bukkit.event.Listener;

public abstract class EventListener implements Listener {

    protected final Vaccinium vaccinium;

    public EventListener(Vaccinium vaccinium) {
        this.vaccinium = vaccinium;
    }

    public void register() {
        vaccinium.getServer().getPluginManager().registerEvents(this, vaccinium);
    }
}

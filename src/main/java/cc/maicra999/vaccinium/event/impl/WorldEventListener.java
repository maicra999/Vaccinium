package cc.maicra999.vaccinium.event.impl;

import cc.maicra999.vaccinium.Vaccinium;
import cc.maicra999.vaccinium.VacciniumConfig;
import cc.maicra999.vaccinium.event.EventListener;
import cc.maicra999.vaccinium.util.Logs;
import cc.maicra999.vaccinium.util.ReflectionUtil;
import cc.maicra999.vaccinium.world.WorldStemType;
import java.lang.reflect.Field;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.dimension.LevelStem;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.WorldLoadEvent;
import org.slf4j.Logger;

public class WorldEventListener extends EventListener {

    private static final Logger LOGGER = Logs.logger();

    private static final Field SERVER_LEVEL_TYPE_KEY_FIELD =
            ReflectionUtil.getDeclaredFieldAndSetAccessible(ServerLevel.class, "typeKey");

    public WorldEventListener(Vaccinium vaccinium) {
        super(vaccinium);
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        VacciniumConfig config = vaccinium.config();
        String worldName = event.getWorld().getName();

        WorldStemType type = config.worldStemTypes.get(worldName);
        if (type == null) {
            String worldNameAlt = event.getWorld().key().asString();
            type = config.worldStemTypes.get(worldNameAlt);
            if (type == null) {
                return;
            }
        }

        ResourceKey<LevelStem> stem = type.getStem();
        ServerLevel nmsLevel = ((CraftWorld) event.getWorld()).getHandle();
        ReflectionUtil.setFieldValueFinal(SERVER_LEVEL_TYPE_KEY_FIELD, nmsLevel, stem);
        LOGGER.info("Set world stem for world '{}' to {}", worldName, stem.identifier());
    }
}

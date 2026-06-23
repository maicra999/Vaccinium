package cc.maicra999.vaccinium;

import cc.maicra999.vaccinium.world.WorldStemType;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class VacciniumConfig {

    public static final int CURRENT_CONFIG_VERSION = 1;

    public Map<String, WorldStemType> worldStemTypes = ImmutableMap.of(
            "shigen", WorldStemType.OVERWORLD,
            "world_minecraft_shigen", WorldStemType.OVERWORLD);

    public int configVersion = -1;
}

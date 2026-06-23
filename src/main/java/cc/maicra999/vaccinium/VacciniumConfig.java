package cc.maicra999.vaccinium;

import cc.maicra999.vaccinium.item.AttributeType;
import cc.maicra999.vaccinium.world.WorldStemType;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Set;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class VacciniumConfig {

    public static final int CURRENT_CONFIG_VERSION = 1;

    public Map<String, WorldStemType> worldStemTypes = ImmutableMap.of(
            "shigen", WorldStemType.OVERWORLD,
            "world_minecraft_shigen", WorldStemType.OVERWORLD);

    public Set<AttributeType> disallowedItemAttributes = Set.of(
            AttributeType.MAX_HEALTH,
            AttributeType.MOVEMENT_SPEED,
            AttributeType.FLYING_SPEED,
            AttributeType.ATTACK_KNOCKBACK,
            AttributeType.FALL_DAMAGE_MULTIPLIER,
            AttributeType.LUCK,
            AttributeType.MAX_ABSORPTION,
            AttributeType.SAFE_FALL_DISTANCE,
            AttributeType.SCALE,
            AttributeType.STEP_HEIGHT,
            AttributeType.GRAVITY,
            AttributeType.JUMP_STRENGTH,
            AttributeType.BURNING_TIME,
            AttributeType.CAMERA_DISTANCE,
            AttributeType.MOVEMENT_EFFICIENCY,
            AttributeType.WATER_MOVEMENT_EFFICIENCY,
            AttributeType.BLOCK_INTERACTION_RANGE,
            AttributeType.ENTITY_INTERACTION_RANGE,
            AttributeType.BLOCK_BREAK_SPEED,
            AttributeType.SUBMERGED_MINING_SPEED,
            AttributeType.SPAWN_REINFORCEMENTS,
            AttributeType.AIR_DRAG_MODIFIER,
            AttributeType.FRICTION_MODIFIER,
            AttributeType.BOUNCINESS);

    public int maxEnchantmentLevel = 5;

    public int configVersion = -1;
}

package cc.maicra999.vaccinium.item;

import cc.maicra999.vaccinium.util.Logs;
import com.google.common.collect.ImmutableMap;
import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.attribute.Attribute;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public enum AttributeType {
    MAX_HEALTH(Attribute.MAX_HEALTH),
    FOLLOW_RANGE(Attribute.FOLLOW_RANGE),
    KNOCKBACK_RESISTANCE(Attribute.KNOCKBACK_RESISTANCE),
    MOVEMENT_SPEED(Attribute.MOVEMENT_SPEED),
    FLYING_SPEED(Attribute.FLYING_SPEED),
    ATTACK_DAMAGE(Attribute.ATTACK_DAMAGE),
    ATTACK_KNOCKBACK(Attribute.ATTACK_KNOCKBACK),
    ATTACK_SPEED(Attribute.ATTACK_SPEED),
    ARMOR(Attribute.ARMOR),
    ARMOR_TOUGHNESS(Attribute.ARMOR_TOUGHNESS),
    FALL_DAMAGE_MULTIPLIER(Attribute.FALL_DAMAGE_MULTIPLIER),
    LUCK(Attribute.LUCK),
    MAX_ABSORPTION(Attribute.MAX_ABSORPTION),
    SAFE_FALL_DISTANCE(Attribute.SAFE_FALL_DISTANCE),
    SCALE(Attribute.SCALE),
    STEP_HEIGHT(Attribute.STEP_HEIGHT),
    GRAVITY(Attribute.GRAVITY),
    JUMP_STRENGTH(Attribute.JUMP_STRENGTH),
    BURNING_TIME(Attribute.BURNING_TIME),
    CAMERA_DISTANCE(Attribute.CAMERA_DISTANCE),
    EXPLOSION_KNOCKBACK_RESISTANCE(Attribute.EXPLOSION_KNOCKBACK_RESISTANCE),
    MOVEMENT_EFFICIENCY(Attribute.MOVEMENT_EFFICIENCY),
    OXYGEN_BONUS(Attribute.OXYGEN_BONUS),
    WATER_MOVEMENT_EFFICIENCY(Attribute.WATER_MOVEMENT_EFFICIENCY),
    TEMPT_RANGE(Attribute.TEMPT_RANGE),
    BLOCK_INTERACTION_RANGE(Attribute.BLOCK_INTERACTION_RANGE),
    ENTITY_INTERACTION_RANGE(Attribute.ENTITY_INTERACTION_RANGE),
    BLOCK_BREAK_SPEED(Attribute.BLOCK_BREAK_SPEED),
    MINING_EFFICIENCY(Attribute.MINING_EFFICIENCY),
    SNEAKING_SPEED(Attribute.SNEAKING_SPEED),
    SUBMERGED_MINING_SPEED(Attribute.SUBMERGED_MINING_SPEED),
    SWEEPING_DAMAGE_RATIO(Attribute.SWEEPING_DAMAGE_RATIO),
    SPAWN_REINFORCEMENTS(Attribute.SPAWN_REINFORCEMENTS),
    WAYPOINT_TRANSMIT_RANGE(Attribute.WAYPOINT_TRANSMIT_RANGE),
    WAYPOINT_RECEIVE_RANGE(Attribute.WAYPOINT_RECEIVE_RANGE),
    AIR_DRAG_MODIFIER(Attribute.AIR_DRAG_MODIFIER),
    FRICTION_MODIFIER(Attribute.FRICTION_MODIFIER),
    BOUNCINESS(Attribute.BOUNCINESS),
    BELOW_NAME_DISTANCE(Attribute.BELOW_NAME_DISTANCE),
    NAME_TAG_DISTANCE(Attribute.NAME_TAG_DISTANCE),
    ;

    private static final Logger LOGGER = Logs.logger();
    private static final Map<Attribute, AttributeType> BY_ATTRIBUTE;

    static {
        ImmutableMap.Builder<Attribute, AttributeType> builder = ImmutableMap.builder();
        for (AttributeType type : values()) {
            builder.put(type.asBukkit(), type);
        }
        BY_ATTRIBUTE = builder.build();

        checkTypes();
    }

    private final Attribute bukkit;

    AttributeType(Attribute bukkit) {
        this.bukkit = bukkit;
    }

    public Attribute asBukkit() {
        return bukkit;
    }

    public static @Nullable AttributeType fromBukkit(Attribute attribute) {
        return BY_ATTRIBUTE.get(attribute);
    }

    private static void checkTypes() {
        Set<Attribute> attributes = new HashSet<>();
        RegistryAccess.registryAccess().getRegistry(RegistryKey.ATTRIBUTE).forEach(attributes::add);

        for (AttributeType type : values()) {
            attributes.remove(type.asBukkit());
        }

        for (Attribute attribute : attributes) {
            LOGGER.warn(
                    "Attribute '{}' is missing in AttributeType enum",
                    attribute.key().asString());
        }
    }
}

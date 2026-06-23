package cc.maicra999.vaccinium.world;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.dimension.LevelStem;

public enum WorldStemType {
    OVERWORLD(LevelStem.OVERWORLD),
    NETHER(LevelStem.NETHER),
    END(LevelStem.END),
    ;

    private final ResourceKey<LevelStem> stem;

    WorldStemType(ResourceKey<LevelStem> stem) {
        this.stem = stem;
    }

    public ResourceKey<LevelStem> getStem() {
        return stem;
    }
}

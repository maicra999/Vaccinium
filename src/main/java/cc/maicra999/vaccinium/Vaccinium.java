package cc.maicra999.vaccinium;

import cc.maicra999.vaccinium.event.EventListenerRegistry;
import cc.maicra999.vaccinium.util.Logs;
import java.io.IOException;
import java.nio.file.Path;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

public class Vaccinium extends JavaPlugin {

    private static final Logger LOGGER = Logs.logger();

    private final YamlConfigurationLoader configLoader;

    private VacciniumConfig config;
    private EventListenerRegistry listenerRegistry;

    public Vaccinium() {
        this.configLoader = YamlConfigurationLoader.builder()
                .nodeStyle(NodeStyle.BLOCK)
                .path(getConfigPath())
                .build();
    }

    @Override
    public void onEnable() {
        try {
            loadAndUpdateConfig();
        } catch (IOException e) {
            LOGGER.error("Failed to load or update config.yml");
            throw new RuntimeException(e);
        }

        this.listenerRegistry = new EventListenerRegistry(this);
        this.listenerRegistry.registerAll();
    }

    public void loadAndUpdateConfig() throws IOException {
        CommentedConfigurationNode root = configLoader.load();
        this.config = root.get(VacciniumConfig.class);

        if (config != null && config.configVersion < VacciniumConfig.CURRENT_CONFIG_VERSION) {
            // Save the config file with new fields
            config.configVersion = VacciniumConfig.CURRENT_CONFIG_VERSION;
            root.set(VacciniumConfig.class, config);
            configLoader.save(root);
        }
    }

    public VacciniumConfig config() {
        return config;
    }

    private Path getConfigPath() {
        return getDataFolder().toPath().resolve("config.yml");
    }
}

package org.abstruck.plugin.firework;

import org.abstruck.plugin.firework.command.FireworkCommandExecutor;
import org.abstruck.plugin.firework.runtime.RuntimeFireworkProvider;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @author Astrack
 */
public final class Firework extends JavaPlugin {
    private static RuntimeFireworkProvider runtimeFireworkProvider;
    private static JavaPlugin INSTANCE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        runtimeFireworkProvider = RuntimeFireworkProvider.createRuntimeFireworkProvider();
        INSTANCE=this;

        File fireworksFile = new File(getDataFolder().getPath()+"/fireworks.yml");
        if (!fireworksFile.exists()){
            try {
                YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(getResource("fireworks.yml")))).save(fireworksFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Class.forName("org.abstruck.plugin.firework.Messages");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        getCommand("firework").setExecutor(new FireworkCommandExecutor());

        getLogger().info("plugin enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("plugin disabled");
    }

    public static RuntimeFireworkProvider getRuntimeFireworkProvider(){
        return runtimeFireworkProvider;
    }

    public static JavaPlugin getInstance(){
        return INSTANCE;
    }
}

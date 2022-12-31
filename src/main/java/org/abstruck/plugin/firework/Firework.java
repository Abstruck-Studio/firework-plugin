package org.abstruck.plugin.firework;

import org.abstruck.plugin.firework.command.FireworkCommandExecutor;
import org.abstruck.plugin.firework.runtime.RuntimeFireworkProvider;
import org.bukkit.plugin.java.JavaPlugin;

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

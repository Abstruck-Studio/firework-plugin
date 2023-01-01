package org.abstruck.plugin.firework;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/**
 * @author Astrack
 */
public class Messages {
    private static final YamlConfiguration messagesFile;

    static {
        File file = new File(Firework.getInstance().getDataFolder().getPath()+"/messages.yml");

        if (!file.exists()){
            try {
                YamlConfiguration.loadConfiguration(new InputStreamReader(Objects.requireNonNull(Firework.getInstance().getResource("messages.yml")))).save(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        messagesFile = YamlConfiguration.loadConfiguration(file);
    }

    public static final String COMMAND_HELP = Objects.requireNonNull(messagesFile.getString("command_help"));

}

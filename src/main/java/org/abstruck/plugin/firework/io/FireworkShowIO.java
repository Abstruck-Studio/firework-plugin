package org.abstruck.plugin.firework.io;

import org.abstruck.plugin.firework.Firework;
import org.abstruck.plugin.firework.runtime.FireworkShow;
import org.abstruck.plugin.firework.runtime.Fireworks;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Astrack
 */
public class FireworkShowIO {
    private static final File FILE = new File(Firework.getInstance().getDataFolder().getPath()+"/fireworks.yml");
    private static final FileConfiguration FIREWORK_SHOWS_FILE = YamlConfiguration.loadConfiguration(FILE);
    private static Set<String> fireworkNames;

    static {
        reloadFireworkShowNames();
    }

    public static FireworkShow readFireworkShow(String name){
        ConfigurationSection fireworkShowFile = FIREWORK_SHOWS_FILE.getConfigurationSection(name);
        FireworkShow fireworkShow = FireworkShow.createFireworkShow(name);

        assert fireworkShowFile != null;

        Set<String> fireworksKeys = fireworkShowFile.getKeys(false);
        fireworksKeys.forEach(
                fireworksPath->{
                    ConfigurationSection fireworksFile = fireworkShowFile.getConfigurationSection(fireworksPath);

                    if (fireworksFile==null){
                        throw new RuntimeException("fireworks is null");
                    }

                    List<org.abstruck.plugin.firework.runtime.Firework> fireworkList = new ArrayList<>();
                    Set<String> fireworkKeys = fireworksFile.getKeys(false);


                    fireworkKeys.forEach(
                            fireworkPath->{

                                if ("period".equals(fireworkPath)){
                                    return;
                                }

                                ConfigurationSection fireworkFile = fireworksFile.getConfigurationSection(fireworkPath);

                                org.abstruck.plugin.firework.runtime.Firework firework;

                                if (fireworkFile == null) {
                                    throw new RuntimeException("firework file is null");
                                }

                                firework = readFirework(fireworkFile);

                                fireworkList.add(firework);
                            }
                    );

                    fireworkShow.addFireworks(Fireworks.createFireworks(fireworkList,fireworksFile.getInt("period")));
                }
        );

        return fireworkShow;
    }

    private static org.abstruck.plugin.firework.runtime.Firework readFirework(ConfigurationSection fireworkFile) {
        List<Color> mainColors = new ArrayList<>();
        List<Color> fadeColors = new ArrayList<>();
        FireworkEffect.Type type;
        boolean flicker;
        boolean trail;
        int power;
        int xPos;
        int yPos;
        int zPos;

        readMainColors(fireworkFile, mainColors);
        readFadeColors(fireworkFile, fadeColors);
        type = getType(fireworkFile);
        flicker = isFlicker(fireworkFile);
        trail = isTrail(fireworkFile);
        power = getPower(fireworkFile);
        xPos = getX_pos(fireworkFile);
        yPos = getY_pos(fireworkFile);
        zPos = getZ_pos(fireworkFile);

        return org.abstruck.plugin.firework.runtime.Firework.createFirework(
                mainColors,
                fadeColors,
                type,
                flicker,
                trail,
                power,
                xPos,
                yPos,
                zPos
        );
    }

    private static void readMainColors(ConfigurationSection fireworkFile, List<Color> mainColors) {
        fireworkFile.getIntegerList("main_colors").forEach(
                i-> mainColors.add(Color.fromRGB(i))
        );
    }

    private static void readFadeColors(ConfigurationSection fireworkFile, List<Color> fadeColors) {
        fireworkFile.getIntegerList("fade_colors").forEach(
                i-> fadeColors.add(Color.fromRGB(i))
        );
    }

    private static FireworkEffect.Type getType(ConfigurationSection fireworkFile) {
        return FireworkEffect.Type.valueOf(fireworkFile.getString("type"));
    }

    private static boolean isFlicker(ConfigurationSection fireworkFile) {
        return fireworkFile.getBoolean("flicker");
    }

    private static boolean isTrail(ConfigurationSection fireworkFile) {
        return fireworkFile.getBoolean("trail");
    }

    private static int getPower(ConfigurationSection fireworkFile) {
        return fireworkFile.getInt("power");
    }

    private static int getX_pos(ConfigurationSection fireworkFile) {
        return fireworkFile.getInt("x_pos");
    }

    private static int getY_pos(ConfigurationSection fireworkFile) {
        return fireworkFile.getInt("y_pos");
    }

    private static int getZ_pos(ConfigurationSection fireworkFile) {
        return fireworkFile.getInt("z_pos");
    }

    public static boolean checkFireworkShow(String name){
        return fireworkNames.contains(name);
    }
    public static void reloadFireworkShowNames(){
        fireworkNames = FIREWORK_SHOWS_FILE.getKeys(false);
    }
}

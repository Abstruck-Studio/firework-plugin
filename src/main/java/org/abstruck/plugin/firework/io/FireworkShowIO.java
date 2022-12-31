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
import java.util.Objects;
import java.util.Set;

/**
 * @author Astrack
 */
public class FireworkShowIO {
    private static File file = new File(Firework.getInstance().getDataFolder().getPath()+"/fireworks.yml");
    private static final FileConfiguration fireworkShowsFile = YamlConfiguration.loadConfiguration(file);
    private static Set<String> fireworkNames;

    static {
        reloadFireworkShowNames();
    }
//    public static FireworkShow readFireworkShow(String name){
//        FireworkShow fireworkShow = FireworkShow.createFireworkShow(name);
//
//        ConfigurationSection fwsf = fireworkShowFile.getConfigurationSection("fireworks."+name);
//
//        checkNull(fwsf);
//        //在firework show层
//        fwsf.getKeys(false).stream().peek(
//                (String key)-> readFireworks(fireworkShow, fwsf, key)
//        );
//
//        return fireworkShow;
//    }
//
//    private static void checkNull(ConfigurationSection fwsf) {
//        if (fwsf == null){
//            throw new RuntimeException("an exception curred when read the firework show file");
//        }
//    }
//
//    private static void readFireworks(FireworkShow fireworkShow, ConfigurationSection fwsf, String key) {
//        List< org.abstruck.plugin.firework.runtime.Firework > fireworkList = new ArrayList<>();
//        //在fireworks 层
//        Objects.requireNonNull(fwsf.getConfigurationSection(key)).getKeys(false).stream().map(
//                (String str)->{
//                    if ("period".equals(str)){
//                        return str;
//                    }
//                    //在firework层
//                    readFirework(fwsf, key, fireworkList, str);
//                    return str;
//                }
//        );
//
//        fireworkShow.addFireworks(Fireworks.createFireworks(fireworkList, fwsf.getInt(key +".period")));
//    }
//
//    private static void readFirework(ConfigurationSection fwsf, String key, List<org.abstruck.plugin.firework.runtime.Firework> fireworkList, String str) {
//        ConfigurationSection fwf = fwsf.getConfigurationSection(key+"."+ str);
//
//        List<Color> mainColor = new ArrayList<>();
//        List<Color> fadeColor = new ArrayList<>();
//        FireworkEffect.Type type;
//        boolean flicker;
//        boolean trail;
//        int power;
//        int xPos;
//        int yPos;
//        int zPos;
//
//        readMainColors(fwf, mainColor);
//
//        readFadeColors(fwf, fadeColor);
//
//        type = readType(fwf);
//        flicker = readIsFlicker(fwf);
//        trail = isTrail(fwf);
//        power = readPower(fwf);
//        xPos = readXPos(fwf);
//        yPos = readYPos(fwf);
//        zPos = readZPos(fwf);
//
//        addNewFireworkToFireworkList(fireworkList, mainColor, fadeColor, type, flicker, trail, power, xPos, yPos, zPos);
//
//    }
//
//    private static void addNewFireworkToFireworkList(List<org.abstruck.plugin.firework.runtime.Firework> fireworkList, List<Color> mainColor, List<Color> fadeColor, FireworkEffect.Type type, boolean flicker, boolean trail, int power, int xPos, int yPos, int zPos) {
//        fireworkList.add(org.abstruck.plugin.firework.runtime.Firework.createFirework(
//                mainColor,
//                fadeColor,
//                type,
//                flicker,
//                trail,
//                power,
//                xPos,
//                yPos,
//                zPos
//        ));
//    }
//
//    private static int readZPos(ConfigurationSection fwf) {
//        int zPos;
//        zPos = fwf.getInt("z_pos");
//        return zPos;
//    }
//
//    private static int readYPos(ConfigurationSection fwf) {
//        int yPos;
//        yPos = fwf.getInt("y_pos");
//        return yPos;
//    }
//
//    private static int readXPos(ConfigurationSection fwf) {
//        int xPos;
//        xPos = fwf.getInt("x_pos");
//        return xPos;
//    }
//
//    private static int readPower(ConfigurationSection fwf) {
//        int power;
//        power = fwf.getInt("power");
//        return power;
//    }
//
//    private static boolean isTrail(ConfigurationSection fwf) {
//        boolean trail;
//        trail= fwf.getBoolean("trail");
//        return trail;
//    }
//
//    private static boolean readIsFlicker(ConfigurationSection fwf) {
//        boolean flicker;
//        flicker= fwf.getBoolean("flicker");
//        return flicker;
//    }
//
//    private static FireworkEffect.Type readType(ConfigurationSection fwf) {
//        FireworkEffect.Type type;
//        type= FireworkEffect.Type.valueOf(fwf.getString("type"));
//        return type;
//    }
//
//    private static void readFadeColors(ConfigurationSection fwf, List<Color> fadeColor) {
//        fwf.getIntegerList("fade_colors").stream().peek(
//                (Integer integer)-> fadeColor.add(Color.fromRGB(integer))
//        );
//    }
//
//    private static void readMainColors(ConfigurationSection fwf, List<Color> mainColor) {
//        fwf.getIntegerList("main_colors").stream().peek(
//                (Integer integer)-> mainColor.add(Color.fromRGB(integer))
//        );
//    }

    public static FireworkShow readFireworkShow(String name){
        ConfigurationSection fireworkShowFile = fireworkShowsFile.getConfigurationSection(name);
        FireworkShow fireworkShow = FireworkShow.createFireworkShow(name);

        assert fireworkShowFile != null;
        for (String fireworksPath:fireworkShowFile.getKeys(false)){
            ConfigurationSection fireworksFile = fireworkShowFile.getConfigurationSection(fireworksPath);

            List<org.abstruck.plugin.firework.runtime.Firework> fireworkList = new ArrayList<>();

            for (String fireworkPath:fireworksFile.getKeys(false)){

                if ("period".equals(fireworkPath)){
                    continue;
                }
                ConfigurationSection fireworkFile = fireworksFile.getConfigurationSection(fireworkPath);

                List<Color> mainColors = new ArrayList<>();
                List<Color> fadeColors = new ArrayList<>();
                FireworkEffect.Type type;
                boolean flicker;
                boolean trail;
                int power;
                int xPos;
                int yPos;
                int zPos;

                fireworkFile.getIntegerList("main_colors").stream().forEach(
                        i->{
                            mainColors.add(Color.fromRGB(i));
                        }
                );
                fireworkFile.getIntegerList("fade_colors").stream().forEach(
                        i->{
                            fadeColors.add(Color.fromRGB(i));
                        }
                );
                type = FireworkEffect.Type.valueOf(fireworkFile.getString("type"));
                flicker = fireworkFile.getBoolean("flicker");
                trail = fireworkFile.getBoolean("trail");
                power = fireworkFile.getInt("power");
                xPos = fireworkFile.getInt("x_pos");
                yPos = fireworkFile.getInt("y_pos");
                zPos = fireworkFile.getInt("z_pos");

                org.abstruck.plugin.firework.runtime.Firework firework = org.abstruck.plugin.firework.runtime.Firework.createFirework(
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

                fireworkList.add(firework);
            }
            fireworkShow.addFireworks(Fireworks.createFireworks(fireworkList,fireworksFile.getInt("period")));
        }
        return fireworkShow;
    }
    public static boolean checkFireworkShow(String name){
        return fireworkNames.contains(name);
    }
    public static void reloadFireworkShowNames(){
        fireworkNames = fireworkShowsFile.getKeys(false);
    }
}

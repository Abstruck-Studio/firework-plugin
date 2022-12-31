package org.abstruck.plugin.firework.runtime;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;


import java.util.List;

public class Firework {
    private List<Color> mainColors;
    private List<Color> fadeColors;
    private FireworkEffect.Type type;
    private boolean flicker;
    private boolean trail;
    int power;
    private int xPos;
    private int yPos;
    private int zPos;

    private Firework(List<Color> mainColors, List<Color> fadeColors, FireworkEffect.Type type,int power, boolean flicker, boolean trail, int xPos, int yPos, int zPos) {
        this.mainColors = mainColors;
        this.fadeColors = fadeColors;
        this.type = type;
        this.flicker = flicker;
        this.trail = trail;
        this.power=power;
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
    }

    public static Firework createFirework(List<Color> mainColors, List<Color> fadeColors, FireworkEffect.Type type, boolean flicker, boolean trail,int power, int xPos, int yPos, int zPos) {
        return new Firework( mainColors,fadeColors,type,power,flicker,trail,xPos,yPos,zPos);
    }

    public void launch(Player user){
        org.bukkit.entity.Firework firework = (org.bukkit.entity.Firework) user.getWorld().spawnEntity(new Location(user.getWorld(),user.getLocation().getX()+xPos,user.getLocation().getY()+yPos,user.getLocation().getZ()+zPos), EntityType.FIREWORK);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        FireworkEffect.Builder builder = FireworkEffect.builder();
        builder.withColor(mainColors)
                .withFade(fadeColors)
                .with(type)
                .flicker(flicker)
                .trail(trail);
        FireworkEffect fireworkEffect = builder.build();
        fireworkMeta.addEffect(fireworkEffect);
        fireworkMeta.setPower(power);
        firework.setFireworkMeta(fireworkMeta);
    }
}

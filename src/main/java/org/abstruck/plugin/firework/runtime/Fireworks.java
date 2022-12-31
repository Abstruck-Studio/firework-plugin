package org.abstruck.plugin.firework.runtime;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Fireworks {
    private final int period;
    private final List<Firework> fireworkList;
    private Fireworks(List<Firework> fireworkList, int period){
        this.fireworkList = fireworkList;
        this.period=period;
    }
    public static Fireworks createFireworks(int period){
        return new Fireworks(new ArrayList<>(),period);
    }

    public static Fireworks createFireworks(List<Firework> fireworkMap,int period){
        return new Fireworks(fireworkMap,period);
    }

    public int getPeriod(){
        return period;
    }

    public void addFirework(Firework firework){
        fireworkList.add(firework);
    }

    public void launch(Player user){
        for (Firework firework: fireworkList){
            firework.launch(user);
        }
    }
}

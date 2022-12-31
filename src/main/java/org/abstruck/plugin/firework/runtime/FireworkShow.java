package org.abstruck.plugin.firework.runtime;

import org.bukkit.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Astrack
 */
public class FireworkShow {
    private List<Fireworks> fireworksList=new ArrayList<>();
    private String name;

    private FireworkShow(String name){
        this.name = name;
    }

    public static FireworkShow createFireworkShow(String name){
        return new FireworkShow(name);
    }
    public String getName(){
        return name;
    }

    public void addFireworks(Fireworks fireworks){
        fireworksList.add(fireworks);
    }

    public Fireworks getFireworks(int id){
        return fireworksList.get(id);
    }

    public List<Fireworks> getFireworksList(){
        return fireworksList;
    }
}

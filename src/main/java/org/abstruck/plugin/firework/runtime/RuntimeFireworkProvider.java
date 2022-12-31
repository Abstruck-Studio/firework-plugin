package org.abstruck.plugin.firework.runtime;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Astrack
 */
public class RuntimeFireworkProvider {
    private final HashMap<String,FireworkShow> fireworkShows = new HashMap<>();

    private RuntimeFireworkProvider(){}

    public boolean hasFireworkShow(String name){
        return fireworkShows.containsKey(name);
    }

    public static RuntimeFireworkProvider createRuntimeFireworkProvider(){
        return new RuntimeFireworkProvider();
    }

    public FireworkShow getFireworkShow(String name){
        return fireworkShows.get(name);
    }

    public void removeFireworkShow(String name){
        fireworkShows.remove(name);
    }

    public void addFireworkShow(FireworkShow fireworkShow){
        fireworkShows.put(fireworkShow.getName(),fireworkShow);
    }

    public Set<String> getFireworkShowsNames(){
        return fireworkShows.keySet();
    }

}

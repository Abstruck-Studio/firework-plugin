package org.abstruck.plugin.firework.runtime;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * @author Astrack
 */
public class FireworkShowExplode extends BukkitRunnable {
    private final FireworkShow fireworkShow;
    private final Player user;
    private FireworkShowExplode(FireworkShow fireworkShow,Player user){
        this.fireworkShow = fireworkShow;
        this.user=user;
    }

    public static FireworkShowExplode createFireworkShowExplode(FireworkShow fireworkShow,Player user){
        return new FireworkShowExplode(fireworkShow,user);
    }

    @Override
    public void run() {
        for (Fireworks fireworks:fireworkShow.getFireworksList()){
            fireworks.launch(user);
            try {
                Thread.sleep(fireworks.getPeriod());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

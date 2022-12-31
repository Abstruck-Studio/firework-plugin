package org.abstruck.plugin.firework.command;

import org.abstruck.plugin.firework.Firework;
import org.abstruck.plugin.firework.io.FireworkShowIO;
import org.abstruck.plugin.firework.runtime.FireworkShowExplode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * @author Astrack
 */
public class FireworkCommandExecutor implements CommandExecutor {
    private static final String COMMAND_HEAD = "firework";
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!COMMAND_HEAD.equalsIgnoreCase(command.getName())){
            return true;
        }
        if (args==null){
            return false;
        }
        switch (args[0]){
            case "launch":
                if (sender instanceof ConsoleCommandSender){
                    sender.sendMessage("this command can only run as an player");
                    break;
                }
                if (!Firework.getRuntimeFireworkProvider().hasFireworkShow(args[1])){
                    sender.sendMessage("no loaded firework show called "+args[1]);
                    break;
                }
                FireworkShowExplode.createFireworkShowExplode(Firework.getRuntimeFireworkProvider().getFireworkShow(args[1]), (Player) sender).runTask(Firework.getInstance());
                break;
            case "load":
                if (!FireworkShowIO.checkFireworkShow(args[1])){
                    sender.sendMessage("no firework show called "+args[1]);
                    break;
                }
                Firework.getRuntimeFireworkProvider().addFireworkShow(FireworkShowIO.readFireworkShow(args[1]));
                sender.sendMessage(args[1]+" has been loaded!");
                break;
            case "list":
                sender.sendMessage(Firework.getRuntimeFireworkProvider().getFireworkShowsNames().toString());
                break;
            case "reloadFireworks":
                FireworkShowIO.reloadFireworkShowNames();
                sender.sendMessage("firework file has been reloaded");
                break;
            default:
                return false;
        }
        return true;
    }
}

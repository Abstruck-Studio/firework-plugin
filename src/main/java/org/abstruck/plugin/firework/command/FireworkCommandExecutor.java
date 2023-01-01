package org.abstruck.plugin.firework.command;

import org.abstruck.plugin.firework.Firework;
import org.abstruck.plugin.firework.Messages;
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
                launchFireworkShow(sender, args);
                break;
            case "load":
                loadFireworkShow(sender, args);
                break;
            case "list":
                sendLoadedFireworkShowList(sender);
                break;
            case "reloadFireworks":
                reloadFireworks(sender);
                break;
            case "help":
                sendHelpMessage(sender);
                break;
            default:
                return false;
        }
        return true;
    }

    private void sendHelpMessage(CommandSender sender){
        sender.sendMessage(Messages.COMMAND_HELP);
    }

    private void reloadFireworks(CommandSender sender) {
        FireworkShowIO.reloadFireworkShowNames();
        sender.sendMessage("firework file has been reloaded");
    }

    private void sendLoadedFireworkShowList(CommandSender sender) {
        sender.sendMessage(Firework.getRuntimeFireworkProvider().getFireworkShowsNames().toString());
    }

    private void loadFireworkShow(CommandSender sender, String[] args) {
        if (!FireworkShowIO.checkFireworkShow(args[1])){
            sender.sendMessage("no firework show called "+ args[1]);
            return;
        }
        Firework.getRuntimeFireworkProvider().addFireworkShow(FireworkShowIO.readFireworkShow(args[1]));
        sender.sendMessage(args[1]+" has been loaded!");
    }

    private void launchFireworkShow(CommandSender sender, String[] args) {
        if (sender instanceof ConsoleCommandSender){
            sender.sendMessage("this command can only run as an player");
            return;
        }
        if (!Firework.getRuntimeFireworkProvider().hasFireworkShow(args[1])){
            sender.sendMessage("no loaded firework show called "+ args[1]);
            return;
        }
        FireworkShowExplode.createFireworkShowExplode(Firework.getRuntimeFireworkProvider().getFireworkShow(args[1]), (Player) sender).runTask(Firework.getInstance());
    }
}

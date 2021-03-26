package fr.nil.uplacraft.updater.commands;

import fr.nil.uplacraft.updater.Core;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CheckForUpdateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("checkforupdate")){

            if(commandSender instanceof Player){

                Player p = (Player) commandSender;
                if(!p.isOp())return false;

                if(Core.isUptoDate){

                    p.sendMessage(Core.prefix + ChatColor.GREEN + "The server is up-to date no update needed.");

                }else{

                    p.sendMessage(Core.prefix + ChatColor.RED + "The server is not up-to date you can do /updateserver to update it.");


                }


            }


        }


        return false;
    }
}

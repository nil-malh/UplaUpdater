package fr.nil.uplacraft.updater.commands;

import fr.nil.uplacraft.updater.Core;
import fr.nil.uplacraft.updater.manager.ServerManager;
import fr.nil.uplacraft.updater.utils.FileDownloaderUtils;
import fr.nil.uplacraft.updater.utils.SignatureUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class UpdateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("updateserver")) {

            if (commandSender instanceof Player) {

                Player p = (Player) commandSender;
                if (!p.isOp()) return false;
                if (FileDownloaderUtils.hasFileBeenDownloaded == false) {


                    if (!SignatureUtils.isUpdated(ServerManager.getOnlineFileMD5(), SignatureUtils.getLocalMD5())) {

                        p.sendMessage(Core.prefix + "Starting file download to do a server update.");
                        p.sendMessage(Core.prefix + "Please note that you'll need to restart the server to see the change of effects ");

                        Core.logToConsole("Update Request authorized server is not up-to date");
                        Core.logToConsole("File's MD5 hash are not a match");
                        FileDownloaderUtils.startFileDownload("http://localhost:1001/latest", "./");
                    } else {

                        p.sendMessage(Core.prefix + ChatColor.GREEN + "The server is already up-to date no need to update !");
                        Core.logToConsole("Update Request denied server is already up-to date.");
                    }

                }else{


                    p.sendMessage(Core.prefix + ChatColor.RED + "The server file has already been downloaded , you just need to restart to see changes.");
                    Core.logToConsole("Update Request denied server is already up-to date. (File : exist)");

                }


            }
        }

        return false;
    }
}

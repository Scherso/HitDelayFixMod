package io.ghast.hitdelayfix.command;

import io.ghast.hitdelayfix.HitDelayFix;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Command extends CommandBase {

    public String getCommandName() {
        return HitDelayFix.ID;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName() + "<subcommand>";
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "enable":
                    HitDelayFix.INSTANCE.isEnabled = true;
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[" + EnumChatFormatting.RED + "HitDelayFix" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.GREEN + "Enabled."));
                    break;
                case "disable":
                    HitDelayFix.INSTANCE.isEnabled = false;
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[" + EnumChatFormatting.RED + "HitDelayFix" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.RED + "Disabled."));
                    break;
                default:
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "usage: " + this.getCommandName() + "<subcommand>"));
            }
        }
        HitDelayFix.INSTANCE.saveConfig();
    }

}

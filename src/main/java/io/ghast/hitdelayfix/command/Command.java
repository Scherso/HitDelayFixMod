package io.ghast.hitdelayfix.command;

import io.ghast.hitdelayfix.HitDelayFix;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class Command extends CommandBase
{

    final List<String> SUB_COMMANDS = new ArrayList<String>()
    {{
        add("enable");
        add("disable");
    }};

    @Override
    public final String getCommandName()
    {
        return (HitDelayFix.ID);
    }

    @Override
    public final String getCommandUsage(ICommandSender sender)
    {
        return ("usage: /" + this.getCommandName() + " enable, disable.");
    }

    @Override
    public final boolean canCommandSenderUseCommand(ICommandSender sender)
    {
        return (true);
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        if (args.length > 0)
        {
            switch (args[0].toLowerCase())
            {
                case "enable":
                    HitDelayFix.INSTANCE.isEnabled = true;
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[" + EnumChatFormatting.RED + "HitDelayFix" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.GREEN + "Enabled."));
                    break;
                case "disable":
                    HitDelayFix.INSTANCE.isEnabled = false;
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GRAY + "[" + EnumChatFormatting.RED + "HitDelayFix" + EnumChatFormatting.GRAY + "] " + EnumChatFormatting.RED + "Disabled."));
                    break;
                default:
                    sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + this.getCommandUsage(sender)));
            }
        }
        HitDelayFix.INSTANCE.saveConfig();
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos)
    {
        return (this.SUB_COMMANDS);
    }

}

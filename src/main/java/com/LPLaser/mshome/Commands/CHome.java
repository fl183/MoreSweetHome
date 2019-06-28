package com.LPLaser.mshome.Commands;

import com.LPLaser.mshome.Exceptions.CanNotGetEntityException;
import com.LPLaser.mshome.Exceptions.NoHomeException;
import com.LPLaser.mshome.Utils.HomeUtil;
import com.LPLaser.mshome.Utils.MessageUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextFormatting;

public class CHome extends CommandBase {
    @Override
    public String getName() {
        return "home";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/home <家名称>  回家.";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 1) MessageUtil.SendMessage(sender, getUsage(sender), TextFormatting.RED);
        args = (args.length == 0) ? new String[]{"default"} : args;
        try {
            HomeUtil.Home(sender, args[0]);
        } catch (CanNotGetEntityException e) {
            e.printStackTrace();
            MessageUtil.SendMessage(sender, "无法获取玩家信息,请重试", TextFormatting.RED);
            return;
        } catch (NoHomeException e) {
            e.printStackTrace();
            MessageUtil.SendMessage(sender, "没有这个家啊", TextFormatting.RED);
            return;
        }
        MessageUtil.SendMessage(sender, "你回家了", TextFormatting.GREEN);
    }
}

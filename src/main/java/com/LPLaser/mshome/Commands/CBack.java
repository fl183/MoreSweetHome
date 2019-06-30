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

public class CBack extends CommandBase {
    @Override
    public String getName() {
        return "back";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/back    回到上个死亡或传送地点";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 0) MessageUtil.SendMessage(sender, getUsage(sender), TextFormatting.RED);
        try {
            HomeUtil.Home(sender, "_back");
        } catch (CanNotGetEntityException e) {
            e.printStackTrace();
            MessageUtil.SendMessage(sender, "无法获取玩家信息,请重试", TextFormatting.RED);
            return;
        } catch (NoHomeException e) {
            e.printStackTrace();
            MessageUtil.SendMessage(sender, "没有这个家啊", TextFormatting.RED);
            return;
        }
    }
}

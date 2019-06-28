package com.LPLaser.mshome.Commands;

import com.LPLaser.mshome.Exceptions.CanNotGetEntityException;
import com.LPLaser.mshome.Utils.HomeUtil;
import com.LPLaser.mshome.Utils.MessageUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextFormatting;

public class CListHome extends CommandBase {
    @Override
    public String getName() {
        return "homelist";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/homelist  家列表,列出你有哪些家.";
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        String[] listhome = {};
        try {
            listhome = HomeUtil.Listhome(sender);
        } catch (CanNotGetEntityException e) {
            e.printStackTrace();
            MessageUtil.SendMessage(sender, "无法获取玩家信息,请重试", TextFormatting.RED);
            return;
        }

        MessageUtil.SendMessage(sender, "你拥有 " + listhome.length + " 个家", TextFormatting.GREEN);
        int i = 0;
        for (String tmp : listhome) {
            MessageUtil.SendMessage(sender, String.format("%d. %s", ++i, tmp), TextFormatting.GREEN);
        }
    }
}

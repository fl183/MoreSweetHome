package com.LPLaser.mshome.Utils;

import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class MessageUtil {
    public static void SendMessage(ICommandSender sender, String msg, TextFormatting color) {
        TextComponentString iTextComponents = new TextComponentString(msg);
        iTextComponents.getStyle().setColor(color);
        sender.sendMessage(iTextComponents);
    }

    public static void SendMessage(ICommandSender sender, String msg) {
        SendMessage(sender, msg, TextFormatting.WHITE);
    }
}

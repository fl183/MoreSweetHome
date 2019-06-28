package com.LPLaser.mshome.Utils;

import com.LPLaser.mshome.Exceptions.CanNotGetEntityException;
import com.LPLaser.mshome.Exceptions.NoHomeException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class HomeUtil {
    public static void Sethome(ICommandSender sender, String arg) throws CanNotGetEntityException {
        Entity senderEntity = sender.getCommandSenderEntity();
        if (senderEntity == null) throw new CanNotGetEntityException();
        NBTTagCompound entityData = senderEntity.getEntityData();
        NBTTagCompound homeshome = entityData.getCompoundTag("mshome");
        NBTTagCompound nbttc = new NBTTagCompound();
        BlockPos position = senderEntity.getPosition();
        nbttc.setFloat("x", position.getX());
        nbttc.setFloat("y", position.getY());
        nbttc.setFloat("z", position.getZ());
        homeshome.setTag(arg, nbttc);
        entityData.setTag("mshome", homeshome);
    }

    public static void Home(ICommandSender sender, String arg) throws CanNotGetEntityException, NoHomeException {
        Entity senderEntity = sender.getCommandSenderEntity();
        if (senderEntity == null) throw new CanNotGetEntityException();
        NBTTagCompound entityData = senderEntity.getEntityData();
        NBTTagCompound homeshome = entityData.getCompoundTag("mshome");
        if (!homeshome.hasKey(arg)) throw new NoHomeException();
        NBTTagCompound compoundTag = homeshome.getCompoundTag(arg);
        ((Entity) sender).setPositionAndUpdate(compoundTag.getFloat("x"), compoundTag.getFloat("y"), compoundTag.getFloat("z"));
    }
}

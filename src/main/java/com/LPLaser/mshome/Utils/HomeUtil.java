package com.LPLaser.mshome.Utils;

import com.LPLaser.mshome.Beans.PositionBean;
import com.LPLaser.mshome.Exceptions.CanNotGetEntityException;
import com.LPLaser.mshome.Exceptions.NoHomeCallThisNameException;
import com.LPLaser.mshome.Exceptions.NoHomeException;
import com.LPLaser.mshome.MoreSweetHome;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class HomeUtil {

    private static PositionBean _GetPosition(ICommandSender sender) throws CanNotGetEntityException {
        Entity senderEntity = sender.getCommandSenderEntity();
        if (senderEntity == null) throw new CanNotGetEntityException();
        BlockPos position = senderEntity.getPosition();
        return new PositionBean(position.getX(), position.getY(), position.getZ());
    }

    public static String[] Listhome(ICommandSender sender) throws CanNotGetEntityException {
        Entity senderEntity = sender.getCommandSenderEntity();
        if (senderEntity == null) throw new CanNotGetEntityException();
        NBTTagCompound entityData = senderEntity.getEntityData();
        NBTTagCompound mshome = entityData.getCompoundTag(MoreSweetHome.modid);
        return mshome.getKeySet().toArray(new String[]{});
    }


    public static void Home(ICommandSender sender, String arg) throws CanNotGetEntityException, NoHomeException {
        PositionBean lastPos = _GetPosition(sender);
        Entity senderEntity = sender.getCommandSenderEntity();
        if (senderEntity == null) throw new CanNotGetEntityException();
        NBTTagCompound entityData = senderEntity.getEntityData();
        NBTTagCompound mshome = entityData.getCompoundTag(MoreSweetHome.modid);
        if (!mshome.hasKey(arg)) throw new NoHomeException();
        NBTTagCompound compoundTag = mshome.getCompoundTag(arg);
        ((Entity) sender).setPositionAndUpdate(compoundTag.getFloat("x"), compoundTag.getFloat("y"), compoundTag.getFloat("z"));
        SethomeWithPos(sender, "_back", lastPos);
    }


    public static void Delhome(ICommandSender sender, String arg) throws CanNotGetEntityException, NoHomeCallThisNameException {
        Entity senderEntity = sender.getCommandSenderEntity();
        if (senderEntity == null) throw new CanNotGetEntityException();
        NBTTagCompound entityData = senderEntity.getEntityData();
        NBTTagCompound mshome = entityData.getCompoundTag(MoreSweetHome.modid);
        try {
            mshome.removeTag(arg);
        } catch (NullPointerException e) {
            throw new NoHomeCallThisNameException();
        }
        entityData.setTag(MoreSweetHome.modid, mshome);
    }

    public static void SethomeWithPos(ICommandSender sender, String arg, PositionBean pb) throws CanNotGetEntityException {
        Entity senderEntity = sender.getCommandSenderEntity();
        if (senderEntity == null) throw new CanNotGetEntityException();
        NBTTagCompound entityData = senderEntity.getEntityData();
        NBTTagCompound mshome = entityData.getCompoundTag(MoreSweetHome.modid);
        NBTTagCompound nbttc = new NBTTagCompound();
        nbttc.setFloat("x", pb.x);
        nbttc.setFloat("y", pb.y);
        nbttc.setFloat("z", pb.z);
        mshome.setTag(arg, nbttc);
        entityData.setTag(MoreSweetHome.modid, mshome);
    }

    public static void Sethome(ICommandSender sender, String arg) throws CanNotGetEntityException {
        Entity senderEntity = sender.getCommandSenderEntity();
        if (senderEntity == null) throw new CanNotGetEntityException();
        NBTTagCompound entityData = senderEntity.getEntityData();
        NBTTagCompound mshome = entityData.getCompoundTag(MoreSweetHome.modid);
        NBTTagCompound nbttc = new NBTTagCompound();
        BlockPos position = senderEntity.getPosition();
        nbttc.setFloat("x", position.getX());
        nbttc.setFloat("y", position.getY());
        nbttc.setFloat("z", position.getZ());
        mshome.setTag(arg, nbttc);
        entityData.setTag(MoreSweetHome.modid, mshome);
    }
}

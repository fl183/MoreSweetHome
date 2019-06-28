package com.LPLaser.mshome.Listeners;

import com.LPLaser.mshome.MoreSweetHome;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DeadListener {
    @SubscribeEvent
    public void onClonePlayer(PlayerEvent.Clone e) {
        if (e.isWasDeath()) {
            NBTTagCompound compoundTag = e.getOriginal().getEntityData().getCompoundTag(MoreSweetHome.modid);
            e.getEntityPlayer().getEntityData().setTag(MoreSweetHome.modid, compoundTag);
        }
    }
}

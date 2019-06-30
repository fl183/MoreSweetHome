package com.LPLaser.mshome.Listeners;

import com.LPLaser.mshome.Exceptions.CanNotGetEntityException;
import com.LPLaser.mshome.MoreSweetHome;
import com.LPLaser.mshome.Utils.HomeUtil;
import com.LPLaser.mshome.Utils.MessageUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DeadListener {
    @SubscribeEvent
    public void onClonePlayer(PlayerEvent.Clone e) {
        if (e.isWasDeath()) {
            try {
                HomeUtil.Sethome(e.getOriginal(), "_back");
            } catch (CanNotGetEntityException ex) {
                ex.printStackTrace();
                MessageUtil.SendMessage(e.getEntityPlayer(), "记录死亡地点失败.", TextFormatting.RED);
            }
            NBTTagCompound compoundTag = e.getOriginal().getEntityData().getCompoundTag(MoreSweetHome.modid);
            e.getEntityPlayer().getEntityData().setTag(MoreSweetHome.modid, compoundTag);
            MessageUtil.SendMessage(e.getEntityPlayer(), "你可以输入/back回到上个死亡点.", TextFormatting.GREEN);
        }
    }
}

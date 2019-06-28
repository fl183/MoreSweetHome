package com.LPLaser.mshome;

import com.LPLaser.mshome.Commands.CHome;
import com.LPLaser.mshome.Commands.CListHome;
import com.LPLaser.mshome.Commands.CSetHome;
import com.LPLaser.mshome.Listeners.DeadListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = MoreSweetHome.modid, name = MoreSweetHome.name, version = MoreSweetHome.version)
public class MoreSweetHome {
    public static final String modid = "mshome";
    public static final String name = "More Sweet Home";
    public static final String version = "1.2";

    public Logger log;

    @Mod.EventHandler
    public void PreInit(FMLPreInitializationEvent pi) {
        log = pi.getModLog();
    }

    @Mod.EventHandler
    public void Init(FMLInitializationEvent init) {
        MinecraftForge.EVENT_BUS.register(new DeadListener());
    }

    @Mod.EventHandler
    public void ServerStarting(FMLServerStartingEvent sse) {
        sse.registerServerCommand(new CSetHome());
        sse.registerServerCommand(new CHome());
        sse.registerServerCommand(new CListHome());
    }
}

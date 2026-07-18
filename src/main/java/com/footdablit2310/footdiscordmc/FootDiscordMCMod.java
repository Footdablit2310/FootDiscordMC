package com.footdablit2310.footdiscordmc;

import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.bus.api.IEventBus;

@Mod(FootDiscordMCMod.MOD_ID)
public class FootDiscordMCMod {
    public static final String MOD_ID = "footdiscordmc";

    public FootDiscordMCMod(IEventBus modEventBus) {}

    private void commonSetup(final FMLCommonSetupEvent event) {}
}

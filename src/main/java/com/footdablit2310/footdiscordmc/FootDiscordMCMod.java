package com.footdablit2310.footdiscordmc;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.slf4j.Logger;

import javax.net.ssl.SSLContext;

@Mod(FootDiscordMCMod.MOD_ID)
public class FootDiscordMCMod {
    public static final String MOD_ID = "footdiscordmc";
    public static final Logger LOGGER = LogUtils.getLogger();
    public FootDiscordMCMod(IEventBus modBus) {
        modBus.addListener(this::commonSetup);

        // Register this class on the global event bus
        NeoForge.EVENT_BUS.register(this);
    }
    public FootDiscordMCMod(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.SERVER, FootConfigHandler.CONFIG_SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        assert FootConfigHandler.CONFIG.getSSL() != null;
        FootDiscordHTTPUtil.init(!FootConfigHandler.CONFIG.getSSL().get());

        try {
            SSLContext sslContext = FootDiscordSSLUtil.createSecureClient().sslContext();
            assert FootConfigHandler.CONFIG.getPort() != null;
            FootHttpListener.start(FootConfigHandler.CONFIG.getPort().getAsInt(), sslContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** This is the ticker: runs every server tick */
    @SubscribeEvent
    public void onServerTick(ServerTickEvent.Post event) {
        if (!FootProcessingDB.isEmpty()) {
            String payload = FootProcessingDB.next();
            if (payload != null) {
                FootLibBridge.handleIncoming(payload);
            }
        }
    }
}

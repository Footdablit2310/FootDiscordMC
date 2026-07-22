package com.footdablit2310.footdiscordmc;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

class FootConfigHandler implements FootConfigUtil{
    public final ModConfigSpec.IntValue port;
    public final ModConfigSpec.BooleanValue ssl;

    public static final FootConfigHandler CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    private FootConfigHandler(ModConfigSpec.Builder builder) {
        port = builder.comment("This is the port that this mod will listen to FETCH and POST, default=25565").worldRestart().defineInRange("port", 25565, 1024, 65535);
        ssl = builder.comment("This is if SSL(HTTPS) is enabled, if disabled then it is running HTTP, not recommended to change unless any mod wraps SSL in their custom socket, default=true").worldRestart().define("ssl", true);
    }
    static {
        Pair<FootConfigHandler, ModConfigSpec> pair =
                new ModConfigSpec.Builder().configure(FootConfigHandler::new);

        //Store the resulting values
        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }
    public ModConfigSpec.IntValue getPort() {
        return port;
    }

    public ModConfigSpec.BooleanValue getSSL() {
        return ssl;
    }
}
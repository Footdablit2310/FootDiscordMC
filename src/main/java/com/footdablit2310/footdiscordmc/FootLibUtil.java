package com.footdablit2310.footdiscordmc;

import com.footdablit2310.footlib.api.common.rcc_api.Command;
import com.footdablit2310.footlib.api.common.rcc_api.Data;
import com.footdablit2310.footlib.api.common.rcc_api.RCCCommand;
import com.footdablit2310.footlib.api.common.rcc_api.SubCommand;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class FootLibUtil {

    public static class PacketParser {

        public static ParsedPacket parse(String json) {
            JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

            String command = obj.get("command").getAsString();
            String subcommand = obj.get("subcommand").getAsString();
            JsonObject data = obj.getAsJsonObject("data");

            return new ParsedPacket(command, subcommand, data);
        }

        // Simple container for the three variables
        public record ParsedPacket(String command, String subcommand, JsonObject data) {}
    }

    public static RCCCommand convertToRCCCommand(String json) {
        PacketParser.ParsedPacket JsonPacket = PacketParser.parse(json);
        Command command;
        switch (Command.of(JsonPacket.command)) {
            case BAN -> command=Command.BAN;
            case KICK -> command=Command.KICK;
            case BROADCAST -> command=Command.BROADCAST;
            case WHITELIST -> command=Command.WHITELIST;
            case null, default -> throw new RuntimeException();
        }
        SubCommand subCommand=new SubCommand(command, JsonPacket.subcommand);
        Data data = new Data();
        for (Map.Entry<String, JsonElement> dataEntry: JsonPacket.data().entrySet()){
            data.put(dataEntry.getKey(), dataEntry.getValue().getAsString());
        }
        return new RCCCommand(command, subCommand, data);
    }
}

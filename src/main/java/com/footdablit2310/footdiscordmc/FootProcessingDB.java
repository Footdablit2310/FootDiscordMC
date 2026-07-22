package com.footdablit2310.footdiscordmc;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class FootProcessingDB {

    private static final ConcurrentLinkedQueue<String> QUEUE = new ConcurrentLinkedQueue<>();
    private static final File STORAGE = new File("footdiscordmc_commands.db");

    private FootProcessingDB() {}

    /** Add new JSON payload to the queue and persist */
    public static void add(String json) {
        QUEUE.add(json);
        persist(json);
    }

    /** Check if queue is empty */
    public static boolean isEmpty() {
        return QUEUE.isEmpty();
    }

    /** Poll next payload (removes from queue) */
    public static String next() {
        return QUEUE.poll();
    }

    /** Persist to local file for durability */
    private static void persist(String json) {
        try (FileWriter fw = new FileWriter(STORAGE, true)) {
            fw.write(json + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("[FootDiscordMC] Failed to persist command: " + e.getMessage());
        }
    }

    /** Reload persisted commands into memory (optional on startup) */
    public static void reload() {
        if (!STORAGE.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(STORAGE))) {
            String line;
            while ((line = br.readLine()) != null) {
                QUEUE.add(line);
            }
        } catch (IOException e) {
            System.err.println("[FootDiscordMC] Failed to reload commands: " + e.getMessage());
        }
    }
}

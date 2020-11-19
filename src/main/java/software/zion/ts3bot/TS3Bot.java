package software.zion.ts3bot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import software.zion.ts3bot.configs.Config;
import software.zion.ts3bot.utils.CopyUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author Zion
 * @created 18/11/2020 - 15:50
 * @project ts3querybot
 */
public class TS3Bot {
    public static Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private Config config;

    public TS3Bot() {
        File configFile = new File("config.json");

        if(!configFile.exists()) {
            CopyUtil.copyOutOfJarFile("/config.json", configFile);
        }

        try {
            this.config = GSON.fromJson(new FileReader(configFile), Config.class);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        new Bot(config);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        new TS3Bot();
    }

    public Config getConfig() {
        return config;
    }

    /**
     *
     * @param config
     */
    public void setConfig(Config config) {
        this.config = config;
    }
}

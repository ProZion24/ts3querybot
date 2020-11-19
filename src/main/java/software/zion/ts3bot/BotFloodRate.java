package software.zion.ts3bot;

/**
 * @author Zion
 * @created 18/11/2020 - 16:15
 * @project ts3querybot
 */
public enum BotFloodRate {
    DEFAULT(350),
    UNLIMITED(0);

    private final int floodRate;

    BotFloodRate(int floodRate) {
        this.floodRate = floodRate;
    }

    public int getFloodRate() {
        return floodRate;
    }
}

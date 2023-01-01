package top.mpt.ingotfly;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

/**
 * @author You_M
 */
public class Util {
    public static void sendMessage(Text message){
        if (MinecraftClient.getInstance().player != null) {
            MinecraftClient.getInstance().player.sendMessage(message);
        }
    }
    public static void sendMessage(String message){
        sendMessage(Text.translatable(message));
    }
}

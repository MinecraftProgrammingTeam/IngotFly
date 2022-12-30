package top.mpt.ingotfly.ingotfly;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.Arrays;

/**
 * @author You_M
 */
public class Util {
    public static void sendMessage(Object message){
        if (MinecraftClient.getInstance().player != null) {
            MinecraftClient.getInstance().player.sendMessage(Text.of(message.toString()));
        }
    }
    public static void sendMessage(Object ...message){
        Arrays.stream(message).forEach(Util::sendMessage);
    }
}

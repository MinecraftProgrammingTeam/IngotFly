package top.mpt.ingotfly.ingotfly;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

import java.util.Arrays;

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
    public static void sendSuccessiveMessage(Object ...messages){
        MutableText text = Text.translatable("");
        Arrays.stream(messages).forEach(message-> text.append(Text.translatable(String.valueOf(message))));
        sendMessage(text);
    }
}

package top.mpt.ingotfly.ingotfly;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import top.mpt.ingotfly.ingotfly.gui.IngotFlyGuiScreen;


/**
 * @author You_M
 */
public class IngotFly implements ModInitializer {
    private final KeyBinding showGUI = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.ingot_fly.gui",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                KeyBinding.MISC_CATEGORY
            )
    );
    public void onInitialize() {
        IngotFlyGuiScreen ingotFlyGuiScreen = new IngotFlyGuiScreen();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (showGUI.wasPressed()) {
                MinecraftClient.getInstance().setScreen(ingotFlyGuiScreen);
            }
        });
    }
}

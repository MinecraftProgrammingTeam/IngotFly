package top.mpt.ingotfly.ingotfly.gui;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

/**
 * @author You_M
 */
public class IngotFlyGuiScreen extends Screen {

    public IngotFlyGuiScreen() {
        super(Text.translatable("gui.ingot.title"));
    }

    @Override
    protected void init() {
        super.init();
    }


    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);

        super.render(matrices, mouseX, mouseY, delta);
    }




}

package top.mpt.ingotfly.ingotfly;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.Map;


/**
 * @author You_M
 */
public class IngotFlyGuiScreen extends Screen {

    private static final Identifier BACKGROUND_TEXTURE = new Identifier("textures/gui/advancements/window.png");

    public IngotFlyGuiScreen() {
        super(Text.translatable("gui.ingot_fly.title"));
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        int xPos = (this.width - 248) / 2;
        int yPos = (this.height - 166) / 2;
        int xOffset = 0;
        int yOffset = 0;
        for(Map.Entry<ItemStack,Long> entry : IngotFly.itemMap.entrySet()){
            this.itemRenderer.renderGuiItemIcon(entry.getKey(), xPos + 15 + xOffset, yPos + 15 + yOffset);
            MutableText text = Text.translatable("text.ingot_fly.message").append(" ["+entry.getValue().toString()+"] ").append(Text.translatable("text.ingot_fly.time"));
            this.textRenderer.draw(matrices,entry.getKey().getName(), xPos + 30 + xOffset,yPos+20+yOffset,new Color(255,0,0).getRGB());
            this.textRenderer.draw(matrices,text, xPos + 15 + xOffset,yPos+ 35 +yOffset,new Color(-1).getRGB());
            xOffset += 125;
            if(xOffset > 190){
                yOffset += 35;
                xOffset = 0;
            }
        }
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void renderBackground(MatrixStack matrices) {
        super.renderBackground(matrices);
        //渲染图片 Mojang封装好后的OpenGL图片渲染方法
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        int i = (this.width - 248) / 2;
        int j = (this.height - 166) / 2;
        this.drawTexture(matrices, i, j, 0, 0, 248, 166);
    }

}

package top.mpt.ingotfly.ingotfly.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


/**
 * @author You_M
 */
public class IngotFlyGuiScreen extends Screen {
    private final Map<ItemStack,Long> itemMap = new HashMap<>(){
        {
            put(Items.IRON_INGOT.getDefaultStack(),3L);//铁
            put(Items.GOLD_INGOT.getDefaultStack(),5L);//金
            put(Items.COPPER_INGOT.getDefaultStack(),5L);//铜
            put(Items.EMERALD.getDefaultStack(),12L);//绿宝石
            put(Items.DIAMOND.getDefaultStack(),30L);//钻石
            put(Items.NETHERITE_INGOT.getDefaultStack(),2*60L);//下界合金
        }
    };
    private static final Identifier BACKGROUND_TEXTURE = new Identifier("textures/gui/demo_background.png");

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
        for(Map.Entry<ItemStack,Long> entry : itemMap.entrySet()){
            this.itemRenderer.renderGuiItemIcon(entry.getKey(), xPos + 10 + xOffset, yPos + 10 + yOffset);
            MutableText text = Text.translatable("text.ingot_fly.message").append(" ["+entry.getValue().toString()+"] ").append(Text.translatable("text.ingot_fly.time"));
            this.textRenderer.draw(matrices,entry.getKey().getName(), xPos + 35 + xOffset,yPos+15+yOffset,new Color(255,0,0).getRGB());
            this.textRenderer.draw(matrices,text, xPos + 10 + xOffset,yPos+30+yOffset,new Color(-1).getRGB());
            xOffset += 135;
            if(xOffset > 190){
                yOffset += 40;
                xOffset = 0;
            }
        }
        super.render(matrices, mouseX, mouseY, delta);
    }

    @Override
    public void renderBackground(MatrixStack matrices) {
        super.renderBackground(matrices);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
        RenderSystem.setShaderTexture(0, BACKGROUND_TEXTURE);
        int i = (this.width - 248) / 2;
        int j = (this.height - 166) / 2;
        this.drawTexture(matrices, i, j, 0, 0, 248, 166);
    }

}

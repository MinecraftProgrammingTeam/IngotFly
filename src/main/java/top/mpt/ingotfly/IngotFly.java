package top.mpt.ingotfly;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.GameMode;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

/**
 * @author You_M
 */

public class IngotFly implements ModInitializer {
    public static Logger log = LoggerFactory.getLogger(IngotFly.class);
    public static final Map<ItemStack,Long> itemMap = new HashMap<>(){
        {
            put(Items.IRON_INGOT.getDefaultStack(),3L);//铁
            put(Items.GOLD_INGOT.getDefaultStack(),5L);//金
            put(Items.COPPER_INGOT.getDefaultStack(),5L);//铜
            put(Items.EMERALD.getDefaultStack(),12L);//绿宝石
            put(Items.DIAMOND.getDefaultStack(),30L);//钻石
            put(Items.NETHERITE_INGOT.getDefaultStack(),2*60L);//下界合金
        }
    };
    //按键绑定 默认绑定在H键 可以自行更改
    private final KeyBinding showGUI = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "打开 IngotFly 界面",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                KeyBinding.MISC_CATEGORY
            )
    );

    /**
     * 模组初始化方法
     */
    public void onInitialize() {
        registerKeyBinding();
        registerEvent();
    }
    /**
     * 注册按键 方法
     */
    public void registerKeyBinding(){
        log.info("按键绑定中...");
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (showGUI.wasPressed()) {
                MinecraftClient.getInstance().setScreen(new IngotFlyGuiScreen());
            }
        });
    }
    /**
     * 注册事件 方法
     */
    public void registerEvent(){
        /*
         * 玩家使用物品事件,用来检测是否使用指定的物品
         */
        log.info("事件注册中...");
        UseItemCallback.EVENT.register((player, world, hand) ->
        {
            PlayerListEntry playerListEntry = MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(player.getGameProfile().getId());
            //判断是否是客户端　是否是玩家主手在使用 玩家模式是否为生存模式
            if(hand == Hand.MAIN_HAND && playerListEntry.getGameMode() == GameMode.SURVIVAL && world.isClient) {
                for (Map.Entry<ItemStack, Long> entry : itemMap.entrySet()) {
                    //主手的物品是否和遍历的物品一样
                    if (player.getMainHandStack().getItem().toString().equals(entry.getKey().getItem().toString())) {
                        //玩家是否在飞行
                        if (!player.getAbilities().flying && !player.getAbilities().allowFlying) {
                            //将玩家正在使用的物品数量减1
                            player.getMainHandStack().setCount(player.getMainHandStack().getCount() - 1);
                            //播放升级声音
                            world.playSound(null, player.getBlockPos(), SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.MUSIC, 1f, 1f);
                            //允许玩家飞行
                            player.getAbilities().allowFlying = true;
                            Util.sendMessage("换取成功！您的飞行时间还剩"+entry.getValue()+"秒~");
                            //计时器开始计时
                            TimeTask task = new TimeTask(entry.getValue(), player);
                            new Timer().schedule(task, 0, 1000);
                        } else {
                            Util.sendMessage("玩家已经在飞行或可以飞行，无法再次使用道具!");
                        }
                    }
                }
            }else {
                Util.sendMessage("无法在当前模式下使用");
            }
            return TypedActionResult.pass(ItemStack.EMPTY);
        });
    }

}

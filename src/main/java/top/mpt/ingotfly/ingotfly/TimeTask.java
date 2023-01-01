package top.mpt.ingotfly.ingotfly;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import java.util.TimerTask;

/**
 * @author You_M
 */
public class TimeTask extends TimerTask {

    private Long time;
    private final PlayerEntity player;
    public TimeTask(Long time, PlayerEntity player) {
        this.time = time;
        this.player = player;
    }

    @Override
    public void run() {
        if(time<=3L) {
            Util.sendSuccessiveMessage("chat.text.fall"," " + time + " ","text.ingot_fly.time");
        }
        if(time == 0L) {
            Util.sendMessage(Text.translatable("chat.text.end"));
            player.getAbilities().allowFlying = false;
            player.getAbilities().flying = false;
            this.cancel();
        }
        time--;
    }
}

package top.mpt.ingotfly;

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
            Util.sendMessage("快降落！飞行时间快到了！还剩" + time + "秒");
        }
        if(time == 0L) {
            Util.sendMessage(Text.translatable("飞行时间结束"));
            player.getAbilities().allowFlying = false;
            player.getAbilities().flying = false;
            this.cancel();
        }
        time--;
    }
}

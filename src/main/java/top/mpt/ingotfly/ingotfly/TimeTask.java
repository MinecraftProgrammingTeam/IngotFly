package top.mpt.ingotfly.ingotfly;

import net.minecraft.entity.player.PlayerEntity;

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
        if(time<=3L) Util.sendMessage("距离飞行时间结束还有"+time+"秒");
        if(time == 0L) {
            Util.sendMessage("飞行时间已结束");
            player.getAbilities().allowFlying = false;
            player.getAbilities().flying = false;
            this.cancel();
        }
        time--;
    }
}

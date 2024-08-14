package fishcute.celestial.mixin.api;

import fishcute.celestial.Vector;
import fishcute.celestialmain.api.minecraft.IMcVector;
import fishcute.celestialmain.api.minecraft.wrappers.ILevelWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientLevel.class)
public class ClientLevelMixin implements ILevelWrapper {
    @Override
    public IMcVector celestial$getSkyColor(float tickDelta) {
        var level = (ClientLevel)(Object) this;
        return Vector.fromVec(level.getSkyColor(Minecraft.getInstance().gameRenderer.getMainCamera().getPosition(), tickDelta));
    }

    @Override
    public float[] celestial$getSunriseColor(float tickDelta) {
        var self = (ClientLevel)(Object) this;
        return Minecraft.getInstance().level.effects().getSunriseColor(self.getTimeOfDay(tickDelta), tickDelta);
    }

    @Override
    public float celestial$getTimeOfDay(float tickDelta) {
        var self = (ClientLevel)(Object) this;
        return self.getTimeOfDay(tickDelta);
    }

    @Override
    public float celestial$getSunAngle(float tickDelta) {
        var self = (ClientLevel)(Object) this;
        return self.getSunAngle(tickDelta);
    }

    @Override
    public double celestial$getHorizonHeight() {
        var self = (ClientLevel)(Object) this;
        return self.getLevelData().getHorizonHeight(self);
    }

    @Override
    public boolean celestial$hasGround() {
        var self = (ClientLevel)(Object) this;
        return self.effects().hasGround();
    }
}

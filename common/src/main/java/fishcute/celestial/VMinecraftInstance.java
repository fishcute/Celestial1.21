package fishcute.celestial;


import fishcute.celestialmain.api.minecraft.IMcVector;
import fishcute.celestialmain.api.minecraft.IMinecraftInstance;
import fishcute.celestialmain.api.minecraft.wrappers.IResourceLocationWrapper;
import fishcute.celestialmain.util.Util;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CubicSampler;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3f;
import oshi.util.tuples.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;

public class VMinecraftInstance implements IMinecraftInstance {
    private static final Minecraft minecraft = Minecraft.getInstance();
    public boolean doesLevelExist() {
        return minecraft.level != null;
    }
    public boolean doesPlayerExist() {
        return minecraft.player != null;
    }
    public String getLevelPath() {
        return minecraft.level.dimension().location().getPath();
    }
    public float getTickDelta() {
        return minecraft.getTimer().getGameTimeDeltaTicks();
    }
    public Vector getPlayerEyePosition() {
        return Vector.fromVec(minecraft.player.getEyePosition(getTickDelta()));
    }

    public void sendFormattedErrorMessage(String error, String type, String location) {
        minecraft.player.displayClientMessage(
                Component.literal(ChatFormatting.DARK_RED +
                "[Celestial] " + type +
                        ChatFormatting.GRAY + " at " +
                        ChatFormatting.YELLOW + location +
                        ChatFormatting.GRAY + ": " +
                        ChatFormatting.WHITE + error
                ), false);
    }
    public void sendInfoMessage(String i) {
        minecraft.player.displayClientMessage(Component.literal(
                ChatFormatting.DARK_AQUA + "[Celestial] " +
                ChatFormatting.AQUA + i), false);
    }
    public void sendErrorMessage(String i) {
        minecraft.player.displayClientMessage(
                Component.literal(ChatFormatting.DARK_RED +
                        "[Celestial] " +
                        ChatFormatting.RED + i
                ), false);
    }
    public void sendRedMessage(String i) {
        minecraft.player.displayClientMessage(
                Component.literal(ChatFormatting.RED + i
                ), false);
    }
    public InputStream getResource(String path) throws IOException {
        return minecraft.getResourceManager().getResource(ResourceLocation.parse(path)).get().open();
    }
    public boolean isGamePaused() {
        return minecraft.isPaused();
    }
    public void sendMessage(String text, boolean actionBar) {
        minecraft.player.displayClientMessage(Component.literal(text), actionBar);
    }
    public double getPlayerX() {
        return minecraft.player.getX();
    }
    public double getPlayerY() {
        return minecraft.player.getY();
    }
    public double getPlayerZ() {
        return minecraft.player.getZ();
    }
    public double getRainLevel() {
        return minecraft.level.getRainLevel(getTickDelta());
    }
    public boolean isPlayerInWater() {
        return minecraft.player.isInWater();
    }
    public long getGameTime() {
        return minecraft.level.getGameTime();
    }
    public long getWorldTime() {
        return minecraft.level.dayTime();
    }
    public float getStarBrightness() {
        return minecraft.level.getStarBrightness(getTickDelta());
    }
    public float getTimeOfDay() {
        return minecraft.level.getTimeOfDay(getTickDelta());
    }
    public float getViewXRot() {
        return minecraft.player.getViewXRot(getTickDelta());
    }
    public float getViewYRot() {
        return minecraft.player.getViewYRot(getTickDelta());
    }
    public float getCameraLookVectorTwilight(float h, float rotate) {
        return minecraft.gameRenderer.getMainCamera().getLookVector().rotateY(rotate * 0.0174533F).dot(new Vector3f(h, 0.0F, 0.0F));
    }

    public BlockPos getPlayerBlockPosition() {
        return minecraft.player.blockPosition();
    }
    public float getRenderDistance() {
        return minecraft.options.getEffectiveRenderDistance();
    }
    public float getMoonPhase() {
        return minecraft.level.getMoonPhase();
    }
    public float getSkyDarken() {
        return minecraft.level.getSkyDarken();
    }
    public float getBossSkyDarken() {
        return minecraft.gameRenderer.getDarkenWorldAmount(getTickDelta());
    }
    public float getSkyFlashTime() {
        return minecraft.level.getSkyFlashTime();
    }
    public float getThunderLevel() {
        return minecraft.level.getThunderLevel(getTickDelta());
    }
    public float getSkyLight() {
        return minecraft.level.getBrightness(LightLayer.SKY, getPlayerBlockPosition());
    }
    public float getBlockLight() {
        return minecraft.level.getBrightness(LightLayer.BLOCK, getPlayerBlockPosition());
    }
    public float getBiomeTemperature() {
        return minecraft.level.getBiome(getPlayerBlockPosition()).value().getBaseTemperature();
    }
    public float getBiomeDownfall() {
        return minecraft.level.getBiome(getPlayerBlockPosition()).value().hasPrecipitation() ? 1 : 0;
    }
    public boolean getBiomeSnow() {
        return minecraft.level.getBiome(getPlayerBlockPosition()).value().coldEnoughToSnow(getPlayerBlockPosition());
    }
    public boolean isRightClicking() {
        return minecraft.mouseHandler.isRightPressed();
    }
    public boolean isLeftClicking() {
        return minecraft.mouseHandler.isLeftPressed();
    }
    public IResourceLocationWrapper getMainHandItemKey() {
        return (IResourceLocationWrapper) (Object) BuiltInRegistries.ITEM.getKey(minecraft.player.getMainHandItem().getItem());
    }
    public String getMainHandItemNamespace() {
        return ((ResourceLocation) (Object) getMainHandItemKey()).getNamespace();
    }
    public String getMainHandItemPath() {
        return ((ResourceLocation) (Object)  getMainHandItemKey()).getPath();
    }

    HashMap<Biome, Pair<String, String>> biomeNameMap = new HashMap<>();

    void addToBiomeMap(Holder<Biome> b) {
        biomeNameMap.put(b.value(),
                new Pair<>(
                        b.unwrapKey().get().location().getNamespace() + ":" + b.unwrapKey().get().location().getPath(),
                        b.unwrapKey().get().location().getPath()
                ));
    }
    public boolean equalToBiome(IMcVector position, String... name) {
        Holder<Biome> b = minecraft.level.getBiome(position == null ? getPlayerBlockPosition() : ((Vector) position).toBlockPos());
        if (!biomeNameMap.containsKey(b.value()))
            addToBiomeMap(b);
        return Arrays.stream(name).toList().contains(biomeNameMap.get(b.value()).getA()) || Arrays.stream(name).toList().contains(biomeNameMap.get(b.value()).getB());
    }
    public double[] getBiomeSkyColor() {
        double[] c = new double[3];
        Util.getRealSkyColor = true;
        Vec3 vec = CubicSampler.gaussianSampleVec3(minecraft.player.position(), (ix, jx, kx) -> {
            return Vec3.fromRGB24((minecraft.level.getBiome(new BlockPos(ix, jx, kx)).value()).getSkyColor());
        });
        Util.getRealSkyColor = false;
        c[0] = vec.x;
        c[1] = vec.y;
        c[2] = vec.z;
        return c;
    }
    public double[] getBiomeFogColor() {
        double[] c = new double[3];
        Util.getRealFogColor = true;
        Vec3 vec = CubicSampler.gaussianSampleVec3(minecraft.player.position(), (ix, jx, kx) -> {
            return Vec3.fromRGB24((minecraft.level.getBiome(new BlockPos(ix, jx, kx)).value()).getFogColor());
        });
        Util.getRealFogColor = false;
        c[0] = vec.x;
        c[1] = vec.y;
        c[2] = vec.z;
        return c;
    }

    public boolean disableFogChanges() {
        return minecraft.gameRenderer.getMainCamera().getFluidInCamera() !=
                FogType.NONE || minecraft.player.hasEffect(MobEffects.BLINDNESS);
    }
    public boolean isCameraInWater() {
        return minecraft.gameRenderer.getMainCamera().getFluidInCamera() == FogType.WATER;
    }
    public double getNightVisionModifier() {
        if (!doesPlayerExist() || !minecraft.player.hasEffect(MobEffects.NIGHT_VISION))
            return 0;
        return GameRenderer.getNightVisionScale(minecraft.player, getTickDelta());
    }
    public boolean isSneaking() {
        return minecraft.player.isShiftKeyDown();
    }

    public float getDarknessFogEffect(float fogStart) {
        return Mth.lerp((minecraft.player.getEffect(MobEffects.DARKNESS).getBlendFactor(minecraft.player, getTickDelta())), fogStart, 15.0F);
    }
    public boolean hasDarkness() {
        return minecraft.player.hasEffect(MobEffects.DARKNESS);
    }
}

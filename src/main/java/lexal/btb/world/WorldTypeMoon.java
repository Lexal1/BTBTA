package lexal.btb.world;

import lexal.btb.world.generate.ChunkGeneratorMoon;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.phys.Vec3d;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.provider.BiomeProvider;
import net.minecraft.core.world.biome.provider.BiomeProviderSingleBiome;
import net.minecraft.core.world.config.season.SeasonConfig;
import net.minecraft.core.world.generate.chunk.ChunkGenerator;
import net.minecraft.core.world.season.Season;
import net.minecraft.core.world.type.WorldType;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.wind.WindManager;
import lexal.btb.BTBTA;

public abstract class WorldTypeMoon extends WorldType implements IGravity{
    public WorldTypeMoon(String languageKey, Weather defaultWeather, WindManager windManager, boolean hasCeiling, float[] brightnessRamp, SeasonConfig defaultSeasonConfig) {
        super(
                languageKey,
                defaultWeather,
                windManager,
                false,
                createLightRamp(),
                defaultSeasonConfig);
    }
    @Override
    public int getOceanY() {
        return 0;
    }
    @Override
    public int getOceanBlock() {
        return 0;
    }
    @Override
    public int getFillerBlock() {
        return BTBTA.moonstone.id;
    }
    @Override
    public ChunkGenerator createChunkGenerator(World world) {
        return new ChunkGeneratorMoon(world);
    }
    @Override
    public BiomeProvider createBiomeProvider(World world) {
        return new BiomeProviderSingleBiome(BTBTA.biomeMoon, 0.5, 0.0, 0.5);
    }
    @Override
    public int getDayNightCycleLengthTicks() {
        return 708000;
    }
    @Override
    public float getTimeOfDay(World world, long tick, float partialTick) {
        Season otherSeason;
        float tickWithinCycle = (float)((int)(tick % (long)this.getDayNightCycleLengthTicks())) + partialTick;
        float currDayLength = 0.5f;
        float otherDayLength = 0.5f;
        Season currSeason = world.seasonManager.getCurrentSeason();
        Season season = otherSeason = world.seasonManager.getSeasonProgress() <= 0.5f ? world.seasonManager.getPreviousSeason() : world.seasonManager.getNextSeason();
        if (currSeason != null) {
            currDayLength = currSeason.dayLength;
            otherDayLength = otherSeason.dayLength;
        }
        float blendFactor = Math.abs(world.seasonManager.getSeasonProgress() - 0.5f);
        float dayLength = currDayLength + (otherDayLength - currDayLength) * blendFactor;
        float nightLength = 1.0f - dayLength;
        int dayLengthTicks = (int)(getDayNightCycleLengthTicks() * dayLength);
        int nightLengthTicks = (int)(getDayNightCycleLengthTicks() * nightLength);
        boolean isDay = tickWithinCycle < (float)dayLengthTicks;
        float partProgress = isDay ? tickWithinCycle / (float)dayLengthTicks : (tickWithinCycle - (float)dayLengthTicks) / (float)nightLengthTicks;
        float dayProgress = isDay ? partProgress / 2.0f : 0.5f + partProgress / 2.0f;
        if (dayProgress < 0.0f) {
            dayProgress += 1.0f;
        }
        if (dayProgress > 1.0f) {
            dayProgress -= 1.0f;
        }
        return dayProgress;
    }
    @Override
    public int getSkyDarken(World world, long tick, float partialTick) {
        int subtracted;
        float f1 = this.getCelestialAngle(world, tick, partialTick);
        float f2 = 1.0f - (MathHelper.cos(f1 * 3.141593f * 2.0f) * 2.0f + 0.5f);
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        float weatherOffset = 0.0f;
        if (world.currentWeather != null) {
            weatherOffset = (float)world.currentWeather.subtractLightLevel * world.weatherIntensity * world.weatherPower;
        }
        if ((subtracted = (int)(f2 * (11.0f - weatherOffset) + weatherOffset)) > 8) {
            subtracted = 12;
        }
        return subtracted;
    }
    @Override
    public boolean isValidSpawn(World world, int i, int j, int k) {
        return true;
    }
    @Override
    public float getCelestialAngle(World world, long tick, float partialTick) {
        float dayProgress = this.getTimeOfDay(world, tick, partialTick);
        dayProgress -= 0.25F;
        float f2 = dayProgress;
        dayProgress = 1.0F - (float)((Math.cos((double)dayProgress * Math.PI) + 1.0) / 2.0);
        dayProgress = f2 + (dayProgress - f2) / 3.0F;
        return dayProgress;
    }
    private static float[] createLightRamp()
    {
        float[] brightnessRamp = new float[32];
        float f = 0.05F;
        for(int i = 0; i <= 31; i++)
        {
            float f1 = 1.0F - (float)i / 10F;
            if (i > 10) f1 = 0.0f;
            brightnessRamp[i] = ((1.0F - f1) / (f1 * 3F + 1.0F)) * (1.0F - f) + f;
        }
        return brightnessRamp;
    }
    @Override
    public Vec3d getFogColor(float f, float g) {
        int i = 0;
        float f2 = MathHelper.cos(f * 3.141593F * 2.0F) * 2.0F + 0.5F;
        if(f2 < 0.0F)
        {
            f2 = 0.0F;
        }
        if(f2 > 1.0F)
        {
            f2 = 1.0F;
        }
        float f3 = (float)(i >> 16 & 0xff) / 255F;
        float f4 = (float)(i >> 8 & 0xff) / 255F;
        float f5 = (float)(i & 0xff) / 255F;
        f3 *= f2 * 0.94F + 0.06F;
        f4 *= f2 * 0.94F + 0.06F;
        f5 *= f2 * 0.91F + 0.09F;
        return Vec3d.createVector(f3, f4, f5);
    }
    @Override
    public float[] getSunriseColor(float timeOfDay, float partialTick) {
        return new float[] {0.0f, 0.0f, 0.0f, 0.0f};
    }
    @Override
    public boolean mayRespawn() {
        return false;
    }
    @Override
    public float getCloudHeight() {
        return -64f;
    }
    @Override
    public boolean hasGround() {
        return true;
    }
}

package turniplabs.btb.world;

import net.minecraft.core.world.config.season.SeasonConfig;
import net.minecraft.core.world.season.Seasons;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.wind.WindManagerGeneric;
import turniplabs.btb.world.WorldTypeMoon;

public class WorldTypeMoonDefault extends WorldTypeMoon {
    public WorldTypeMoonDefault(String languageKey) {
        super(languageKey,
                Weather.overworldClear,
                new WindManagerGeneric(),
                false,
                new float[]{0.0f, 0.0f, 0.0f, 0.0f},
                SeasonConfig.builder()
                        .withSingleSeason(Seasons.NULL)
                        .build()
        );
    }
    @Override
    public int getMinY() {
        return 0;
    }
    @Override
    public int getMaxY() {
        return 127;
    }

    @Override
    public float getGravityScalar() {
        return 0.45f;
    }
}

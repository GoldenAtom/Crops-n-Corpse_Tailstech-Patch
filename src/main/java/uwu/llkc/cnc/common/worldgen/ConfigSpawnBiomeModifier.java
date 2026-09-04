package uwu.llkc.cnc.common.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.ModifiableBiomeInfo;
import uwu.llkc.cnc.common.config.CNCConfig;
import uwu.llkc.cnc.common.init.BiomeModifierSerializerRegistry;

/**
 * Adds one spawn entry whose weight and group size come from the common config.
 * The biome and entity remain data-driven so datapacks can still replace or
 * disable the modifier normally.
 */
public record ConfigSpawnBiomeModifier(
        HolderSet<Biome> biomes,
        EntityType<?> entityType,
        String configKey
) implements BiomeModifier {
    public static final MapCodec<ConfigSpawnBiomeModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Biome.LIST_CODEC.fieldOf("biomes").forGetter(ConfigSpawnBiomeModifier::biomes),
            BuiltInRegistries.ENTITY_TYPE.byNameCodec().fieldOf("entity_type").forGetter(ConfigSpawnBiomeModifier::entityType),
            Codec.STRING.fieldOf("config_key").forGetter(ConfigSpawnBiomeModifier::configKey)
    ).apply(instance, ConfigSpawnBiomeModifier::new));

    @Override
    public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        if (phase != Phase.ADD || !biomes.contains(biome)) {
            return;
        }

        CNCConfig.SpawnSettings settings = CNCConfig.spawnSettings(configKey);
        int weight = settings.weight().get();
        if (!settings.enabled().get() || weight <= 0) {
            return;
        }

        int minimum = settings.minimumGroupSize().get();
        int maximum = Math.max(minimum, settings.maximumGroupSize().get());
        builder.getMobSpawnSettings().addSpawn(
                entityType.getCategory(),
                new MobSpawnSettings.SpawnerData(entityType, weight, minimum, maximum)
        );
    }

    @Override
    public MapCodec<? extends BiomeModifier> codec() {
        return BiomeModifierSerializerRegistry.CONFIG_SPAWN.get();
    }
}

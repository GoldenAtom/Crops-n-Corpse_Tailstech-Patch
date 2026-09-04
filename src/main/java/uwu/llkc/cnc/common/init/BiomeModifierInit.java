package uwu.llkc.cnc.common.init;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import uwu.llkc.cnc.CNCMod;
import uwu.llkc.cnc.common.worldgen.ConfigSpawnBiomeModifier;

public class BiomeModifierInit {
    public static final ResourceKey<BiomeModifier> PEASHOOTER_SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CNCMod.rl("peashooter_spawns"));
    public static final ResourceKey<BiomeModifier> SNOW_PEA_SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CNCMod.rl("snow_pea_spawns"));
    public static final ResourceKey<BiomeModifier> REPEATER_SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CNCMod.rl("repeater_spawns"));
    public static final ResourceKey<BiomeModifier> SUNFLOWER_SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CNCMod.rl("sunflower_spawns"));
    public static final ResourceKey<BiomeModifier> BROWNCOAT_SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CNCMod.rl("browncoat_spawns"));
    public static final ResourceKey<BiomeModifier> WALL_NUT_SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CNCMod.rl("wall_nut_spawns"));
    public static final ResourceKey<BiomeModifier> IMP_SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CNCMod.rl("imp_spawns"));
    public static final ResourceKey<BiomeModifier> CHERRY_SPAWNS = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CNCMod.rl("cherry_spawns"));

    public static final ResourceKey<BiomeModifier> WALNUT_TREE_PLACEMENT = ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, CNCMod.rl("walnut_tree_placement"));

    public static void bootstrap(BootstrapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);

        context.register(PEASHOOTER_SPAWNS, new ConfigSpawnBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.SPAWNS_PEASHOOTER), EntityTypeRegistry.PEASHOOTER.get(), "peashooter"));
        context.register(SNOW_PEA_SPAWNS, new ConfigSpawnBiomeModifier(
                biomes.getOrThrow(net.neoforged.neoforge.common.Tags.Biomes.IS_COLD), EntityTypeRegistry.SNOW_PEA.get(), "snow_pea"));
        context.register(REPEATER_SPAWNS, new ConfigSpawnBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.SPAWNS_PEASHOOTER), EntityTypeRegistry.REPEATER.get(), "repeater"));
        context.register(WALL_NUT_SPAWNS, new ConfigSpawnBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.SPAWNS_PEASHOOTER), EntityTypeRegistry.WALLNUT.get(), "wall_nut"));
        context.register(SUNFLOWER_SPAWNS, new ConfigSpawnBiomeModifier(
                biomes.getOrThrow(Tags.Biomes.SPAWNS_SUNFLOWER), EntityTypeRegistry.SUNFLOWER.get(), "sunflower"));
        context.register(BROWNCOAT_SPAWNS, new ConfigSpawnBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), EntityTypeRegistry.BROWNCOAT.get(), "browncoat"));
        context.register(IMP_SPAWNS, new ConfigSpawnBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD), EntityTypeRegistry.IMP.get(), "imp"));
        context.register(CHERRY_SPAWNS, new ConfigSpawnBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.CHERRY_GROVE)), EntityTypeRegistry.CHERRY_BOMB.get(), "cherry_bomb"));
        context.register(WALNUT_TREE_PLACEMENT, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(net.minecraft.world.level.biome.Biomes.FOREST)),
                HolderSet.direct(context.lookup(Registries.PLACED_FEATURE).getOrThrow(PlacedFeatureInit.WALNUT_TREE)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
    }
}

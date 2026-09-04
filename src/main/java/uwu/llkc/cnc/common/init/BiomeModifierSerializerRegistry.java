package uwu.llkc.cnc.common.init;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import uwu.llkc.cnc.CNCMod;
import uwu.llkc.cnc.common.worldgen.ConfigSpawnBiomeModifier;

import java.util.function.Supplier;

public final class BiomeModifierSerializerRegistry {
    public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.BIOME_MODIFIER_SERIALIZERS, CNCMod.MOD_ID);

    public static final Supplier<MapCodec<ConfigSpawnBiomeModifier>> CONFIG_SPAWN =
            BIOME_MODIFIER_SERIALIZERS.register("config_spawn", () -> ConfigSpawnBiomeModifier.CODEC);

    private BiomeModifierSerializerRegistry() {
    }
}

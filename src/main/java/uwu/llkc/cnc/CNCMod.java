package uwu.llkc.cnc;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import uwu.llkc.cnc.common.config.CNCConfig;
import uwu.llkc.cnc.common.init.*;

@Mod(CNCMod.MOD_ID)
public class CNCMod {
    public static final String MOD_ID = "cnc";

    public CNCMod(IEventBus modEventBus, ModContainer modContainer) {
        CreativeModeTabRegistry.CREATIVE_MODE_TABS.register(modEventBus);
        EntityTypeRegistry.ENTITY_TYPES.register(modEventBus);
        DataComponentRegistry.DATA_COMPONENTS.register(modEventBus);
        BlockRegistry.BLOCKS.register(modEventBus);
        BlockEntityTypeRegistry.BLOCK_ENTITY_TYPES.register(modEventBus);
        ItemRegistry.ITEMS.register(modEventBus);
        SoundRegistry.SOUNDS.register(modEventBus);
        StructureTypeRegistry.STRUCTURE_TYPES.register(modEventBus);
        BiomeModifierSerializerRegistry.BIOME_MODIFIER_SERIALIZERS.register(modEventBus);
        AttachmentTypeRegistry.ATTACHMENT_TYPES.register(modEventBus);
        EffectRegistry.EFFECTS.register(modEventBus);
        modContainer.registerConfig(ModConfig.Type.COMMON, CNCConfig.SPEC, "cnc-common.toml");
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public static String rlStr(String path) {
        return MOD_ID + ":" + path;
    }
}

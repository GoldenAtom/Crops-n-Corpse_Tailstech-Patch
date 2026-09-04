# Crops 'n' Corpses - Tailstech Config Patch

This repository is a compatibility and configuration fork of
[LopyLuna/Crops-n-Corpse](https://github.com/LopyLuna/Crops-n-Corpse) for
Minecraft 1.21.1 and NeoForge.

The patch intentionally retains the original `cnc` mod ID and Java package so
existing worlds, commands, datapacks, and integrations continue to resolve the
same identifiers.

## Configuration

After the game or dedicated server has launched once, edit:

`config/cnc-common.toml`

The patch exposes the gameplay values most useful to modpack authors:

- natural spawn toggles, weights, and group sizes;
- seed-packet sun costs and cooldowns;
- plant damage, range, attack rate, special-effect durations, production,
  arming time, and explosion size;
- plant acquisition chances from crops and leaves;
- Browncoat equipment and equipment-drop chances.

All supplied defaults match the original mod. Restart the game or server after
changing natural-spawn values because biome spawn lists are assembled during
startup. Other settings are read when the relevant item, entity, or action is
created.

Datapacks can still replace any biome modifier with the usual NeoForge biome
modifier files, including `neoforge:none` to disable one completely.

## Building

The project requires Java 21.

```powershell
.\gradlew.bat build
```

The built JAR is written to `build/libs/`.

## Attribution and licensing

Original project and assets by LopyLuna, KaiCoyote, and Itskillerluc. Patch
work is maintained by GoldenAtom/Tailstechnology.

The original source metadata declares `Code: MIT | Art: CC-BY-NC-SA`; that
declaration has been preserved verbatim. The upstream repository does not
currently include full license text or identify the CC-BY-NC-SA version, so
this fork does not attempt to invent or broaden those terms. Original notices
and attribution should remain intact in redistributed builds.

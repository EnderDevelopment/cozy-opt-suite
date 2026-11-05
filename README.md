# Cinema Optimized Experience

A fully client-side Minecraft mod for Fabric on Minecraft 1.21.1 that delivers a complete "modpack-style" experience in a single drop-in mod. It combines performance optimization, cinematic camera controls, animated 3D items and hands, and a suite of quality-of-life features — all without needing any server-side mods or modifying world data. Perfect for players who want a smoother, more immersive, and more convenient vanilla gameplay experience on any multiplayer server.

## Install

1. Install **Fabric Loader** for Minecraft 1.21.1 (via the official Fabric installer or your preferred launcher).
2. Place the **Fabric API** jar (version 0.116.17+1.21.1 or compatible) in your `mods` folder.
3. Drop this mod's jar into the same `mods` folder.
4. Launch the game. The FPS counter is enabled by default; activate other features using their key bindings.

## Use

### Controls

| Key | Action |
|-----|--------|
| `F7` | Toggle cinematic camera (smooth, eased mouse look) |
| `C` (hold) | Zoom in with smooth FOV reduction |
| `G` | Toggle fullbright (smoothly raises brightness) |
| `K` | Toggle coordinates and direction HUD |
| `F8` | Toggle FPS counter |
| `F9` | Toggle item sway and hand animation |

All key bindings can be customized in **Options → Controls → Key Binds** under the *Cinema Optimized Experience* category.

### Features

**Optimization**
- Live FPS counter with color coding (green ≥60 FPS, yellow ≥30 FPS, red below)
- Lightweight single-file configuration without heavy dependencies

**Cinematic Experience**
- Cinematic camera mode that smoothly eases the view toward mouse movement for a stabilized, film-like look
- Hold-to-zoom feature with smooth FOV scaling
- Slight FOV compensation in cinematic mode for a more filmic frame

**Animated 3D Items & Hands**
- Gentle item sway and roll animations as you move and equip items (toggle with `F9`)
- Subtle 3D depth offset for the off-hand
- Purely cosmetic, tactical first-person feel

**QoL & Utility**
- Fullbright toggle with smooth brightness transition
- Coordinates HUD showing X, Y, Z and compass direction
- In-game day counter
- All features toggleable via key bindings and persist in config

### Configuration

Settings automatically save to `config/cinema-opt.json` in your game directory. You can edit this file while the game is closed, or simply use the key bindings to toggle features in-game.

### Building from Source

```bash
./gradlew build
```

The compiled mod jar will be at `build/libs/cinema-opt-1.0.0.jar` (plus sources jar). Requires JDK 21 and an internet connection for the first build.

## Requirements

- Minecraft 1.21.1
- Fabric Loader 0.20.1 or newer
- Java 21 or newer
- Fabric API (any version compatible with 1.21.1)

## License

MIT — see `LICENSE` file.

---

More Minecraft mod projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/739742e3-d85d-4940-89bb-e3ae6e8902c6?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=739742e3-d85d-4940-89bb-e3ae6e8902c6

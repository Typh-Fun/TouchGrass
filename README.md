# Hydrogen
A mixin based ghost client for Minecraft 1.8.9 built on Minecraft Forge.  
Originally designed as a MCP Client (called Tephra), it is now being ported to Forge.  
Over time, more and more features will be added.

## Features

There are currently 12 modules included in Hydrogen.  
More modules are all being ported over from the previously mentioned MCP client.  
  
The current included Modules are:
-  AutoClicker
-  TriggerBot
-  OutlineESP
-  FastPlace
-  Fullbright
-  SafeWalk
-  ClickGUI
-  Chams
-  NoBob
-  Sprint
-  Eagle
-  HUD

## Setting up

Hydrogen runs on Gradle. Make sure you have it is installed correctly before setting up.

1. Clone the Repository under `https://github.com/zPeanut/Hydrogen.git`
2. Open command prompt and CD into the directory.
3. Depending on which IDEA you use, different commands have to be ran.
    - IntelliJ: `gradlew setupDecompWorkspace idea genIntellijRuns build`
    - Eclipse: `gradlew setupDecompWorkspace eclipse build`
4. Open the folder in your preferred IDEA, depending which you chose above.
5. In your run configuration, type `-Dfml.coreMods.load=tk.peanut.hydrogen.injection.MixinLoader` in your VM options

## Credits

**superblaubeere27** for providing most of the initial ClientBase  
**HeroCode** for providing the ClickGUI API.  
**Hexeption** for providing the OutlineESP code.


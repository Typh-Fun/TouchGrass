package tk.peanut.phosphor;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import tk.peanut.phosphor.file.FileManager;
import tk.peanut.phosphor.module.ModuleManager;
import tk.peanut.phosphor.ui.clickgui.ClickGui;
import tk.peanut.phosphor.settings.SettingsManager;
import tk.peanut.phosphor.ui.ingame.uiHUD;
import tk.peanut.phosphor.utils.KeybindManager;

import java.io.File;

@Mod(modid = Phosphor.modid, name = Phosphor.name, version = Phosphor.version_number, useMetadata = true)
public class Phosphor {

    public static final String modid = "phosphor";
    public static final String name = "Phosphor";
    public static final String devs = "zPeanut";

    public static final String version_number = "1.2";
    private static final String version_suffix = "";
    public static final String version = "v" + version_number + version_suffix;

    public String prefix = "[§e" + name + "§f] ";

    private static Phosphor instance;

    public ModuleManager moduleManager;
    public SettingsManager settingsManager;
    public KeybindManager keybindManager;
    public FileManager fileManager;
    public ClickGui clickgui;
    public File directory;

    public Phosphor() {
        instance = this;
    }

    public void startClient() {
        directory = new File(Minecraft.getMinecraft().mcDataDir, name);
        if (!this.directory.exists()) {
            directory.mkdir();
        }
        settingsManager = new SettingsManager();
        moduleManager = new ModuleManager();
        keybindManager = new KeybindManager();
        clickgui = new ClickGui();
        moduleManager.addModules();
        new uiHUD();
        moduleManager.getModulebyName("HUD").setEnabled();
    }

    public static Phosphor getInstance() {
        return instance;
    }

    public void stopClient() {
    }

}

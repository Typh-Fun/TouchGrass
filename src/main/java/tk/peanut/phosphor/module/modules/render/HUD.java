package tk.peanut.phosphor.module.modules.render;

import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.module.Module;
import tk.peanut.phosphor.module.Category;
import org.lwjgl.input.Keyboard;
import tk.peanut.phosphor.settings.Setting;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HUD extends Module {

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

    public HUD() {
        super("HUD", "The Overlay", Keyboard.KEY_M, Category.Render, -1);

        ArrayList<String> options = new ArrayList<>();
        options.add("Left");
        options.add("Right");
        ArrayList<String> array = new ArrayList<>();
        array.add("Rainbow");
        array.add("White");
        Phosphor.getInstance().settingsManager.rSetting(new Setting("List Side", this, "Left", options));
        Phosphor.getInstance().settingsManager.rSetting(new Setting("List Color",this, "White", array));
        Phosphor.getInstance().settingsManager.rSetting(new Setting("Background", this, false));
    }

}

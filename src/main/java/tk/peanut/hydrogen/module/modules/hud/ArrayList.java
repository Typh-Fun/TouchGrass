package tk.peanut.hydrogen.module.modules.hud;

import com.darkmagician6.eventapi.EventTarget;
import com.darkmagician6.eventapi.types.Priority;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import tk.peanut.hydrogen.Hydrogen;
import tk.peanut.hydrogen.events.EventRender2D;
import tk.peanut.hydrogen.module.Category;
import tk.peanut.hydrogen.module.Info;
import tk.peanut.hydrogen.module.Module;
import tk.peanut.hydrogen.settings.Setting;
import tk.peanut.hydrogen.utils.FontHelper;
import tk.peanut.hydrogen.utils.ReflectionUtil;
import tk.peanut.hydrogen.utils.Utils;

import java.awt.*;

/**
 * Created by peanut on 18/02/2021
 */
@Info(name = "ArrayList", description = "Shows enabled modules", category = Category.Gui)
public class ArrayList extends Module {

    public ArrayList() {
        super(0x00, Color.white);

        new Thread(() -> {
            while (true) {
                try {
                    if (!ReflectionUtil.running.getBoolean(mc)) break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep((long) Hydrogen.getClient().settingsManager.getSettingByName("List Speed").getValDouble());
                } catch (InterruptedException e) {
                }
                for (Module mod : Hydrogen.getClient().moduleManager.getModules()) {
                    if (mod.isEnabled()) {
                        if (mod.getSlide() < FontHelper.hfontnormal.getStringWidth(mod.getName())) {
                            mod.setSlide(mod.getSlide() + 1);
                        }

                    } else if (mod.getSlide() != 0 && !mod.isEnabled()) {
                        if (mod.getSlide() > 0) {
                            mod.setSlide(mod.getSlide() - 1);
                        }

                    }
                }
            }
        }, "smooth array").start();

        java.util.ArrayList<String> array = new java.util.ArrayList<>();
        array.add("Rainbow");
        array.add("White");
        array.add("Category");

        Hydrogen.getClient().settingsManager.rSetting(new Setting("List Color",this, "Rainbow", array));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("List Speed", this, 3, 0, 20, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Rb. Saturation", this, 0.4, 0, 1, false));
        Hydrogen.getClient().settingsManager.rSetting(new Setting("Rb. Delay", this, 4, 1, 10, true));
    }


    @EventTarget(Priority.HIGHEST)
    public void drawArray(EventRender2D e) {
        if (Hydrogen.getClient().moduleManager.getModulebyName("HUD").isEnabled()) {
            if (Minecraft.getMinecraft().gameSettings.showDebugInfo)
                return;
            int count = 0;

            float rbdelay = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Delay").getValDouble();
            float rbsaturation = (float) Hydrogen.getClient().settingsManager.getSettingByName("Rb. Saturation").getValDouble();

            // dont ask why its seperated, if id put it together the drawrects wouldnt draw correctly and i have no idea why

            for (int j = 0; j < Hydrogen.getClient().moduleManager.getEnabledMods().size(); j++) {
                ScaledResolution sr = new ScaledResolution(mc);
                Module mod = Hydrogen.getClient().moduleManager.getEnabledMods().get(j);
                boolean background = Hydrogen.getClient().settingsManager.getSettingByName("Background").isEnabled();
                if (background) {
                    Gui.drawRect(sr.getScaledWidth() - mod.getSlide() - 6, 1 + j * 12, sr.getScaledWidth(), j * 12 + 13, 0x66000000);
                }
            }
            for (int i = 0; i < Hydrogen.getClient().moduleManager.getEnabledMods().size(); i++) {
                ScaledResolution sr = new ScaledResolution(mc);
                Module mod = Hydrogen.getClient().moduleManager.getEnabledMods().get(i);
                Color rainbow = Utils.getRainbowColor(rbdelay, rbsaturation, 1, count * 100);
                Color color = Hydrogen.getClient().settingsManager.getSettingByName("List Color").getValString().equalsIgnoreCase("White") ? Color.white : (Hydrogen.getClient().settingsManager.getSettingByName("List Color").getValString().equalsIgnoreCase("Rainbow") ? rainbow : mod.getColor());
                int mheight = (count * 11 + i) + 1;

                FontHelper.hfontnormal.drawStringWithShadow(mod.getName(), sr.getScaledWidth() - mod.getSlide() - 3, mheight, color);
                count++;

            }
        }


    }


    @EventTarget(Priority.LOWEST)
    public void drawBackgrounds(EventRender2D e) {

    }



}

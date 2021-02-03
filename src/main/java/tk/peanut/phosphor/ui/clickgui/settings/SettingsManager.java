package tk.peanut.phosphor.ui.clickgui.settings;

import tk.peanut.phosphor.Phosphor;
import tk.peanut.phosphor.modules.Module;

import java.util.ArrayList;


public class SettingsManager {
	
	private ArrayList<Setting> settings;
	
	public SettingsManager(){
		this.settings = new ArrayList<>();
	}
	
	public void rSetting(Setting in){
		this.settings.add(in);
	}
	
	public ArrayList<Setting> getSettings(){
		return this.settings;
	}
	
	public ArrayList<Setting> getSettingsByMod(Module mod){
		ArrayList<Setting> out = new ArrayList<>();
		for(Setting s : getSettings()){
			if(s.getParentMod().equals(mod)){
				out.add(s);
			}
		}
		if(out.isEmpty()){
			return null;
		}
		return out;
	}
	
	public Setting getSettingByName(String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name)){
				return set;
			}
		}
		System.err.println("["+ Phosphor.getInstance().name + "] Error Setting NOT found: '" + name +"'!");
		return null;
	}

	public Setting getSettingByName(Module m, String name){
		for(Setting set : getSettings()){
			if(set.getName().equalsIgnoreCase(name) && set.getParentMod() == m){
				return set;
			}
		}
		System.err.println("["+ Phosphor.getInstance().name + "] Error Setting NOT found: '" + name +"'!");
		return null;
	}

}
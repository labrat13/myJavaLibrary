/**
 * File jCodeLibrary Feature.java created Mar 26, 202212:41:30 AM 
 * @author: jsmith
 * 
 */
package Part1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jsmith
 *
 */
public class Feature
{
    public List<SettingItem> settings;
    private String name;
    
    public Feature() {
        this.settings = new ArrayList<SettingItem>();
    }
    
    public Feature(final String name) {
        this.settings = new ArrayList<SettingItem>();
        this.name = name;
    }
    
//    public static boolean nullCheck() {
//        return Feature.mc.player == null;
//    }
//    
//    public static boolean fullNullCheck() {
//        return Feature.mc.player == null || Feature.mc.world == null;
//    }
    
    public String getName() {
        return this.name;
    }
    
    public List<SettingItem> getSettings() {
        return this.settings;
    }
    
    public boolean hasSettings() {
        return !this.settings.isEmpty();
    }
    
//    public boolean isEnabled() {
//        return this instanceof Module && ((Module)this).isOn();
//    }
    
//    public boolean isDisabled() {
//        return !this.isEnabled();
//    }
    
//    public SettingItem register(final Setting setting) {
//        setting.setFeature(this);
//        this.settings.add(setting);
//        if (this instanceof Module && Feature.mc.currentScreen instanceof esohackGui) {
//            esohackGui.getInstance().updateModule((Module)this);
//        }
//        return setting;
//    }
//    
//    public void unregister(final Setting settingIn) {
//        final List<Setting> removeList = new ArrayList<Setting>();
//        for (final Setting setting : this.settings) {
//            if (setting.equals(settingIn)) {
//                removeList.add(setting);
//            }
//        }
//        if (!removeList.isEmpty()) {
//            this.settings.removeAll(removeList);
//        }
//        if (this instanceof Module && Feature.mc.currentScreen instanceof esohackGui) {
//            esohackGui.getInstance().updateModule((Module)this);
//        }
//    }
    
    public SettingItem getSettingByName(final String name) {
        for (final SettingItem setting : this.settings) {
            if (setting.getName().equalsIgnoreCase(name)) {
                return setting;
            }
        }
        return null;
    }
    
//    public void reset() {
//        for (final SettingItem setting : this.settings) {
//            setting.setValue(setting.getDefaultValue());
//        }
//    }
    
    public void clearSettings() {
        this.settings = new ArrayList<SettingItem>();
    }
}


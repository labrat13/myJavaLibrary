/**
 * File jCodeLibrary NotificationItem.java created Mar 26, 202212:49:55 AM 
 * @author: jsmith
 * 
 */
package Part1;

import java.util.Timer;

/**
 * @author jsmith
 *
 */
public class NotificationItem
{
 
        private final String text;
        private final long disableTime;
        private final float width;
        private final Timer timer;
        
//        public NotificationItem(final String text, final long disableTime) {
//            this.timer = new Timer();
//            this.text = text;
//            this.disableTime = disableTime;
//            this.width = (float)esohack.moduleManager.getModuleByClass(HUD.class).renderer.getStringWidth(text);
//            this.timer.reset();
//        }
//        
//        public void onDraw(final int y) {
//            final ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
//            if (this.timer.passedMs(this.disableTime)) {
//                esohack.notificationManager.getNotifications().remove(this);
//            }
//            RenderUtil.drawRect(scaledResolution.getScaledWidth() - 4 - this.width, (float)y, (float)(scaledResolution.getScaledWidth() - 2), (float)(y + esohack.moduleManager.getModuleByClass(HUD.class).renderer.getFontHeight() + 3), 1962934272);
//            esohack.moduleManager.getModuleByClass(HUD.class).renderer.drawString(this.text, scaledResolution.getScaledWidth() - this.width - 3.0f, (float)(y + 2), -1, true);
//        }
    }
    
}

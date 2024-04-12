package lexal.btb;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Global;
import net.minecraft.core.HitResult;

import javax.annotation.Nullable;

public class BTBClientContainer {
    @Nullable
    public static HitResult getMouseOver(){
        if (Global.isServer) return null;
        Minecraft mc = Minecraft.getMinecraft(Minecraft.class);
        return mc.objectMouseOver;
    }
}

package utilz;

import static utilz.Constants.EnemyConstants.FLYING_SAUCER;
import static utilz.Constants.WindowSize.*;

import objects.Projectile;

public class HelpMethods {
    public static boolean IsProjectileHittingLevel(Projectile p) {
        if((p.getHitbox().x + p.getHitbox().width / 2) >= Constants.WindowSize.WINDOW_SIZE_X) {
            return true;
        }
        return false;
    }

    public static boolean CanMoveHere(float x, float y, int width, int height) {
        if (x >= 0) {
            if (y >= 0) {
                if (x + width <= WINDOW_SIZE_X) {
                    if (y + height <= WINDOW_SIZE_Y) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

/**
 * Created by Adar on 6/10/17.
 */

package scripts.SPZeahBloods.constants;

import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSTile;

public class Positions {
    public static final RSTile MINE_TO_DARK_ALTAR_SHORTCUT_TILE = new RSTile(1761, 3872, 0);
    public static final RSTile DARK_ALTAR_TO_MINE_SHORTCUT_TILE = new RSTile(1761, 3873, 0);
    public static final RSTile BLOOD_ALTAR_TO_MINE_SHORTCUT_TILE = new RSTile(1743, 3854, 0);

    private static final RSArea MINE_AREA = new RSArea(new RSTile[] {
            new RSTile(1769, 3871, 0),
            new RSTile(1768, 3847, 0),
            new RSTile(1757, 3847, 0),
            new RSTile(1751, 3854, 0),
    });

    public static final RSArea getMineArea() {
        return Positions.MINE_AREA;
    }

    private static final RSArea DARK_ALTAR_AREA = new RSArea(new RSTile[] {
            new RSTile(1723, 3878, 0),
            new RSTile(1723, 3890, 0),
            new RSTile(1709, 3887, 0),
            new RSTile(1705, 3880, 0),
    });
    private static final RSArea BLOOD_ALTAR_AREA = new RSArea(new RSTile[] {
            new RSTile(1733, 3835, 0),
            new RSTile(1736, 3826, 0),
            new RSTile(1729, 3824, 0),
            new RSTile(1714, 3825, 0),
            new RSTile(1713, 3834, 0),
            new RSTile(1721, 3832, 0),
    });

    public static boolean atMine() {
        return Positions.MINE_AREA.contains(Player.getPosition());
    }

    public static boolean atDarkAltar() {
        return Positions.DARK_ALTAR_AREA.contains(Player.getPosition());
    }

    public static  boolean atBloodAltar() {
        return Positions.BLOOD_ALTAR_AREA.contains(Player.getPosition());
    }
}
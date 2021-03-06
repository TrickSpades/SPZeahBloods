
/**
 * Created by Adar on 6/10/17.
 */

package scripts.SPZeahBloods.src.tasks;

import org.tribot.api.General;
import org.tribot.api.DynamicClicking;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import scripts.SPZeahBloods.src.SPZeahBloods;
import scripts.SPZeahBloods.src.constants.Animations;
import scripts.SPZeahBloods.src.constants.ItemIds;
import scripts.SPZeahBloods.src.constants.ObjectNames;
import scripts.SPZeahBloods.src.constants.Positions;
import scripts.SPZeahBloods.src.util.Task;

public class VenerateEssence implements Task {

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public boolean validate() {
        return (Inventory.isFull() && Inventory.getCount(ItemIds.DENSE_ESSENCE_BLOCK_ID) > 10
                && Positions.getDarkAltarArea().contains(Player.getPosition())
        );
    }

    @Override
    public void execute() {
        RSObject[] objects = Objects.findNearest(40, ObjectNames.DARK_ALTAR_NAME);

        // If altar exists
        if (objects.length > 0) {
            RSTile tile = objects[0].getPosition();
            if (objects[0].isOnScreen()) {
                if (DynamicClicking.clickRSObject(objects[0], "Venerate")) {
                    General.sleep(600, 1400);
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(100); // Add this in to reduce CPU usage
                            return (Player.getAnimation() == Animations.VENERATE_ANIM || Player.isMoving());
                        }
                    }, General.random(1350, 2350));
                } else {
                    WebWalking.walkTo(Positions.getDarkAltarArea().getRandomTile());
                }
                General.sleep(300, 800);
            } else {
                SPZeahBloods.aCamera.turnToTile(tile);
                General.sleep(1000, 2200);
            }
        }
    }
}

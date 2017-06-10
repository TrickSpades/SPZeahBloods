/**
 * Created by Adar on 6/9/17.
 */

package scripts.SPZeahBloods;

import org.tribot.api.General;
import org.tribot.api2007.*;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import scripts.SPZeahBloods.actions.Mining;
import scripts.SPZeahBloods.constants.ItemIds;
import scripts.SPZeahBloods.constants.Positions;


@ScriptManifest(authors={"Spades"}, category="Runecrafting", name="SPZeahBloods", description="Start at runestone location.")
public class SPZeahBloods extends Script {

    private State state;
    private boolean shouldCraftFull = true;

    @Override
    public void run() {
        General.useAntiBanCompliance(true);
        loop(230, 550);
    }

    private void loop(int min, int max) {
        while (true) {
            state = getState();
            switch (state) {
                case MINING:
                    Mining.handleMining();
                    break;
                case TRAVELING_TO_DARK_ALTAR:
                    break;
                case VENERATING_ESSENCE:
                    break;
                case CRAFTING_ESSENCE:
                    break;
                case RETURNING_TO_MINE:
                    break;
                case TRAVELING_TO_BLOOD_ALTAR:
                    break;
                case CREATING_BLOOD_RUNES:
                    break;
            }
            sleep(min, max);
        }
    }

    private enum State {
        MINING, TRAVELING_TO_DARK_ALTAR, VENERATING_ESSENCE, CRAFTING_ESSENCE, RETURNING_TO_MINE, TRAVELING_TO_BLOOD_ALTAR,
        CREATING_BLOOD_RUNES
    }

    private State getState() {
        if (Positions.atMine()) {
            if (Inventory.isFull()) {
                if (Inventory.getCount(ItemIds.DENSE_ESSENCE_BLOCK_ID) > 10) {
                    println("travel to dark altar");
                    return State.TRAVELING_TO_DARK_ALTAR;
                }
            } else {
                return State.MINING;
            }
        } else if (Positions.atDarkAltar()) {
            println("at dark altar");
            if (Inventory.isFull()) {
                if (Inventory.getCount(ItemIds.DENSE_ESSENCE_BLOCK_ID) > 10) {
                    println("venerate essence");
                    return State.VENERATING_ESSENCE;
                } else if (Inventory.getCount(ItemIds.DARK_ESSENCE_BLOCK_ID) > 0) {
                    if (Inventory.getCount(ItemIds.DARK_ESSENCE_FRAGMENTS_ID) > 0 && !shouldCraftFull) {
                        println("go to blood altar");
                        return State.TRAVELING_TO_BLOOD_ALTAR;
                    } else {
                        println("craft essence");
                        return State.CRAFTING_ESSENCE;
                    }
                } else {
                    println("no dark essence");
                }
            } else {
                if (Inventory.getAll().length == 3) {
                    println("Return to mine");
                    shouldCraftFull = false;
                    return State.RETURNING_TO_MINE;
                } else {
                    println("craft essence");
                    shouldCraftFull = true;
                    return State.CRAFTING_ESSENCE;
                }
            }
        } else if (Positions.atBloodAltar()) {
            println("at blood altar");
            if (Inventory.isFull()) {
                println("create blood runes");
                return State.CREATING_BLOOD_RUNES;
            } else {
                if (Inventory.getCount(ItemIds.DARK_ESSENCE_BLOCK_ID) > 0) {
                    println("craft essence");
                    return State.CRAFTING_ESSENCE;
                    // no blocks but yes shards
                } else if (Inventory.getCount(ItemIds.DARK_ESSENCE_BLOCK_ID) == 0 && Inventory.getCount(ItemIds.DARK_ESSENCE_FRAGMENTS_ID) > 0) {
                    println("create blood runes");
                    return State.CREATING_BLOOD_RUNES;
                    // no blocks and no shards
                } else if (Inventory.getCount(ItemIds.DARK_ESSENCE_BLOCK_ID) == 0 && Inventory.getCount(ItemIds.DARK_ESSENCE_FRAGMENTS_ID) == 0) {
                    println("go mine");
                    return State.RETURNING_TO_MINE;
                }
            }
        }

        return State.RETURNING_TO_MINE;
    }
}
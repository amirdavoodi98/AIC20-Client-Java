package Client.utils;

import Client.Model.BaseUnit;
import Client.Model.World;
import Client.constants.ConstantsValue;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    private static Random random;

    public static int getRandomInt(int bound) {
        if (random == null) {
            random = new Random();
        }
        return random.nextInt(bound);
    }

    public static List<Integer> toList(int[] array) {
        List<Integer> list = new ArrayList<Integer>();
        for (Integer value : array) {
            list.add(value);
        }
        return list;
    }

    public static void chooseSpecificHand(World world){
        List<BaseUnit> allBaseUnits = world.getAllBaseUnits();
        List<BaseUnit> starterHand = new ArrayList<>();
        List<Integer> starterUnitList = Utils.toList(ConstantsValue.STARTER_UNITS);

        // choosing all flying units
        for (BaseUnit baseUnit : allBaseUnits) {
            if (starterUnitList.contains(baseUnit.getTypeId())) {
                starterHand.add(baseUnit);
            }
        }

        starterHand.forEach(baseUnit -> {
            System.out.println("selected units: " + baseUnit.getTypeId());
        });

        // picking the chosen hand - rest of the hand will automatically be filled with random baseUnits
        world.chooseHand(starterHand);
    }

    public static void chooseRandomHand(World world) {
        List<BaseUnit> allBaseUnits = world.getAllBaseUnits();
        List<Integer> starterUnitIds = new ArrayList<Integer>();
        List<BaseUnit> starterHand = new ArrayList<>();

        int handSize = world.getGameConstants().getHandSize();
        int allBaseUnitSize = allBaseUnits.size();

        while (starterUnitIds.size() < handSize) {
            int random = getRandomInt(allBaseUnitSize);
            if (!starterUnitIds.contains(random)) {
                starterUnitIds.add(random);
            }
        }

        for (BaseUnit baseUnit : allBaseUnits) {
            if (starterUnitIds.contains(baseUnit.getTypeId())) {
                starterHand.add(baseUnit);
            }
        }
        starterHand.forEach(baseUnit -> {
            System.out.println("selected units: " + baseUnit.getTypeId());
        });
        world.chooseHand(starterHand);
    }
}

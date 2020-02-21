package Client.utils;

import Client.Model.BaseUnit;
import Client.Model.Map;
import Client.Model.Unit;
import Client.Model.World;
import Client.constants.ConstantsValue;
import java.util.ArrayList;
import java.util.HashMap;
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

    public static void chooseSpecificHand(World world) {
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

    public static List<Unit> getUnits(World world, int row, int col) {
        return getUnits(world.getMap(), row, col);
    }

    public static List<Unit> getUnits(Map map, int row, int col) {
        return map.getCell(row, col).getUnits();
    }

    public static HashMap<Pair<Integer, Integer>, List<Unit>> getAllPresentUnits(World world) {
        HashMap<Pair<Integer, Integer>, List<Unit>> allPresentUnits = new HashMap<Pair<Integer, Integer>, List<Unit>>();
        Map map = world.getMap();
        for (int row = 0; row < map.getRowNum(); row++) {
            for (int col = 0; col < map.getColNum(); col++) {
                List<Unit> units = getUnits(map, row, col);
                if (units.size() != 0) {
                    allPresentUnits.put(new Pair<Integer, Integer>(row, col), units);
                }
            }
        }
        return allPresentUnits;
    }

    public static HashMap<Pair<Integer, Integer>, List<Unit>> getUnitsByPlayerId(World world, int playerId) {
        HashMap<Pair<Integer, Integer>, List<Unit>> presentUnits = new HashMap<Pair<Integer, Integer>, List<Unit>>();
        Map map = world.getMap();

        for (int row = 0; row < map.getRowNum(); row++) {
            for (int col = 0; col < map.getColNum(); col++) {
                List<Unit> units = getUnits(map, row, col);
                if (units.size() != 0) {
                    List<Unit> result = new ArrayList<Unit>();
                    units.forEach(unit -> {
                        if (unit.getPlayerId() == playerId) {
                            result.add(unit);
                        }
                    });
                    if (result.size() != 0) {
                        presentUnits.put(new Pair<Integer, Integer>(row, col), result);
                    }
                }
            }
        }
        return presentUnits;
    }

    public static HashMap<Pair<Integer, Integer>, List<Unit>> getMyUnits(World world) {
        return getUnitsByPlayerId(world, world.getMe().getPlayerId());
    }

    public static HashMap<Pair<Integer, Integer>, List<Unit>> getFriendUnits(World world) {
        return getUnitsByPlayerId(world, world.getFriend().getPlayerId());
    }

    public static HashMap<Pair<Integer, Integer>, List<Unit>> getFirstEnemyUnits(World world) {
        return getUnitsByPlayerId(world, world.getFirstEnemy().getPlayerId());
    }

    public static HashMap<Pair<Integer, Integer>, List<Unit>> getSecondEnemyUnits(World world) {
        return getUnitsByPlayerId(world, world.getSecondEnemy().getPlayerId());
    }

    public static HashMap<Pair<Integer, Integer>, List<Unit>> getEnemyUnits(World world) {
        HashMap<Pair<Integer, Integer>, List<Unit>> presentUnits = new HashMap<Pair<Integer, Integer>, List<Unit>>();
        Map map = world.getMap();
        int firstEnemyId = world.getFirstEnemy().getPlayerId();
        int secondEnemyId = world.getSecondEnemy().getPlayerId();

        for (int row = 0; row < map.getRowNum(); row++) {
            for (int col = 0; col < map.getColNum(); col++) {
                List<Unit> units = getUnits(map, row, col);
                if (units.size() != 0) {
                    List<Unit> result = new ArrayList<Unit>();
                    units.forEach(unit -> {
                        if (unit.getPlayerId() == firstEnemyId || unit.getPlayerId() == secondEnemyId) {
                            result.add(unit);
                        }
                    });
                    if (result.size() != 0) {
                        presentUnits.put(new Pair<Integer, Integer>(row, col), result);
                    }
                }
            }
        }
        return presentUnits;
    }
}

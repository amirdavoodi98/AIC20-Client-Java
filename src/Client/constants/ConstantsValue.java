package Client.constants;

import Client.Model.BaseUnit;
import Client.utils.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConstantsValue {
    public static final int[] STARTER_UNITS = new int[]{0, 8, 6, 2, 7};
    public static final int CELL_PENALTY_RATIO = 2;
    public static final int UNIT_PENALTY_RATIO = 3;
    public static final int BASE_UNIT_SIZE = 9;
    private static HashMap<BaseUnit, List<BaseUnit>> ANTI_ENEMY_UNIT;

    public static HashMap<BaseUnit, List<BaseUnit>> getAntiEnemyUnit(List<BaseUnit> allBaseUnit) {
        if (ANTI_ENEMY_UNIT == null) {
            for (int id = 0; id < BASE_UNIT_SIZE; id++) {
                List<BaseUnit> antiUnits = new ArrayList<>();
                BaseUnit target = Utils.findBaseUnitById(allBaseUnit, id);
                if (target != null) {
                    BaseUnit antiBaseUnit = null;
                    switch (target.getTypeId()) {
                        case 0:
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 0);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 1);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            break;

                        case 1:
                        case 3:
                        case 4:
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 6);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 7);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 8);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            break;

                        case 2:
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 1);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 3);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 4);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 0);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            break;
                        case 5:
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 2);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 0);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            break;
                        case 6:
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 0);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 8);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 2);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            break;
                        case 7:
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 6);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 5);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 8);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            break;
                        case 8:
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 0);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 2);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 8);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 6);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            antiBaseUnit = Utils.findBaseUnitById(allBaseUnit, 5);
                            if (antiBaseUnit != null) {
                                antiUnits.add(antiBaseUnit);
                            }
                            break;
                    }
                    ANTI_ENEMY_UNIT.put(target, antiUnits);
                }
            }
        }
        return ANTI_ENEMY_UNIT;
    }
}

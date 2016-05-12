package Logic;

import java.util.Map;

public class AfricanFighter extends Fighter {

    public AfricanFighter(String newName, Health newHP, float newHeight, float newWeight, float newSpeed, float newEndurance,
                          float newStamina , float newAccuracy, float newTactics, float newAggression, Map<String, Float> newDefCoefs){
        super(newName, newHP, newHeight, newWeight, newSpeed, newEndurance, newStamina, newAccuracy,newTactics, newAggression, newDefCoefs);
        recountProbabilities();
    }

}
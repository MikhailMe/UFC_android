package Logic;

import java.util.Map;

public class AsianFighter extends Fighter {

    public AsianFighter(String newName, Health newHP, float newHeight, float newWeight, float newSpeed, float newEndurance,
                        float newAccuracy, float newTactics, float newAggression, Map<String, Float> newDefCoefs) {
        super(newName, newHP, newHeight, newWeight, newSpeed, newEndurance, newAccuracy, newTactics, newAggression, newDefCoefs);
        recountProbabilities();
    }
}
package Logic;

import java.util.*;

public class Fighter {
    public String name;                     // имя
    private Health HP;                      // здоровье
    public Health getHP(){return HP;}       // геттер для здоровья
    private float height;                   // рост
    private float weight;                   // вес
    private float speed;                    // скорость
    private float endurance;                // выносливость
    private float stamina;                  // стамина
    private float accuracy;                 // точность
    private float tactics;                  // тактика
    public float aggression;                // агрессия
    public Random rand;                     // рандом
    public boolean rack;                    // стойка, true -> правая нога, false -> левая нога
    private Map<String, Float> defenceCoefs;// вероятность блокировки каждой части тела
    public ArrayList<Skill> skills;
    public float receivedDamage;            // полученный урон

    public Fighter(String name,Health newHP,float newHeight, float newWeight, float newSpeed, float newEndurance,
                   float newAccuracy, float newTactics, float newAggression, Map<String, Float> defCoef){
        this.name = name;
        this.HP = newHP;
        this.height = newHeight / 100 * 46;
        this.weight = newWeight / 25;
        this.speed = newSpeed / 10;
        this.endurance = newEndurance / 100;
        this.stamina = 1f;
        this.accuracy = newAccuracy / 100;
        this.tactics = newTactics / 100;
        this.aggression = newAggression / 100;
        rand = new Random();
        rack = true;
        defenceCoefs = defCoef;
        defenceCoefs.put("head",defCoef.get("head")/100);
        defenceCoefs.put("body",defCoef.get("body")/100);
        defenceCoefs.put("hands",defCoef.get("hands")/100);
        defenceCoefs.put("legs",defCoef.get("legs")/100);
        skills = new ArrayList<>();
        skills.add(new Skill(name,"Heal","",0f,0f));
        skills.add(new Skill(name,"uppercut","head",1.2f,14));
        skills.add(new Skill(name,"lowKick","legs",1.15f,14));
        skills.add(new Skill(name,"jab","head",0.9f,14));
        skills.add(new Skill(name,"cross","body",1.25f,14));
        skills.add(new Skill(name,"hook","head",0.8f,14));
        receivedDamage = 0f;
        recountProbabilities();
    }

    public Skill act(){
        if (((aggression > tactics) && (rand.nextFloat() < aggression)) || makeDecision())
            return attack();
        else return defend();
    }

    private boolean makeDecision(){
        if (!HP.check(tactics) || (stamina-tactics < -0.3f))
            return false;
        return true;
    }

    private float damageCount(float coef){
        if (rand.nextFloat() > accuracy)
            return 0f;
        return (weight * speed * speed / 2f) * coef * stamina;
    }

    protected void recountProbabilities(){
        float sum = 0f;
        for (Skill i : skills)
            sum += i.probability;
        float segment = 0f;
        for (Skill i : skills) {
            i.probability /= sum;
            i.probability += segment;
            segment = i.probability;
        }
    }

    private Skill attack(){
        float temp = rand.nextFloat();
        Skill result = null;
        for (int i = 0; i < skills.size(); ++i)
            if (skills.get(i).probability > temp){
                skills.get(i).damage = damageCount(skills.get(i).coef);
                setStamina(stamina -= (skills.get(i).coef-endurance)/4);
                result = skills.get(i);
                break;
            }
        return result;
    }

    public void setStamina(float value){
        if (value < 0f)
            stamina = 0.01f;
        else if (value > 1f)
            stamina = 1f;
        else stamina = value;
    }

    public void repay(Skill current){
        Judge.log.add(current);
        float k;
        try{
            k = defenceCoefs.get(current.place);
        }catch (NullPointerException nlptr){
            k = 0;
        }
        if (k != 0)
            current.damage /= k*10;
        this.HP.damage(current);
        receivedDamage += current.damage;
    }

    private Skill defend(){
        stamina += endurance / 3;
        HP.recovery(endurance);
        for (Skill s : skills) if (s.name.equals("Heal")) return s;
        return null;
    }
}
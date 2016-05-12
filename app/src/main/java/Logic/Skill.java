package Logic;

public class Skill {

    String name;
    String place;
    public float coef;
    public float damage;
    public float probability;
    public String whoIs;

    public Skill(String whoIs, String name, String place, float coef, float probability){
        this.name = name;
        this.place = place;
        this.coef = coef;
        this.probability = probability;
        this.whoIs = whoIs;
        damage = 0f;
    }
}
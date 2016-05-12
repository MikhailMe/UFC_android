package Logic;

public class Health {

    private static final int AMOUNT = 4;
    private float[] arrayHP;

    public Health(float new_head_HP, float new_body_HP, float new_legs_HP, float new_hands_HP) {
        arrayHP = new float[AMOUNT];
        arrayHP[0] = new_head_HP;
        arrayHP[1] = new_body_HP;
        arrayHP[2] = new_legs_HP;
        arrayHP[3] = new_hands_HP;
    }

    public boolean check(float tactics){
        float val = tactics * 10;
        for (int i = 0; i < arrayHP.length ; i++) {
            if (arrayHP[i]-val < -30)
                return false;
        }
        return true;
    }

    public void recovery(float endurance){
        for (int i = 0; i < AMOUNT; i++){
            arrayHP[i] += 10 * endurance;
            if (arrayHP[i] > 100)
                arrayHP[i] = 100;
        }
    }

    public boolean isAlive(){
        for (int i = 0; i < AMOUNT; ++i)
            if (arrayHP[i] <= 0)
                return false;
        return true;
    }

    public void damage(Skill obj){
        switch (obj.place){
            case "head":
                arrayHP[0] -= obj.damage;
                break;
            case "body":
                arrayHP[1] -= obj.damage;
                break;
            case "legs":
                arrayHP[2] -= obj.damage;
                break;
            case "hands":
                arrayHP[3] -= obj.damage;
                break;
            default: break;
        }
    }
}
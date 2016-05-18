package Logic;

import android.widget.TextView;

import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public final class Judge {

    private static Timer timer;
    public static LinkedList<Fighter> fighters = new LinkedList<Fighter>();
    private static Random rand;
    private static final int balanceTwo = 350;
    private static final int balanceThree = 280;
    public static int secondsRemaining = 15;
    public static LinkedList<Skill> log;

    public static void startFight(final TextView output) {
        rand = new Random();
        log = new LinkedList<Skill>();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                output.append(Judge.battle() + '\n');
            }
        },50,1000);
    }

    public static Integer controlTwo(int sum) {
        return balanceTwo - sum;
    }

    public static Integer controlThree(int sum) {
        return balanceThree - sum;
    }

    public static String battle() {
        secondsRemaining--;
        if (checkWinner() != null) {
            timer.cancel();
            return comment(checkWinner());
        }
        float sum = 0f;
        for (Fighter f : fighters)
            sum += f.aggression;
        if (rand.nextFloat() < fighters.get(0).aggression / sum)
            fighters.get(1).repay(fighters.get(0).act());
        else fighters.get(0).repay(fighters.get(1).act());
        return comment();
    }

    private static Fighter checkWinner() {
        if (secondsRemaining == -1) {
            float average = (fighters.get(0).receivedDamage + fighters.get(1).receivedDamage) / 2;
            for (Fighter f : fighters)
                if (f.receivedDamage > average)
                    return f;
        } else {
            for (Fighter f : fighters)
                if (!f.getHP().isAlive())
                    return f;
        }
        return null;
    }

    private static String comment() {
        Skill last = log.getLast();
        log.remove(last);
        if (last.name.equals("Heal"))
            return last.whoIs + " defends";
        if (last.damage == 0)
            return last.whoIs + " missed";
        return last.whoIs + " hits to " + last.place + " with " + last.damage + " pts of damage";
    }

    private static String comment(Fighter looser) {
        int ind = -1;
        for (int i = 0; i < fighters.size(); ++i)
            if (fighters.get(i) != looser) {
                ind = i;
                break;
            }
        Fighter winner = fighters.get(ind);
        return "\n\n" + winner.name + " won this battle.\nHe made " + looser.receivedDamage +
                "points of damage\nand recieve only " + winner.receivedDamage;
    }

/*    public static int balanceCount(int[] characts) {
        int remains = balance;
        for (int i = 0; i < 9; ++i)
            remains -= characts[i];
        return remains;
    }*/
}
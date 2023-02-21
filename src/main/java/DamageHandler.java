import java.util.Random;

public class DamageHandler {
    Random r = new Random();
    private final Pokemon opponent;
    private final Pokemon attacker;

    private final double attackFactor;
    private final double defenseFactor;
    private final double difference;

    public DamageHandler(Pokemon attacker, Pokemon opponent) {
        this.opponent = opponent;
        this.attacker = attacker;
        this.attackFactor = calculateAttackFactor(r);
        this.defenseFactor = calculateDefenseFactor(r);
        this.difference = calculateDamage();
        if (difference > 0) { // wenn Angriff stärker als Verteidigung -> Schaden zufügen
            dealDamage();
        } else {
            System.out.println("Die Attacke war zu schwach und wurde abgewehrt.");
        }
    }

    private double calculateDamage() {
        double attackStrength = attacker.getAttackPoints() * attackFactor;
        double defenseStrength = opponent.getDefensePoints() * defenseFactor;
        return attackStrength - defenseStrength;
    }

    private double calculateAttackFactor(Random r) {
        //System.out.println("Attackfaktor: " + attFactor);
        return r.nextInt(101) / (double) 100;
    }

    private double calculateDefenseFactor(Random r) {
        //System.out.println("Defensefaktor: " + defFactor);
        return r.nextInt(51) / (double) 100;
    }

    private void dealDamage() {
        int damage = (int) Math.round(difference);
        System.out.println("Schaden für den Gegner: " + damage);
        opponent.setCurrentHealth(opponent.getCurrentHealth() - damage);
    }
}

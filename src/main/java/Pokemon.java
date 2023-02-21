import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    private final String name;
    private final int pokedexNumber;
    private final String type1;
    private final String type2;
    private final int maxHealth;
    private final ArrayList<String> abilitiesList;
    private final int attackPoints;
    private final int defensePoints;
    private final List<String> asciiPic;


    private int currentHealth;
    private int healthPercentage;

    public Pokemon(String pokeData) throws IOException{
        String[] pokeDataArr = pokeData.split(",");
        this.name = pokeDataArr[29];
        this.pokedexNumber = Integer.parseInt(pokeDataArr[31]);
        this.type1 = pokeDataArr[35];
        this.type2 = pokeDataArr[36];
        this.maxHealth = Integer.parseInt(pokeDataArr[28]);
        this.currentHealth = maxHealth;
        this.attackPoints = Integer.parseInt(pokeDataArr[32]);
        this.defensePoints = Integer.parseInt(pokeDataArr[33]);
        this.abilitiesList = produceAbilitiesList(pokeDataArr[0]);
        this.asciiPic = importAscii(pokedexNumber);
        this.healthPercentage = 100;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "name='" + name + '\'' +
                ", pokedexNumber=" + pokedexNumber +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", maxHealth=" + maxHealth +
                ", currentHealth=" + currentHealth +
                ", abilities='" + abilitiesList + '\'' +
                ", attackPoints=" + attackPoints +
                ", defensePoints=" + defensePoints +
                '}';
    }

    private List<String> importAscii(int pokedexNumber) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Ascii.txt"));
        List<String> asciiLines = new ArrayList<>();

        String currentLine = reader.readLine();
        while(!currentLine.contains(String.valueOf(pokedexNumber))){
            currentLine = reader.readLine();
        }
        currentLine = reader.readLine();
        while (!currentLine.contains("break;")){
            asciiLines.add(currentLine);
            currentLine = reader.readLine();
        }
        reader.close();
        return asciiLines;
    }

    public void printAscii(){
        for (String s : asciiPic) {
            System.out.println(s);
        }
    }

    public String getName() {
        return name;
    }


    public int getCurrentHealth() {
        return currentHealth;
    }

    public ArrayList<String> getAbilitiesList() {
        return abilitiesList;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public int getHealthPercentage() {
        return healthPercentage;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
        this.healthPercentage = calculateHealthPercentage(currentHealth, maxHealth);
    }


    public int calculateHealthPercentage(int currentHealth, int maxHealth){
        double percentage = (100*currentHealth)/(double)maxHealth;
        percentage = Math.round(percentage*100)/100; //Prozent werden gerundet
        if(percentage < 0){
            percentage = 0;
        }
        return (int) percentage;
    }

    private ArrayList<String> produceAbilitiesList(String abilitiesString){
        ArrayList<String> abilitiesList = new ArrayList<>();
        String[] abilitiesArray = abilitiesString.split("'");
        for (String s : abilitiesArray) {
            if (!s.equals(" ")) {
                abilitiesList.add(s);
            }
        }
        return abilitiesList;
    }


    public void attack(Pokemon opponent){
        System.out.println("Attacke!");
        new DamageHandler(this, opponent);
    }


}

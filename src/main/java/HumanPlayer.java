import java.util.Scanner;

public class HumanPlayer {
    private String name;
    private Pokemon chosenPokemon;

    public HumanPlayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Pokemon getChosenPokemonWithAscii() {
        chosenPokemon.printAscii();
        return chosenPokemon;
    }

    public Pokemon getChosenPokemon() {
        return chosenPokemon;
    }

    public void setChosenPokemon(Pokemon chosenPokemon) {
        this.chosenPokemon = chosenPokemon;
    }

    public void chooseAttack(Scanner sn) {
        System.out.println("Folgende Attacken sind möglich:");
        for (int i = 0; i < chosenPokemon.getAbilitiesList().size(); i++) {
            System.out.println(i + ") " + chosenPokemon.getAbilitiesList().get(i));
        }
        System.out.println("Wähle aus!");
        if (sn.hasNextInt()) {
            int chosenAttackNum = sn.nextInt();
            if (chosenAttackNum >= 0 && chosenAttackNum < chosenPokemon.getAbilitiesList().size()) {
                System.out.println(chosenPokemon.getName() + " setzt " + chosenPokemon.getAbilitiesList().get(chosenAttackNum) + " ein!");
            } else {
                chooseAttack(sn);
            }
        }
    }


}

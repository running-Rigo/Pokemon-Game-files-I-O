import java.io.IOException;
import java.util.Random;
public class ComputerOpponent {
    private final Random r = new Random();
    private Pokemon chosenPokemon;
    public ComputerOpponent() {
    }

    public String choosePokemon() throws IOException {
        int randomPokeNum = r.nextInt(152);
        return PokemonChoser.searchPokemonByNumber(randomPokeNum); //entspricht String chosenPokemonData
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


    public void chooseAttack(){
        int randomAttackNum = r.nextInt(getChosenPokemon().getAbilitiesList().size());
        System.out.println(chosenPokemon.getName() + " setzt " + chosenPokemon.getAbilitiesList().get(randomAttackNum) +" ein!");
    }
}

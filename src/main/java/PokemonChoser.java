import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class PokemonChoser {
    public static String getUserPokemonChoice(Scanner sn) throws IOException {
        String chosenPokemonData = "";

        if (sn.hasNextInt()) {
            int pokeChoiceInt = Integer.parseInt(sn.nextLine()); //falls Nummer;
            if (pokeChoiceInt > 0 && pokeChoiceInt < 152) {
                chosenPokemonData = searchPokemonByNumber(pokeChoiceInt);
            } else {
                System.out.println("Du musst ein Pokemon zw. 1 und 151 wählen.");
            }
        } else {
            //falls keine Zahl sondern Pokemon-Name:
            String pokeChoiceString = sn.nextLine();
            chosenPokemonData = searchPokemonByName(pokeChoiceString); //Methode sucht Pokemon anhand des Namens
        }
        //Rekursion statt Schleife falls ungültige Eingabe sodass kein Pokemon gefunden wurde:
        if (chosenPokemonData.equals("")) {
            return getUserPokemonChoice(sn);
        }
        return chosenPokemonData;
    }

    //Pokemon anhand des Namens aus Liste suchen:
    public static String searchPokemonByName(String pokeChoiceString) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Pokedata.csv"));
        String chosenPokemonData ="";
        String currentLine = reader.readLine();
        while (currentLine != null) {
            if (currentLine.contains(pokeChoiceString)) {
                chosenPokemonData = currentLine;
                reader.close();
                return chosenPokemonData;
            }
            currentLine = reader.readLine();
        }
        reader.close();
        return chosenPokemonData;
    }

    //Pokemon anhand der Nummer aus Liste suchen:
    public static String searchPokemonByNumber(int chosenPokeNr) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Pokedata.csv"));
        String chosenPokemonData = "";
        String currentLine = reader.readLine();
        for(int i = 0; i <= chosenPokeNr; i++){
            if ( i == chosenPokeNr){
                chosenPokemonData = currentLine;
            }
            currentLine = reader.readLine();
        }
        return chosenPokemonData;
    }

}

import java.io.IOException;
import java.util.Scanner;

public class GameMaster {
    private static HumanPlayer player;
    private static ComputerOpponent computer;
    private static Scanner sn;

    public static void startGame() throws IOException {
        sn = new Scanner(System.in);
        computer = new ComputerOpponent();

        //Auswahl, Initialisierung und Print der Pokemons:
        System.out.println("Willkommen in der Pokemon-Arena!");
        System.out.println("Wie heißt du?");
        String name = sn.nextLine();
        player = new HumanPlayer(name);
        System.out.println(player.getName() + ", bitte wähle ein Pokemon (Eingabe Nr. 1-151 oder Name des Pokemons)");
        Pokemon pokemon = new Pokemon(PokemonChoser.getUserPokemonChoice(sn));
        player.setChosenPokemon(pokemon);
        System.out.println("Du hast folgendes Pokemon gewählt:");
        System.out.println(player.getChosenPokemonWithAscii());
        System.out.println("Nun wählt der Computer ein Pokemon...");
        Pokemon pokemon1 = new Pokemon(computer.choosePokemon());
        computer.setChosenPokemon(pokemon1);
        System.out.println("Der Computer wählt:");
        System.out.println(computer.getChosenPokemonWithAscii());
        startBattle();
    }

    public static void startBattle() {
        boolean stillPlaying = true;
        boolean playerAttacks = true;
        while (stillPlaying) {
            showHealthStatus();
            Pokemon opponent;
            if (playerAttacks) {
                System.out.println("Du bist dran, " + player.getName() + "!");
                System.out.println();
                opponent = computer.getChosenPokemon();
                player.chooseAttack(sn);
                player.getChosenPokemon().attack(opponent); //Pokemon führt Attacke aus
            } else {
                opponent = player.getChosenPokemon();
                computer.chooseAttack();
                computer.getChosenPokemon().attack(opponent); //Pokemon führt Attacke aus
            }
            //Wenn Health des Gegners auf 0 reduziert:
            if (opponent.getCurrentHealth() <= 0) {
                stillPlaying = false;
                System.out.println(opponent.getName() + " hat verloren.");
                System.out.println("Ergebnis des Spieles: ");
                showHealthStatus();
                if (playerAttacks) {
                    System.out.println("Gratuliere, du hast gewonnen!");
                } else {
                    System.out.println("Leider hast du diesmal gegen den Computer verloren.");
                }
            }
            playerAttacks = !playerAttacks; //Spieler spielen abwechselnd
        }
    }

    public static void showHealthStatus() {
        System.out.println();
        System.out.println("Für " + player.getName() + ":");
        System.out.println(player.getChosenPokemon().getName() + " (" + player.getChosenPokemon().getHealthPercentage() + "/100 ♥)");
        System.out.println("Für den Computer: ");
        System.out.println(computer.getChosenPokemon().getName() + " (" + computer.getChosenPokemon().getHealthPercentage() + "/100 ♥)");
        System.out.println();
    }


}

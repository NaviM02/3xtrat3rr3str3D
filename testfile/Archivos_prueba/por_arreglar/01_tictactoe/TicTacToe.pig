import tictactoe.Tablero.z
import tictactoe.Jugador.z
import tictactoe.JugadorIA.z
import tictactoe.JugadorFijo.z
import tictactoe.Partida.z

VARIABILES>
esto ana : novus JugadorFijo("Ana", 1) : JugadorFijo ;
esto bot : novus JugadorIA("Bot", 2) : JugadorIA ;
esto bot1 : novus JugadorIA("Bot1", 1) : JugadorIA ;
esto bot2 : novus JugadorIA("Bot2", 2) : JugadorIA ;
esto partida1 : novus Partida(ana, bot) : Partida ;
esto partida2 : novus Partida(bot1, bot2) : Partida ;

MAIOR>
>> "=== PARTIDA 1: Ana (fijo) vs Bot (IA) ===" ;
ana.agregarJugada(0, 0);
ana.agregarJugada(1, 0);
ana.agregarJugada(2, 0);
partida1.jugar();

>> "=== PARTIDA 2: Bot1 vs Bot2 ===" ;
partida2.jugar();

>> "Victorias Ana: " >> ana.getVictorias() ;
>> "Victorias Bot: " >> bot.getVictorias() ;
>> "Victorias Bot1: " >> bot1.getVictorias() ;
>> "Victorias Bot2: " >> bot2.getVictorias() ;
FINIS;

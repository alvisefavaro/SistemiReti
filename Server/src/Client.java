import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            // Messaggio di benvenuto e istruzioni per l'utente
            System.out.println("Benvenuto nel sistema di ricerca ospedali!");
            System.out.println("Comandi disponibili:");
            System.out.println("-------------------------------------------------");
            System.out.println("GET_ROW n                - Ottieni la riga n°n");
            System.out.println("GET_ALL                  - Ottieni tutti gli ospedali");
            System.out.println("GET_COMUNE comune        - Ricerca ospedali per comune");
            System.out.println("GET_NOME nome            - Ricerca ospedali per nome");
            System.out.println("GET_PROVINCIA provincia  - Ricerca ospedali per provincia");
            System.out.println("GET_REGIONE regione      - Ricerca ospedali per regione");
            System.out.println("GET_ANNO_INSERIMENTO anno - Ricerca ospedali per anno di inserimento");
            System.out.println("GET_IDENTIFICATORE_OSM identificatore - Ricerca per identificatore OpenStreetMap");
            System.out.println("GET_LONGITUDINE longitudine - Ricerca ospedali per longitudine");
            System.out.println("GET_LATITUDINE latitudine - Ricerca ospedali per latitudine");
            System.out.println("-------------------------------------------------");
            System.out.println("Per uscire, digita: 'exit'");

            String comando;
            while (true) {
                // Chiedi all'utente di inserire un comando
                System.out.print("\nInserisci un comando: ");
                comando = tastiera.readLine().trim();

                if (comando.equalsIgnoreCase("exit")) {
                    System.out.println("Arrivederci!");
                    break;
                }

                // Invia il comando al server
                out.println(comando);

                // Ricevi e stampa la risposta dal server
                String risposta;
                boolean fine = false;
                while ((risposta = in.readLine()) != null) {
                    if (risposta.equals("*END*")) {
                        fine = true;
                        break;
                    }
                    System.out.println(risposta);
                }

                // Se la risposta è finita, esci dal loop di risposta
                if (fine) {
                    continue;
                }
            }

        } catch (IOException e) {
            System.err.println("Errore di connessione con il server: " + e.getMessage());
        }
    }
}

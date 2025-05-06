import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

            String richiesta;
            while ((richiesta = in.readLine()) != null) {
                if (richiesta.startsWith("GET_ROW")) {
                    try {
                        int index = Integer.parseInt(richiesta.split(" ")[1]);
                        if (index >= 0 && index < ServerMain.ospedali.size()) {
                            out.println(ServerMain.ospedali.get(index));
                        } else {
                            out.println("*ERROR: Invalid row*");
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        out.println("*ERROR: Invalid request format*");
                    }
                } else if (richiesta.equals("GET_ALL")) {
                    for (Ospedale o : ServerMain.ospedali) {
                        out.println(o);
                    }
                    out.println("*END*");
                } else if (richiesta.startsWith("GET_COMUNE")) {
                    String comune = richiesta.split(" ", 2)[1];
                    for (Ospedale o : ServerMain.ricercaPerComune(comune)) {
                        out.println(o);
                    }
                    out.println("*END*");
                } else if (richiesta.startsWith("GET_NOME")) {
                    String nome = richiesta.split(" ", 2)[1];
                    for (Ospedale o : ServerMain.ricercaPerNome(nome)) {
                        out.println(o);
                    }
                    out.println("*END*");
                } else if (richiesta.startsWith("GET_PROVINCIA")) {
                    String provincia = richiesta.split(" ", 2)[1];
                    for (Ospedale o : ServerMain.ricercaPerProvincia(provincia)) {
                        out.println(o);
                    }
                    out.println("*END*");
                } else if (richiesta.startsWith("GET_REGIONE")) {
                    String regione = richiesta.split(" ", 2)[1];
                    for (Ospedale o : ServerMain.ricercaPerRegione(regione)) {
                        out.println(o);
                    }
                    out.println("*END*");
                } else if (richiesta.startsWith("GET_ANNO_INSERIMENTO")) {
                    String annoInserimento = richiesta.split(" ", 2)[1];
                    for (Ospedale o : ServerMain.ricercaPerAnnoInserimento(annoInserimento)) {
                        out.println(o);
                    }
                    out.println("*END*");
                } else if (richiesta.startsWith("GET_IDENTIFICATORE_OSM")) {
                    String identificatore = richiesta.split(" ", 2)[1];
                    for (Ospedale o : ServerMain.ricercaPerIdentificatoreOpenStreetMap(identificatore)) {
                        out.println(o);
                    }
                    out.println("*END*");
                } else if (richiesta.startsWith("GET_LONGITUDINE")) {
                    String longitudine = richiesta.split(" ", 2)[1];
                    for (Ospedale o : ServerMain.ricercaPerLongitudine(longitudine)) {
                        out.println(o);
                    }
                    out.println("*END*");
                } else if (richiesta.startsWith("GET_LATITUDINE")) {
                    String latitudine = richiesta.split(" ", 2)[1];
                    for (Ospedale o : ServerMain.ricercaPerLatitudine(latitudine)) {
                        out.println(o);
                    }
                    out.println("*END*");
                } else {
                    out.println("*ERROR: Unknown command*");
                }
            }

        } catch (IOException e) {
            System.err.println("Connessione persa: " + e.getMessage());
        }
    }
}

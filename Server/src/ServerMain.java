import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ServerMain {
    private static final int PORT = 12345;
    public static List<Ospedale> ospedali = new ArrayList<>();

    public static void main(String[] args) {
        caricaCSV("Mappa-degli-ospedali-in-Italia.csv");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server in ascolto sulla porta " + PORT);
            ExecutorService pool = Executors.newCachedThreadPool();

            while (true) {
                Socket client = serverSocket.accept();
                pool.execute(new ClientHandler(client));
            }

        } catch (IOException e) {
            System.err.println("Errore Server: " + e.getMessage());
        }
    }

    // Caricamento CSV
    private static void caricaCSV(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String riga;
            br.readLine(); // salta intestazione
            while ((riga = br.readLine()) != null) {
                String[] campi = riga.split(";");
                if (campi.length >= 9) {
                    ospedali.add(new Ospedale(
                            campi[0], // comune
                            campi[1], // provincia
                            campi[2], // regione
                            campi[3], // nome
                            campi[4], // anno inserimento
                            campi[5], // data e ora inserimento
                            campi[6], // identificatore OpenStreetMap
                            campi[7], // longitudine
                            campi[8]  // latitudine
                    ));
                } else {
                    System.err.println("Riga ignorata: dati insufficienti -> " + riga);
                }
            }
        } catch (IOException e) {
            System.err.println("Errore lettura CSV: " + e.getMessage());
        }
    }

    // Metodi di ricerca per ogni campo
    public static List<Ospedale> ricercaPerComune(String comune) {
        List<Ospedale> risultati = new ArrayList<>();
        for (Ospedale ospedale : ospedali) {
            if (ospedale.getComune().equalsIgnoreCase(comune)) {
                risultati.add(ospedale);
            }
        }
        return risultati;
    }

    public static List<Ospedale> ricercaPerNome(String nome) {
        List<Ospedale> risultati = new ArrayList<>();
        for (Ospedale ospedale : ospedali) {
            if (ospedale.getNome().equalsIgnoreCase(nome)) {
                risultati.add(ospedale);
            }
        }
        return risultati;
    }

    public static List<Ospedale> ricercaPerProvincia(String provincia) {
        List<Ospedale> risultati = new ArrayList<>();
        for (Ospedale ospedale : ospedali) {
            if (ospedale.getProvincia().equalsIgnoreCase(provincia)) {
                risultati.add(ospedale);
            }
        }
        return risultati;
    }

    public static List<Ospedale> ricercaPerRegione(String regione) {
        List<Ospedale> risultati = new ArrayList<>();
        for (Ospedale ospedale : ospedali) {
            if (ospedale.getRegione().equalsIgnoreCase(regione)) {
                risultati.add(ospedale);
            }
        }
        return risultati;
    }

    public static List<Ospedale> ricercaPerAnnoInserimento(String annoInserimento) {
        List<Ospedale> risultati = new ArrayList<>();
        for (Ospedale ospedale : ospedali) {
            if (ospedale.getAnnoInserimento().equals(annoInserimento)) {
                risultati.add(ospedale);
            }
        }
        return risultati;
    }

    public static List<Ospedale> ricercaPerIdentificatoreOpenStreetMap(String identificatore) {
        List<Ospedale> risultati = new ArrayList<>();
        for (Ospedale ospedale : ospedali) {
            if (ospedale.getIdentificatoreOpenStreetMap().equals(identificatore)) {
                risultati.add(ospedale);
            }
        }
        return risultati;
    }

    public static List<Ospedale> ricercaPerLongitudine(String longitudine) {
        List<Ospedale> risultati = new ArrayList<>();
        for (Ospedale ospedale : ospedali) {
            if (ospedale.getLongitudine().equals(longitudine)) {
                risultati.add(ospedale);
            }
        }
        return risultati;
    }

    public static List<Ospedale> ricercaPerLatitudine(String latitudine) {
        List<Ospedale> risultati = new ArrayList<>();
        for (Ospedale ospedale : ospedali) {
            if (ospedale.getLatitudine().equals(latitudine)) {
                risultati.add(ospedale);
            }
        }
        return risultati;
    }
}

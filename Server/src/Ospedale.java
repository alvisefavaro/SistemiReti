public class Ospedale {
    private String comune;
    private String provincia;
    private String regione;
    private String nome;
    private String annoInserimento;
    private String dataEOraInserimento;
    private String identificatoreOpenStreetMap;
    private String longitudine;
    private String latitudine;

    public Ospedale(String comune, String provincia, String regione, String nome,
                    String annoInserimento, String dataEOraInserimento,
                    String identificatoreOpenStreetMap, String longitudine, String latitudine) {
        this.comune = comune;
        this.provincia = provincia;
        this.regione = regione;
        this.nome = nome;
        this.annoInserimento = annoInserimento;
        this.dataEOraInserimento = dataEOraInserimento;
        this.identificatoreOpenStreetMap = identificatoreOpenStreetMap;
        this.longitudine = longitudine;
        this.latitudine = latitudine;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s",
                comune, provincia, regione, nome,
                annoInserimento, dataEOraInserimento,
                identificatoreOpenStreetMap, longitudine, latitudine);
    }

    // Getter methods
    public String getComune() {
        return comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getRegione() {
        return regione;
    }

    public String getNome() {
        return nome;
    }

    public String getAnnoInserimento() {
        return annoInserimento;
    }

    public String getDataEOraInserimento() {
        return dataEOraInserimento;
    }

    public String getIdentificatoreOpenStreetMap() {
        return identificatoreOpenStreetMap;
    }

    public String getLongitudine() {
        return longitudine;
    }

    public String getLatitudine() {
        return latitudine;
    }
}

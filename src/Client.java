import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Client {

    private final String socialSecurityNumb;
    private final String name;
    private final LocalDate dateOfLastPaiment;

    public Client(String socialSecurityNumb, String name, LocalDate dateOfLastPaiment) {
        this.socialSecurityNumb = socialSecurityNumb;
        this.name = name;
        this.dateOfLastPaiment = dateOfLastPaiment;
    }

    public static List<Client> getClient(String fileName) {
        List<Client> clientInfo = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String newLine;
            while ((newLine = reader.readLine()) != null) {
                String[] clientDetails = newLine.split(", ");

                if (clientDetails.length == 2 | clientDetails.length == 1) {
                    String socialSecurityNmb = clientDetails[0].trim();
                    String name = clientDetails[1].trim();

                    newLine = reader.readLine();
                    String[] lastPaid = newLine.split("\n");

                    String LastPaiment = lastPaid[0].trim();
                    LocalDate dateOfLastPaiment = LocalDate.parse(LastPaiment);

                    Client client = new Client(socialSecurityNmb, name, dateOfLastPaiment);
                    clientInfo.add(client);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    return clientInfo;
    }
    //Metod som kollar av datumen för senast betalat medlemskap
    //Lägg till att den jämför med text filen

    public boolean activeClient() {
        boolean isActiveClient = ChronoUnit.DAYS.between(
                this.dateOfLastPaiment, LocalDate.now()) < 365;
        return isActiveClient;
    }

    public String getSocialSecurityNumb() {
        return socialSecurityNumb;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfLastPaiment() {
        return this.dateOfLastPaiment;
    }
}





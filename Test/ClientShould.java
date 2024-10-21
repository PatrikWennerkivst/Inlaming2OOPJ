import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientShould {

    @Test
    void readFromFile() {
        List<Client> clients = Client.getClient("src/ClientInfo.txt");
        assertFalse(clients.isEmpty());
    }

    @Test
    void haveCorrectDataInArraylist() {
        List<Client> clients = Client.getClient("src/ClientInfo.txt");
        //Hämtar ett existerande exemepl från uppgiftens txt fil
        String expectedSsn = "7911061234";
        String expectedName = "Fritjoff Flacon";
        LocalDate expectedLastPaiment =  LocalDate.parse("2023-12-16");
        //Söker efter exemplet ur uppgiftens txt fil via index igenom listan som jag skapat av min klass Client.
        assertEquals(expectedSsn, clients.get(5).getSocialSecurityNumb());
        assertEquals(expectedName, clients.get(5).getName());
        assertEquals(expectedLastPaiment, clients.get(5).getDateOfLastPaiment());
    }

    @Test
    //Skapar upp två nya Client:er för att kolla om kunden är en aktiv kund eller inte med hjälp av metoden activeClient
    //som returnerar en Boolean om hurvida en kund är aktiv = true, och om en kund inte är akvtiv = false.
    void returnActiveClientAndNotActiveClient() {
        Client client1 = new Client("9407156677", "Patrik Wennerkvist,", LocalDate.parse("2022-01-15"));
        assertFalse(client1.activeClient());
        Client client2 = new Client("9512286677", "Carina Gerlach", LocalDate.now());
        assertTrue(client2.activeClient());
    }

    @Test
    void searchForClientShouldReturnNullForNonExsistingClient() {
        //metoden Gym.searchForClient returnerar null om en person inte är en registrerad kund.
        //Där av testar jag att sökningn efter Patrik, 9407156677 returnerar null
        //Alltså, är Patrik inte en kund eftersom att det inte går att söka på hans namn/personnummer i clientList.
        List<Client> clientList = Client.getClient("src/ClientInfo.txt");
        assertNull(Gym.searchForClient(clientList, "Patrik"));
        assertNull(Gym.searchForClient(clientList, "9407156677"));
    }
}

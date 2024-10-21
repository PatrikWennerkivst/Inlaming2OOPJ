import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GymShould {

    @Test
    void searchForClientWhitSocialSecurityNumber() {
        //skapade en ny Client för att kunna testa om det går att sök efter clienten i Array listan av clients
        Client clientTest = new Client("9407156677","Patrik Wenenrkvist",
                LocalDate.parse("2024-01-13"));
        List<Client> clientTestList = new ArrayList<>();
        clientTestList.add(clientTest);
        Client searchClient =  Gym.searchForClient(clientTestList, "9407156677");
        assertEquals("9407156677", searchClient.getSocialSecurityNumb());
    }

    @Test
    void searchForClientWhitName() {
        //skapade en ny Client för att kunna testa om det går att sök efter clienten i Array listan av clients
        Client clientTest = new Client("9407156677","Patrik Wennerkvist",
                LocalDate.parse("2024-01-13"));
        List<Client> clientTestList = new ArrayList<>();
        clientTestList.add(clientTest);
        Client searchClient =  Gym.searchForClient(clientTestList, "Patrik Wennerkvist");
        assertEquals("Patrik Wennerkvist", searchClient.getName());
    }

    @Test
    void checkInClient() throws IOException {
        List<Client> clientList = Client.getClient("Test/ClientInfoTest.txt");
        Gym.checkInClient(clientList.get(0), "Test/PtInfoTest.txt");
        BufferedReader reader = new BufferedReader(new FileReader("Test/PtInfoTest.txt"));
        String firstLine = reader.readLine();
        assertTrue(firstLine.contains("Patrik Wennerkvist"));
    }
    //Skapaer två txt filer för att kunna testa metoden. En där jag matade in en egen "kund"
    //samt en där jag sedan skriver in den nya "kunden" i txt filen PtInfoTest.txt. För att sedan kolla om
    //den nya kundens namn finns inskriven i txt filen.
}

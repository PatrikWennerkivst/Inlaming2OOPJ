import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class Gym {

    public static Client searchForClient(List<Client> clientList, String nameOrSsn) {
        for (Client client : clientList) {
            if (client.getName().equalsIgnoreCase(nameOrSsn) || client.getSocialSecurityNumb().equalsIgnoreCase(nameOrSsn)) {
                return  client;
            }
        }
        return null;
    }

    public static void checkInClient(Client client, String fileName) {
        LocalDateTime checkInTime = LocalDateTime.now();
        try(FileWriter writer = new FileWriter(fileName, true)) {
                writer.write(client.getName() + ", " + client.getSocialSecurityNumb() + ". Har checkat in: " + checkInTime + "\n");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String questionWindow(String option1, String option2, String messeage) {
        Object[] options = {option1, option2, "Avsluta"};

        int choice = JOptionPane.showOptionDialog(
                null,
                messeage,
                "Best Gym Ever 2.0",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        //Här använder jag mig av JOptionPane konstanter för att kunna få ut fler knapp alternativ som jag sedan tilldelar
        //jag dom sin egen String för att kunna ändra vad som visas i dialogrutan.
        if (choice == JOptionPane.YES_OPTION) {
            return option1;
        } else if (choice == JOptionPane.NO_OPTION) {
            return option2;
        } else if (choice == JOptionPane.CANCEL_OPTION) {
            return null;
        }
        return null;
    }
}


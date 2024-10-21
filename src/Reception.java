import javax.swing.*;
import java.util.List;

public class Reception {

    public  String searchForClient() {
        List<Client> listOfClients = Client.getClient("src/ClientInfo.txt");

        while(true) {
            String nameOrSSN = Gym.questionWindow("Namn", "Personnummer", "Vill du söka på\nnamn eller personnummer?");

            if (nameOrSSN == "Namn") {
                String userInputName = JOptionPane.showInputDialog("Vad är besökarens namn?");

                if(userInputName == null) {
                    break;
                }
                    Client client = Gym.searchForClient(listOfClients, userInputName);
                    if (client != null) {

                        if (client.activeClient()) {
                            JOptionPane.showMessageDialog(null, client.getName() + " Är en aktiv kund");
                            String ClientAwnser = Gym.questionWindow("Ja", "Nej", "Vill kunden checka in för ett\ntränginspass?");

                            if (ClientAwnser == "Ja"){
                                Gym.checkInClient(client, "src/PtInfo.txt");
                            } else if (ClientAwnser == "Nej") {
                                return null;
                            }
                            else {
                                break;
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(null, client.getName() + " är en kund, men medlemskapet\när inte aktivt.");
                        }
                        break;
                }
                else {
                    JOptionPane.showMessageDialog(null, userInputName + "\nfinns inte registrerad i systemet.");
                }
            }
            else if (nameOrSSN == "Personnummer") {
                String userInputSsn = JOptionPane.showInputDialog("Vad är besökarens personnummer?");

                if (userInputSsn == null) {
                    break;
                }
                Client client = Gym.searchForClient(listOfClients, userInputSsn);
                if (client != null) {
                    if (client.activeClient()) {
                        JOptionPane.showMessageDialog(null, client.getSocialSecurityNumb() + " Är en aktiv kund");
                        String ClientAwnser = Gym.questionWindow("Ja", "Nej", "Vill kunden checka in för ett\ntränginspass?");

                        if (ClientAwnser == "Ja"){
                            Gym.checkInClient(client, "src/PtInfo.txt");
                        } else if (ClientAwnser == "Nej") {
                            return null;
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, client.getSocialSecurityNumb() + " är en kund,\nmen medlemskapet är inte aktivt.");
                    }
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, userInputSsn + "\nfinns inte registrerad i systemet");
                }
            }
            else {
                break;
            }
        }
        return null;
    }
}

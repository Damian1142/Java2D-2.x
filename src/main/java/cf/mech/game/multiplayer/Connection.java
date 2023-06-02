package cf.mech.game.multiplayer;

import lombok.SneakyThrows;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Connection {
    Socket s;
    Scanner input;
    PrintStream output;

    //3157

    @SneakyThrows
    public Connection(String ip, int port) {
        s = new Socket(ip,port);
        input = new Scanner(s.getChannel());
        output = new PrintStream(s.getOutputStream());
    }

    public String send(String command){
        output.println(command);
        return input.nextLine();
    }

}
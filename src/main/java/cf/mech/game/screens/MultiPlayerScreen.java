package cf.mech.game.screens;

import cf.mech.Tool;
import cf.mech.game.Game;
import cf.mech.game.KeyLoger;
import cf.mech.game.Player;
import cf.mech.game.multiplayer.Connection;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MultiPlayerScreen extends AbstractScreen{
    public MultiPlayerScreen(Game game) {
        super(Tool.getImage("tlo/telko.png"), game);
        players = new ArrayList<>();
        connection = new Connection(JOptionPane.showInputDialog("Podaj IP: "),3157);
        playerImage = Tool.getImage("player/player.png");
        player = new Rectangle(50,60,80,80);
    }

    Connection connection;

    Rectangle player;

    Gson gson = new Gson();

    BufferedImage playerImage;

    ArrayList<Rectangle> players;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //g.drawImage(playerImage, player.x, player.y, null);
        for (Rectangle r : players) {
            g.drawImage(playerImage,r.x,r.y,null);
            //System.out.println(r);
        }
        //System.out.println("----------------------");
    }

    @Override
    public void update() {

        if (KeyLoger.keys[KeyEvent.VK_W]){
            player.y -= 5;
        }
        if (KeyLoger.keys[KeyEvent.VK_A]){
            player.x -= 5;
        }
        if (KeyLoger.keys[KeyEvent.VK_S]){
            player.y += 5;
        }
        if (KeyLoger.keys[KeyEvent.VK_D]){
            player.x += 5;
        }

        String out = connection.send("xy " + player.x + " " + player.y);
        //System.out.println(out);
        players.clear();
        for(Object o : gson.fromJson(out,ArrayList.class)){
            LinkedTreeMap ltm = (LinkedTreeMap)o;
            players.add(new Rectangle(Integer.parseInt(ltm.get("x").toString().split("\\.")[0]),Integer.parseInt(ltm.get("y").toString().split("\\.")[0]),0,0));

        }
        //players = ;
        //players.forEach(System.out::println);
        game.repaint();
    }
}

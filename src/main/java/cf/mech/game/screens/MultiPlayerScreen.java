package cf.mech.game.screens;

import cf.mech.Tool;
import cf.mech.game.Game;
import cf.mech.game.multiplayer.Connection;
import com.google.gson.Gson;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class MultiPlayerScreen extends AbstractScreen{
    public MultiPlayerScreen(Game game) {
        super(Tool.getImage("tlo/telko.png"), game);
        connection = new Connection("localhost",3157);
        playerImage = Tool.getImage("player/player.png");
    }

    Connection connection;

    Rectangle player;

    Gson gson = new Gson();

    BufferedImage playerImage;

    ArrayList<Rectangle> players;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(playerImage, player.x, player.y, null);
        for (Rectangle r : players) {
            g.drawImage(playerImage,r.x,r.y,null);
        }
    }

    @Override
    public void update() {
        String out = connection.send("xy " + player.x + " " + player.y);

        players = gson.fromJson(out,players.getClass());
        game.repaint();
    }
}

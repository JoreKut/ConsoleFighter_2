package com.company.UI;

import com.company.MapCharacters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GraphicBattleMap extends JPanel implements ActionListener{

    private final Timer clock;

    public int WINDOW_WIDTH, WINDOW_HEIGHT;

    private boolean isBattle = false;
    private int size;

    private char[][] map;
    private final JFrame jframe = new JFrame();

    public GraphicBattleMap(int width, int height){

        this.WINDOW_WIDTH = width;
        this.WINDOW_HEIGHT = height;

        int delay = 10;
        clock = new Timer(delay, this);
        clock.start();


        jframe.setSize(width, height);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setVisible(true);
        jframe.add(this);

        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

    }

    @Override
    public void paint(Graphics g) {
        if(isBattle){
            super.paint(g);
            int cell_size = WINDOW_WIDTH / size;

            for(int i = 0; i < map.length; i++){
                for(int j = 0; j < map[0].length; j++){

                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(j*cell_size, i*cell_size, cell_size, cell_size);

                    if(map[i][j] == MapCharacters.WALL.sym) {
                        g.setColor(Color.BLACK);
                        g.fillRect(j*cell_size, i*cell_size, cell_size, cell_size);
                    }
                    else if(map[i][j] == MapCharacters.HERO.sym) {
                        g.setColor(Color.GREEN);
                        g.fillOval(j*cell_size, i*cell_size, cell_size, cell_size);
                    }
                    else if (map[i][j] != MapCharacters.SPACE.sym) {
                        g.setColor(Color.RED);
                        g.fillOval(j*cell_size, i*cell_size, cell_size, cell_size);
                    }


                }
            }
        }


    }

    public void loadMap(char[][] map){
        this.map = map;
    }

    public void loadMap(String[] map){
        this.map = new char[map.length][];
        for(int i = 0; i < map.length; i++){
            this.map[i] = map[i].toCharArray();
        }

        this.size = Math.max(map.length, map[0].length());
    }

    public void startShowing(){
        isBattle = true;
    }

    public void breakShowing(){
        isBattle = false;
        jframe.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clock.start();
        if(isBattle)
            repaint();
    }


}

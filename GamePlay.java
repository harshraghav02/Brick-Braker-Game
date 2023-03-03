package Brick_Breaker;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GamePlay extends JPanel implements ActionListener,KeyListener{
    private boolean play=false;
    private int score = 0;
    private int totalbricks = 21;
    private int delay = 8;
    private int playerX  = 310;
    private int ballXpos = 120;
    private int ballYpos = 350;
    private int ballXdir =-1;
    private int ballYdir =-2;

    private Timer Timer;
    private MapGenerator map;

    public GamePlay(){
        addKeyListener(this);
        Timer = new Timer(delay,this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
        Timer.start();
        map = new MapGenerator(3,7);
    }
    public void paint(Graphics g){
        // background
        g.setColor(Color.black);
        g.fillRect(0,0,692,592);

        // border
        g.setColor(Color.YELLOW);
        g.fillRect(0,0,692,4);
        g.fillRect(0,0,4,592);
        g.fillRect(683,3,3,592);

        // player
        g.setColor(Color.YELLOW);
        g.fillRect(playerX,550,100,10);



        // bricks
        map.draw((Graphics2D) g);

        // ball
        g.setColor(Color.RED);
        g.fillOval(ballXpos,ballYpos,20,20);

        // score
        g.setColor(Color.PINK);
        g.setFont(new Font("serif",Font.BOLD,20));
        g.drawString("Score : "+ score,550,30);


        // gameover
        if(ballYpos>570){
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.RED);
            g.setFont(new Font("arial",Font.BOLD,40));
            g.drawString("Game Over! Score: "+ score,150,300);

            g.setFont(new Font("arial",Font.BOLD,28));
            g.drawString("Press Enter to Restart!!",200,350);
        }

        // win condition
        if(totalbricks<=0){
            play = false;
            ballXdir = 0;
            ballYdir = 0;

            g.setColor(Color.RED);
            g.setFont(new Font("arial",Font.BOLD,40));
            g.drawString("You Win!! Score: "+ score,150,300);

            g.setFont(new Font("arial",Font.BOLD,28));
            g.drawString("Press Enter to Restart!!",200,350);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent a){
        if(play){
            if(ballXpos<=0){
                ballXdir = -1*ballXdir;
            }
            if(ballXpos>=670){
                ballXdir = -1*ballXdir;
            }
            if(ballYpos<=0){
                ballYdir = -1*ballYdir;
            }

            Rectangle ballrect = new Rectangle(ballXpos,ballYpos,20,20);
            Rectangle paddlerect = new Rectangle(playerX ,550,100,10);

            if(ballrect.intersects(paddlerect)) {
                ballYdir = -1*ballYdir;
            }

            A:
            for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int width = map.brickWidth;
                        int hight = map.brickHight;
                        int brickposX = 80 + j*width;
                        int brickposY = 50 + i*hight;

                        Rectangle brick = new Rectangle(brickposX,brickposY,width,hight);

                        if(ballrect.intersects(brick)){
                            map.setBrick(0,i,j);
                            totalbricks--;
                            score +=5;

                            if(ballXpos+19<=brickposX || ballXpos+1>=brickposX+width){
                                ballXdir = -1*ballXdir;
                            }
                            else ballYdir=-ballYdir;

                            break A;

                        }
                    }
                }
            }

            ballXpos += ballXdir;
            ballYpos += ballYdir;
        }
        repaint();
    }
    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX<=5) playerX=10;
            else moveleft();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX>=600) playerX=600;
            else moveright();
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                score = 0;
                totalbricks=21;
                ballXdir =-1;
                ballYdir = -2;
                ballXpos = 120;
                ballYpos = 350;
                playerX = 320;
                play = true;
                map = new MapGenerator(3,7);
            }
        }
        repaint();
    }
    @Override
    public void keyTyped(KeyEvent t){}
    @Override
    public void keyReleased(KeyEvent r){}

    public void moveleft(){
        play=true;
        playerX -= 20;
    }
    public void moveright(){
        play=true;
        playerX += 20;
    }
}
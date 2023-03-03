package Brick_Breaker;

import java.awt.*;

public class MapGenerator{
    public int map[][];
    public int brickWidth;
    public int brickHight;

    public MapGenerator(int row,int col){
        map = new int[row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                map[i][j] = 1;
            }
        }

        brickWidth = 540/col;  // width/col
        brickHight = 150/row;  // height/row
    }
    public void setBrick(int value,int r,int c){
        map[r][c] = value;
    }
    public void draw(Graphics2D g){

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]>0){
                    // drawing brick
                    g.setColor(Color.white);
                    g.fillRect(j*brickWidth+80,i*brickHight+50,brickWidth,brickHight);

                    // drawing strokes around brick
                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(3));
                    g.drawRect(j*brickWidth+80,i*brickHight+50,brickWidth,brickHight);
                }
            }
        }
    }
}
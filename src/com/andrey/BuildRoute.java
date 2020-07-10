package com.andrey;
import java.awt.*;
import java.util.LinkedList;

public class BuildRoute implements Navigator{
    public BuildRoute()
    {

    }

    @Override
    public char[][] searchRoute(char[][] map) {
        int xLength = map.length,yLenght = map[0].length;
        PointOfMap[][] mapOfSearch = new PointOfMap[xLength][yLenght];

        //Inicialization:
        for (int i = 0; i < xLength; i++)
            for (int j = 0; j < yLenght; j++)
                mapOfSearch[i][j] = new PointOfMap(i,j,false);

        //1 find entry;
        int xEntry = 0, yEntry = 0;
        boolean isFindEnter = false;
        for (int i = 0; i < xLength; i++) {
            for (int j = 0; j < yLenght; j++)
                if (map[i][j] == '@') {
                    isFindEnter = true;
                    xEntry = i;
                    yEntry = j;
                    break;
                }

                if (isFindEnter) break;
        }

        mapOfSearch[xEntry][yEntry].setPrevX(-1);
        mapOfSearch[xEntry][yEntry].setPrevY(-1);
        mapOfSearch[xEntry][yEntry].setVisited(true);

        int xExit = -1, yExit = -1;
        boolean isFind = false;

        LinkedList <PointOfMap> queue = new LinkedList<>();
        queue.addLast(mapOfSearch[xEntry][yEntry]);
        while (!queue.isEmpty())
        {
            PointOfMap currentPoint = queue.removeFirst();
            int [] xi = new int [4];
            int [] yi = new int [4];
            //1. Top
            xi[0] = currentPoint.getX() - 1;
            yi[0] = currentPoint.getY();

            //2. Left
            xi[1] = currentPoint.getX();
            yi[1] = currentPoint.getY() - 1;

            //3. Right
            xi[2] = currentPoint.getX();
            yi[2] = currentPoint.getY() + 1;

            //4. Down
            xi[3] = currentPoint.getX() + 1;
            yi[3] = currentPoint.getY();

            for (int i = 0; i < 4; i++)
            {
                if ((xi[i] >= 0 && xi[i] < xLength) && (yi[i] >= 0 && yi[i] < yLenght)
                    && (!mapOfSearch[xi[i]][yi[i]].getIsVisited()) && (map[xi[i]][yi[i]] != '#'))
                {
                    mapOfSearch[xi[i]][yi[i]].setVisited(true);
                    mapOfSearch[xi[i]][yi[i]].setPrevX(currentPoint.getX());
                    mapOfSearch[xi[i]][yi[i]].setPrevY(currentPoint.getY());

                    if (map[xi[i]][yi[i]] == 'X')
                    {
                        xExit = xi[i];
                        yExit = yi[i];
                        isFind = true;
                        break;
                    }
                    queue.addLast(mapOfSearch[xi[i]][yi[i]]);
                }
            }

            if (isFind) break;
        }

        char [][] resMatrix = null;
        if (isFind)
        {
            resMatrix = new char[xLength][yLenght];
            for (int i = 0; i < xLength; i++)
                for (int j = 0; j < yLenght; j++)
                    resMatrix[i][j] = map[i][j];

            int currentX = xExit, currentY = yExit;

            while (true)
            {
                int prevX = mapOfSearch[currentX][currentY].getPrevX();
                int prevY = mapOfSearch[currentX][currentY].getPrevY();
                if (resMatrix[prevX][prevY] == '@') break;
                else {
                    resMatrix[prevX][prevY] = '+';
                    currentX = prevX;
                    currentY = prevY;
                }
            }

        }

        return resMatrix;
    }
}

class PointOfMap {
    private int x;
    private int y;
    private int prevX;
    private int prevY;
    private boolean isVisited;

    public PointOfMap()
    {

    }

    public PointOfMap(int x, int y, boolean isVisited)
    {
        this.x = x;
        this.y = y;
        this.isVisited = isVisited;
    }

    public PointOfMap(int x, int y, int prevX, int prevY, boolean isVisited)
    {
        this.x = x;
        this.y = y;
        this.prevX = prevX;
        this.prevY = prevY;
        this.isVisited = isVisited;
    }

    public void setX(int x)
    {
       this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setPrevX(int prevX)
    {
        this.prevX = prevX;
    }

    public void setPrevY(int prevY)
    {
        this.prevY = prevY;
    }

    public void setVisited(boolean isVisited)
    {
        this.isVisited = isVisited;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getPrevX()
    {
        return this.prevX;
    }

    public int getPrevY()
    {
        return this.prevY;
    }

    public boolean getIsVisited()
    {
        return this.isVisited;
    }
}

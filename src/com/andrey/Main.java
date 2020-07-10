package com.andrey;

public class Main {

    public static void main(String[] args) {
	// write your code here
        char [][] test1 = {{'.','.','.','@','.'}, {'.','#','#','#','#'}, {'.','.','.','.','.'},
                {'#','#','#','#','.'}, {'.','X','.','.','.'}};

        char [][] test2 = {{'.','.','X','.','.'}, {'#','#','#','#','#'}, {'.','.','.','.','.'},
                {'.','@','.','.','.'},{'.','.','.','.','.'}};

        char [][] test3 = {{'.','.','.','.','@'},{'#','.','#','#','#'},{'.','.','.','.','.'},
                {'.','.','.','.','X'},{'.','.','.','.','.'}};

        printMatrix(test3);

        BuildRoute myRoute = new BuildRoute();
        char [][] res = myRoute.searchRoute(test3);

        if (res != null) {
            printMatrix(res);
        }
        else
        {
            System.out.println("Path is not exists");
        }
    }

    public static void printMatrix(char [][] matr)
    {
        for (int i = 0; i < matr.length; i++)
        {
            for (int j = 0; j < matr[0].length; j++)
                System.out.print(matr[i][j]);

            System.out.println();
        }
        System.out.println();
    }
}
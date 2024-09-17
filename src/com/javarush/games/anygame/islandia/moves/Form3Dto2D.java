package com.javarush.games.anygame.islandia.moves;

class Form3Dto2D {
    // --------------перевод массива 3D в 2D ----------------------
    public static void setkas() {

        for (int i = 0; i < AnyGame.height; i++) {
            for (int j = 0; j < AnyGame.width; j++) {

                if (!AnyGame.cubix[j][i][3].isEmpty()) {
                    AnyGame.matrix[j][i] = (((Animals) AnyGame.cubix[j][i][3].get(0)).getIcon());

                } else if (!AnyGame.cubix[j][i][2].isEmpty()) {
                    AnyGame.matrix[j][i] = (((Animals) AnyGame.cubix[j][i][2].get(0)).getIcon());

                } else if (!AnyGame.cubix[j][i][1].isEmpty()) {
                    AnyGame.matrix[j][i] = (((Animals) AnyGame.cubix[j][i][1].get(0)).getIcon());

                } else if (!AnyGame.cubix[j][i][0].isEmpty()) {
                    AnyGame.matrix[j][i] = "";
                }
            }
        }
    }
}

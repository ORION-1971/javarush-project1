package com.javarush.games.anygame.islandia.moves;

import com.javarush.games.anygame.islandia.herbivores.Enum2;
import com.javarush.games.anygame.islandia.herbivores.RabbType;
import com.javarush.games.anygame.islandia.hishniki.Enum3;
import com.javarush.games.anygame.islandia.hishniki.Predator;
import com.javarush.games.anygame.islandia.herbivores.PlantEating;
import com.javarush.games.anygame.islandia.hishniki.PredatorType;

import java.util.concurrent.ThreadLocalRandom;
import static com.javarush.games.anygame.islandia.moves.AnyGame.*;


class Evolution {

    public static void eatDead() {
        //------------перенос запасного поля в основной----------------------
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int n = 2; n < AnyGame.animal; n++) {
                    for (int k = 0; k < AnyGame.cubix2[j][i][n].size(); k++) {
                        cubix[j][i][n].add(AnyGame.cubix2[j][i][n].get(k));
                        AnyGame.cubix2[j][i][n].remove(k);                                  // удаление живности с запасной локации
                    }
                }
            }
        }

        //  ------------------съедение кроликов ----------------------------
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!cubix[j][i][2].isEmpty() && (!cubix[j][i][3].isEmpty())) {                                                   // Если в одной клетки хищник и жертва
                    if (((Predator) cubix[j][i][3].get(0)).getWeight() > ((Predator) cubix[j][i][3].get(0)).getLive()) {         // Если вес ниже уровня
                        if (ThreadLocalRandom.current().nextInt(100) <= 60) {                                             // рандом 60 % что съест
                            ((Predator) cubix[j][i][3].get(0)).setLive(((PlantEating) cubix[j][i][2].get(0)).getWeight());     // увеличение массы после ужина + вес жертвы
                            System.out.println(cubix[j][i][3].get(0).toString() + " eat " + cubix[j][i][2].get(0).toString());

                            ((Animals) cubix[j][i][2].get(0)).deadPluss();
                            cubix[j][i][2].remove(0);                                                        // удаление съеденного крола
                        }
                    }
                }
            }
        }

        //  ------------------- тут должно быть поедание мышей -------------------------



        //  ------------------съедение травы ----------------------------
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //if (!(cubix[j][i][1].get(0).getClass().equals(Mouse.class))){
                if (!cubix[j][i][1].isEmpty() && (!cubix[j][i][2].isEmpty())) {
                    if (((PlantEating) cubix[j][i][2].get(0)).getWeight() >= ((PlantEating) cubix[j][i][2].get(0)).getLive()) {        // Если вес ниже уровня
                        ((PlantEating) cubix[j][i][2].get(0)).setLive(1);                                            // увеличение жизни после обеда = трава = 1
                        herbDead++;
                        cubix[j][i][1].remove(0);                                                               // удаление травы
                    }
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cubix[j][i][2].size() > 1) {                                                                         // Размножение кроликов

                    for (Enum2 types : Enum2.values()) {
                        if (cubix[j][i][2].get(0).toString().equals(cubix[j][i][2].get(1).toString())) {
                            if (cubix[j][i][2].get(0).getClass().getName().toUpperCase().lastIndexOf(types.toString()) > 0) {
                                if (ThreadLocalRandom.current().nextInt(10) == 3) {                                // шанс 1 из 10, что родит потомство
                                    cubix[ThreadLocalRandom.current().nextInt(width)][ThreadLocalRandom.current().nextInt(height)][2].add(new RabbType().creats(types));
                                    System.out.println("                  " + cubix[j][i][2].get(0).toString() + "sex" + cubix[j][i][2].get(1).toString());

                                    ((PlantEating) cubix[j][i][2].get(0)).livePluss();                                      // добавление нового крола в статистику
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (cubix[j][i][3].size() > 1) {                                                                       // Секс волков

                    for (Enum3 types : Enum3.values()) {
                        if (cubix[j][i][3].get(0).toString().equals(cubix[j][i][3].get(1).toString())) {

                            if (cubix[j][i][3].get(0).getClass().getName().toUpperCase().lastIndexOf(types.toString()) > 0) {
                                if (ThreadLocalRandom.current().nextInt(10) == 3) {                                // шанс 1 из 10, что родит потомство
                                   // double aa = ((Predator) cubix[j][i][3].get(0)).getLive();
                                   // if (aa > 10 && aa < 40) {                                                           // если у волчицы течка
                                        cubix[ThreadLocalRandom.current().nextInt(width)][ThreadLocalRandom.current().nextInt(height)][3].add(new PredatorType().creats(types));
                                        System.out.println("                  " + cubix[j][i][3].get(0).toString() + "sex" + cubix[j][i][3].get(1).toString());                       // зародился новый волк
                                        ((Predator) cubix[j][i][3].get(0)).livePluss();                                      // добавление нового волка в статистику
                                    //}
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



package com.javarush.games.anygame.islandia.moves;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.games.anygame.islandia.herbivores.Enum2;
import com.javarush.games.anygame.islandia.herbivores.PlantEating;
import com.javarush.games.anygame.islandia.herbivores.RabbType;
import com.javarush.games.anygame.islandia.mouse.MouseType;
import com.javarush.games.anygame.islandia.mouse.Rats;
import com.javarush.games.anygame.islandia.hishniki.Enum3;
import com.javarush.games.anygame.islandia.hishniki.Predator;
import com.javarush.games.anygame.islandia.hishniki.PredatorType;
import com.javarush.games.anygame.islandia.plantacia.*;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/* 
Любая игра
*/
public class AnyGame extends Game {
    // ======================= НАСТРОЙКИ ИГРЫ ================================
    public static int speed = 200;                  // скорость игры
    public static int width = 30;                   // ширина поля
    public static int height = 30;                  // высота поля

    public static int herbar = 1000;                 // количество растений раз в минуту прирост
    public static int plantEatings = 3;  // 5 из 10 от нормы таблицы количество травоядных
    public static int predators = 3;     // 5 из 10 от нормы таблицы количество хищников
    public static int mouses = 2;        // 5 из 10 от нормы таблицы количество мышей
    public static int hungrySpeed = 5;   // 5 оптимал как долго живет без еды
    // =======================================================================



    // ХХХХХХХХХХХХХХХХХХХХХХХ НИЖЕ ДЕЛАТЬ НЕЧЕГО ХХХХХХХХХХХХХХХХХХХХХХХХХХХХ
    static int day = 0;                              // счетчик дней
    public static int animal = 5;                    // количество типов животных
    Color color = Color.BLACK;                       // цвет иконки
    int textSize = 95;                               // размер иконки
    static boolean wolfSex = true;                   // спавн волков
    static boolean rabbitSex = true;                 // спавн кроликов
    static boolean mouseSex = true;                 // спавн мышей
    static boolean herbSex = true;                   // спавн растений
    public static int herbDead;
    public static int herbLive;

    static int rabbitEat = 0;
    static int rabbitPlus = 0;
    static int wolfPlus = 0;

    public static ArrayList[][][] cubix = new ArrayList[width][height][animal];                     // Создание глобального 3D массива
    public static ArrayList[][][] cubix2 = new ArrayList[width][height][animal];                    // Создание запасного 3D массива
    public static String[][] matrix = new String[width][height];                                    // Создание глобального 2D массива

   /* public class Stremchanin implements Runnable {
        @Override
        public void run() {
            //AnyGame anyGame = new AnyGame();
            while (rabbitLive != 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                day++;
                drawer();
            }

        }
    }*/

    @Override
    public void initialize() {                          // 🐃, 🐻, 🐎, 🦌, 🐗, 🐑, 🐐,🐺 , 🐍, 🦊, 🦅, 🐇, 🦆, 🐁, 🐛, 🍀, ⬜️
        setScreenSize(width, height);                                                          //размер поля
        showGrid(false);                                                                // сетка

        //potoc();
        setTurnTimer(speed);                                                                    //Включаем таймер, интервал между вызовами – 500мс.
        cubatrix();                                                                             //  заполняет массив данными
    }

    // ------------------------таймер-------------------------------------
    @Override
    public void onTurn(int step) {
        drawer();
        day++;
    }
    // ----------------- МНОГОПОТОЧКА ------------------------------ пока рассинхрон идет
   /* public void potoc() {
        Stremchanin stremchanin = new Stremchanin();
        Thread sss = new Thread(stremchanin);
        Thread sss1 = new Thread(stremchanin);
        sss.start();
        try {
            sss.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        sss1.start();
        if(wolfLive == 0) sss.interrupt();
        if(rabbitLive == 0) sss1.interrupt();
    }*/


    //-------------------------- логика жизни--------------------------------------
    public void drawer() {
        setka();
        setCellValueEx(width - 1, 0, Color.WHITE, day + "", Color.BLACK, 40);                        // сколько дней прошло
        //if (day % 5 == 0) System.out.print("\n" +  day + " день  ");

        if (herbSex || day % 30 == 0) {                                                                                 // трава появляется раз в 30 тактов
            //if (herbLive - herbDead < 500) {
                for (int i = 0; i < herbar; i++) {                        // засев расстений
                    for (Enum1 types : Enum1.values()) {
                        PlantType plantType = new PlantType();
                        Plant plant = plantType.creats(types);

                        cubix[ThreadLocalRandom.current().nextInt(width - 1)][ThreadLocalRandom.current().nextInt(height - 1)][1].add(plant);
                    }
                    herbLive++;
                }
            //}
            herbSex = false;
        }

        if (rabbitSex) {                                           // заброс на локацию живности
            for (Enum2 types : Enum2.values()) {
                PlantEating plantEating = new RabbType().creats(types);
                for (int i = 0; i < plantEatings * plantEating.getMax() / 10; i++) {
                    cubix[ThreadLocalRandom.current().nextInt(width)][ThreadLocalRandom.current().nextInt(height)][2].add(new RabbType().creats(types));
                    plantEating.livePluss();
                }
                rabbitSex = false;
            }
        }

        if (wolfSex) {
            for (Enum3 types : Enum3.values()) {                                           // спавн хищников
                Predator predator = new PredatorType().creats(types);
                for (int i = 0; i < predators * predator.getMax() / 10; i++) {
                    cubix[ThreadLocalRandom.current().nextInt(width)][ThreadLocalRandom.current().nextInt(height)][3].add(new PredatorType().creats(types));
                    predator.livePluss();
                }
                wolfSex = false;
            }
        }

//        if (mouseSex) {
//            for (Enum4 types : Enum4.values()) {                                // зародился новый мышь
//                Rats rats = new MouseType().creats(types);
//                for (int i = 0; i < mouses * rats.getMax() / 10; i++) {
//                    cubix[ThreadLocalRandom.current().nextInt(width)][ThreadLocalRandom.current().nextInt(height)][3].add(new MouseType().creats(types));
//                    rats.livePluss();
//                }
//                wolfSex = false;
//            }
//        }


        // --------------------  ИНФО ПАНЕЛЬ --------------------------------------
        setCellValueEx(width - 1, 2, Color.WHITE, "🌿", Color.GREEN, 95);                    // статистика жизни - смерти
        setCellValueEx(width - 1, 3, Color.WHITE, herbLive - herbDead + "", Color.BLACK, 40);
        setCellValueEx(width - 1, 4, Color.WHITE, herbDead + "", Color.RED, 40);


        setCellValueEx(width - 1, 6, Color.WHITE, "🐻", Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(width - 1, 7, Color.WHITE, PredatorType.bearLive - PredatorType.bearDead + "", Color.BLACK, 60);
        setCellValueEx(width - 1, 8, Color.WHITE, PredatorType.bearDead + "", Color.RED, 60);

        setCellValueEx(width - 1, 10, Color.WHITE, "🐺", Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(width - 1, 11, Color.WHITE, PredatorType.wolfLive - PredatorType.wolfDead + "", Color.BLACK, 60);
        setCellValueEx(width - 1, 12, Color.WHITE, PredatorType.wolfDead + "", Color.RED, 60);

        setCellValueEx(width - 1, 14, Color.WHITE, "🦊", Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(width - 1, 15, Color.WHITE, PredatorType.foxLive - PredatorType.foxDead + "", Color.BLACK, 60);
        setCellValueEx(width - 1, 16, Color.WHITE, PredatorType.foxDead + "", Color.RED, 60);

        setCellValueEx(width - 1, 18, Color.WHITE, "🐍", Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(width - 1, 19, Color.WHITE, PredatorType.boaLive - PredatorType.boaDead + "", Color.BLACK, 60);
        setCellValueEx(width - 1, 20, Color.WHITE, PredatorType.boaDead + "", Color.RED, 60);

        setCellValueEx(width - 1, 22, Color.WHITE, "🦅", Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(width - 1, 23, Color.WHITE, PredatorType.eagleLive - PredatorType.eagleDead + "", Color.BLACK, 60);
        setCellValueEx(width - 1, 24, Color.WHITE, PredatorType.eagleDead + "", Color.RED, 60);

        setCellValueEx(width - 1, 27, Color.WHITE, "🦆", Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(width - 1, 28, Color.WHITE, RabbType.duckLive - RabbType.duckDead + "", Color.BLACK, 60);
        setCellValueEx(width - 1, 29, Color.WHITE, RabbType.duckDead + "", Color.RED, 60);

//------------------------------------------------------------------------------------------------------------------------
        setCellValueEx(0, height - 1, Color.WHITE, "🐇", Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(1, height - 1, Color.WHITE, RabbType.rabbitLive - RabbType.rabbitDead + "", Color.BLACK, 60);
        setCellValueEx(2, height - 1, Color.WHITE, RabbType.rabbitDead + "", Color.RED, 60);

        setCellValueEx(4, height - 1, Color.WHITE, "🐑", Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(5, height - 1, Color.WHITE, RabbType.sheepLive - RabbType.sheepDead + "", Color.BLACK, 60);
        setCellValueEx(6, height - 1, Color.WHITE, RabbType.sheepDead + "", Color.RED, 60);

        setCellValueEx(8, height - 1, Color.WHITE, "🐐" , Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(9, height - 1, Color.WHITE, RabbType.goatLive - RabbType.goatDead + "", Color.BLACK, 60);
        setCellValueEx(10, height - 1, Color.WHITE, RabbType.goatDead + "", Color.RED, 60);

        setCellValueEx(12, height - 1, Color.WHITE, "🐖" , Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(13, height - 1, Color.WHITE, RabbType.boarLive - RabbType.boarDead + "", Color.BLACK, 60);
        setCellValueEx(14, height - 1, Color.WHITE, RabbType.boarDead + "", Color.RED, 60);

        setCellValueEx(16, height - 1, Color.WHITE, "🦌" , Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(17, height - 1, Color.WHITE, RabbType.deerLive - RabbType.deerDead + "", Color.BLACK, 60);
        setCellValueEx(18, height - 1, Color.WHITE, RabbType.deerDead + "", Color.RED, 60);

        setCellValueEx(20, height - 1, Color.WHITE, "🐎" , Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(21, height - 1, Color.WHITE, RabbType.horseLive - RabbType.horseDead + "", Color.BLACK, 60);
        setCellValueEx(22, height - 1, Color.WHITE, RabbType.horseDead + "", Color.RED, 60);

        setCellValueEx(24, height - 1, Color.WHITE, "🐮" , Color.RED, 95);                      // статистика жизни - смерти
        setCellValueEx(25, height - 1, Color.WHITE, RabbType.buffaloLive - RabbType.buffaloDead + "", Color.BLACK, 60);
        setCellValueEx(26, height - 1, Color.WHITE, RabbType.buffaloDead + "", Color.RED, 60);
    }


    // ---------------- КУБИК РУБИКА -------------------------   // 🐃, 🐻, 🐎, 🦌, 🐗, 🐑, 🐐, 🐺 , 🐍, 🦊, 🦅, 🐇, 🦆, 🐁, 🐛, 🍀, ⬜️

    public void cubatrix() {


        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                cubix[j][i][0] = new ArrayList<Cub>();                                                              // создание  поля с параметром иконки  🟫    ⬜   ⬛
                cubix[j][i][0].add(new ArrayList<Cub>());

                cubix[j][i][1] = new ArrayList<Plant>();                                                                 // 🍀  🌿   🌾☘️
                cubix2[j][i][1] = new ArrayList<Plant>();

                cubix[j][i][2] = new ArrayList<PlantEating>();                                                           // 🐇
                cubix2[j][i][2] = new ArrayList<PlantEating>();

                cubix[j][i][3] = new ArrayList<Predator>();                                                              // 🐺
                cubix2[j][i][3] = new ArrayList<Predator>();

                cubix[j][i][4] = new ArrayList<Rats>();                                                                  // 🐁
                cubix2[j][i][4] = new ArrayList<Rats>();
            }
        }
    }

    // ---------------логика передвижения и голодной смерти --------------------
    public void setka() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                for (int n = 1; n < animal; n++) {

                    if (!cubix[j][i][n].isEmpty()) {                                                                    //  проверка на наличие Живности
                        for (int k = 0; k < cubix[j][i][n].size(); k++) {                                               //  количество тварей на одном месте
                            //System.out.println(((Animals) cubix[j][i][n].get(k)) + "  " + ((Animals) cubix[j][i][n].get(k)).hashCode()  + "  "+((Animals) cubix[j][i][n].get(k)).getLive());
                            ((Animals) cubix[j][i][n].get(k)).setLive();                                                //забирает кусочек здоровья

                            if (!(cubix[j][i][n].get(k).getClass().equals(Koko.class)) && !(cubix[j][i][n].get(k).getClass().equals(Herb.class))) {                               // исключить движение растений
                                    //System.out.println(((Animals) cubix[j][i][n].get(k)) + "  " +((Animals) cubix[j][i][n].get(k)).hashCode()  + "  "+((Animals) cubix[j][i][n].get(k)).getLive());

                                    if (((Animals) cubix[j][i][n].get(k)).getLive() == 10200) {                             // груз двести

                                    ((Animals) cubix[j][i][n].get(k)).talk();                                           // сообщение о смерти
                                    ((Animals) cubix[j][i][n].get(k)).deadPluss();


                                    cubix[j][i][n].remove(k);                                                          // удаление голодного

                                    //-------------------------- куда бежать - то? ---------------------------------------------------
                                } else {
                                    int speed = ThreadLocalRandom.current().nextInt(((Animals) cubix[j][i][n].get(k)).getSpeed()) + 1;
                                    //System.out.println(speed);
                                    // скорость передвижения (требуется переделка системы)
                                    if (day % 4 / speed == 0) {

                                        int z = ThreadLocalRandom.current().nextInt(5);
                                        switch (z) {
                                            case 0, 5:
                                                cubix2[j][i][n].add(cubix[j][i][n].get(k));                                 // стоять на месте
                                                break;
                                            case 1:                                                                         // вправо если не яма
                                                if (j > width - 3) cubix2[j][i][n].add(cubix[j][i][n].get(k));
                                                else cubix2[j + 1][i][n].add(cubix[j][i][n].get(k));
                                                break;
                                            case 2:
                                                if (j < 2) cubix2[j][i][n].add(cubix[j][i][n].get(k));
                                                else cubix2[j - 1][i][n].add(cubix[j][i][n].get(k));
                                                break;
                                            case 3:
                                                if (i > height - 3) cubix2[j][i][n].add(cubix[j][i][n].get(k));
                                                else cubix2[j][i + 1][n].add(cubix[j][i][n].get(k));
                                                break;
                                            case 4:
                                                if (i < 2) cubix2[j][i][n].add(cubix[j][i][n].get(k));
                                                else cubix2[j][i - 1][n].add(cubix[j][i][n].get(k));
                                                break;
                                        }
                                        cubix[j][i][n].remove(k);                                                           // удаление живности с основной локации
                                    }
                                }
                           }
                        }
                    }
                }
            }
        }
        Evolution.eatDead();          //------------перенос запасного поля в основной----------------------

        Form3Dto2D.setkas();          // --------------перевод массива 3D в 2D ----------------------

        draw();                       //  --------- Рисует 2D поле ---------------------
    }


    public void draw() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String icona = matrix[j][i];
                switch (icona) {
                    case "🐺": color = Color.BROWN; textSize = 75; break;
                    case "🐻": color = Color.BROWN; textSize = 95; break;
                    case "🦊": color = Color.BROWN; textSize = 70; break;
                    case "🐍": color = Color.BROWN; textSize = 85; break;
                    case "🦅": color = Color.BROWN; textSize = 80; break;
                    case "🐇": color = Color.BLACK; textSize = 50; break;
                    case "🐐", "🐑" : color = Color.BLACK; textSize = 70; break;
                    case "🐖" : color = Color.BLACK; textSize = 80; break;
                    case "🐎", "🦌", "🐮" : color = Color.BLACK; textSize = 95; break;
                    case "🦆", "🐓" : color = Color.AZURE; textSize = 40; break;
                    case "🐭" : color = Color.CHOCOLATE; textSize = 30; break;
                    case "☘️": color = Color.GREEN; textSize = 40; break;
                    case "🌿": color = Color.GREEN; textSize = 50;break;

                }
                setCellValueEx(j, i, Color.DARKSEAGREEN, matrix[j][i], color, textSize);

            }
        }
    }

    public class Cub {
    }
}






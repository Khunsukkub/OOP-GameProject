package com.example.kombat.oopprojectapi.model;

import java.util.Arrays;
import java.util.Scanner;

public class MinionSetting {
    public static void design(int minionNumber) {
        System.out.println("--------------------------------------");
        Scanner sc = new Scanner(System.in);
        String minionList[] = {"GuGu", "GaGa", "Simba", "Kuku", "Pupo"};
        Minion MinionList[] = new Minion[minionNumber];
        String minionName = "";

        for (int i = 0; i < minionNumber; i++) {
            System.out.println("--------------------------------------");
            System.out.println("GuGu GaGa Simba Kuku Pupo");
            boolean isValidMinion = false;

            while (!isValidMinion) {
                System.out.print("Minion: ");
                String inputMinionName = sc.nextLine();

                // ตรวจสอบชื่อมินเนี่ยน
                for (int j = 0; j < minionList.length; j++) {
                    if (minionList[j].equals(inputMinionName)) {
                        isValidMinion = true;
                        minionName = inputMinionName;
                        break;
                    }
                }

                if (!isValidMinion) {
                    System.out.println("Invalid minion. Please try again.");
                }
            }
            System.out.print("");

            System.out.print("Initial HP : ");
            double initialHP = sc.nextDouble();
            sc.nextLine();

            System.out.print("Defence Factor: ");
            double defenceFactor = sc.nextDouble();
            sc.nextLine();

            System.out.print("Spawn cost (>0): ");
            int spawn_cost = sc.nextInt();
            sc.nextLine();

            System.out.print("Strategy: ");
            String strategy = sc.nextLine();

            Minion chosenMinion = new Minion(minionName, initialHP, defenceFactor, spawn_cost , strategy);
            MinionList[i] = chosenMinion;
        }

        MainGame.getInstance();
        MainGame.minionList = MinionList;
        System.out.println("--------------------------------------");
    }

}

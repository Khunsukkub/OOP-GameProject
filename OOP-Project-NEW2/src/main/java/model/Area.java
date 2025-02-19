package model;

import java.util.Map;
import java.util.Set;

public class Area {
    private static Area instance;
    Hex hexFloor;
//    int[] init_player1Area = {0, 1, 2, 8, 9, 16};
int[] init_player1Area = {0,2,3};
    int[] init_player2Area = {47, 54, 55, 61, 62, 63};

    public static Area getInstance() {
        if (instance == null) {
            instance = new Area();
        }
        return instance;
    }

    public void getDefaultMap(Player[] players) {
        int Number = 0;
        for(int i = 0; i < 8; i++) {
            for (int j = 0 ; j < 8 ; j++) {
                hexFloor = new Hex(i,j);
                for(int k = 0; k < init_player1Area.length; k++) {
                    if(init_player1Area[k] == Number) {
                        System.out.println(init_player1Area[k] + " " + Number);
                        Hex.setOwner(players[0]);
                    }

                }
                hexFloor = null;
//                for(int l = 0; l < init_player2Area.length; l++) {
//                    if(init_player2Area[l] == Number) hexFloor.setOwner(players[1]);
//                }
                Number++;
                System.out.println("Hex no." +Number + " " + Hex.getOwner());
            }
        }
    }

}

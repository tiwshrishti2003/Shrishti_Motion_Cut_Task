package com.gzm;

//import com.gamz.Artifact;
//import com.gamz.Asura;
//import com.gamz.Chamber;
//import com.gamz.Competitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AdventureArena {
    private static final String[] iconImage = {
            "Rama", "Shiva", "Krishna", "Laxman", "Hanuman", "Karna", "Ashwatdhama", "Abhimanyu", "Parsuram"
    };
    public static void main(String[] args) {
        AdventureArena.prc_arena();
    }
    private static final int NUM_HALLS = 6;
    public static void disp_preamble(int postn) {
        System.out.println("Welcome to Mystery Palace - IndraAsanam!\n");
        System.out.println("Greetings Competitor with IconImage: *** " + String.format("%s",AdventureArena.iconImage[postn]) + " *** ");
        System.out.println("\nYou are entering the Palace of King Indra. He is a noble and courageous warrier.");
        System.out.println("The kingdom has been conquered by cruel Asur Ravanandam.");
        System.out.println("Your quest is to navigate the palace...");
        System.out.println("...and defeat the different mighty Asuras that rome the Palace Halls.");
        System.out.println("Once done you will meet the final Asur King Ravanandam.");
        System.out.println("You will be the winner if you defeat the conqueror Asur Ravanandam.");
        System.out.println("Good Luck!!!");
        System.out.println("Type 'HELP/OM' for instructions.");
    }
    public static void prc_arena() {
        Scanner scanner = new Scanner(System.in);
        //Random rnd = new Random();
        int postn = (int)(Math.random() * (AdventureArena.iconImage.length));
        Competitor competitor = new Competitor(AdventureArena.iconImage[postn]);
        AdventureArena.disp_preamble(postn);

        Chamber currentChamber = createArenaPalace();

        while (competitor.getStamina() > 0) {
            System.out.println("\nCurrent Chamber: " + currentChamber.getName());
            System.out.println(currentChamber.getDesc());

            if (currentChamber.getAsura() != null) {
                Asura asura = currentChamber.getAsura();
                System.out.println("Asura " + asura.getName() + " magically appears!");
                AdventureArena.engageFight(competitor, asura);
                currentChamber.setAsura(null);
            }

            if (currentChamber.getArtifact() != null) {
                Artifact artifact = currentChamber.getArtifact();
                System.out.println("You found *** " + artifact.getName() + " ***. It increases your stamina by " + artifact.getEnergyInc() +
                        " and drains your stamina by " + artifact.getEnergyDec() + ".");
                competitor.takeHit(-artifact.getEnergyInc());
                competitor.takeHit(-artifact.getEnergyDec());
                currentChamber.setArtifact(null);
            }
            if (currentChamber.getAsura() == null &&
                    currentChamber.getArtifact() == null &&
                    currentChamber.getName().contains("Treasure"))
            {
                System.out.println("\nGreatest Warrior *** " + String.format("%s",AdventureArena.iconImage[postn]) +
                        " *** , you have defeated Asur Ravanandam and freed IndraAsanam");
                System.out.println("The Kingdom will forever be debted to you.");
                System.out.println("You are the winner. Please collect your Treasure");
                System.out.println("We hope you liked the Adventure Game.");
                System.out.print("Mystery Palace Adventure Game will end in few moments...");
                for (int i=0;i<9;i++) {
                    try {
                        Thread.sleep(300);
                        System.out.print(".");
                    } catch (InterruptedException ex) {
                    }
                }
                System.exit(4);
            }
            System.out.print("Where do you want to go? (F, L, B, R): ");
            String input = scanner.next().toUpperCase();

            if (input.equals("HELP") || input.equals("OM")) {
                System.out.println("Commands: 'F' (Front), 'L' (Left), 'B' (Back), 'R' (Right), 'X' (eXit)");
            } else if (input.equals("X")) {
                System.out.println("Hope you enjoyed the adventure game. Exiting game");
                break;
            } else if (input.equals("F") || input.equals("L") || input.equals("B") || input.equals("R")) {
                int dirx = "FLBR".indexOf(input);
                Chamber nextChamber = currentChamber.get_exit(dirx);
                if (nextChamber != null) {
                    currentChamber = nextChamber;
                    //System.out.println(nextChamber.toString());
                } else {
                    System.out.println("No exit available.");
                }
            } else {
                System.out.println("Invalid input. Please Type 'HELP/OM' for assistance.");
            }
        }

        System.out.println("Game Over " + String.format("%s",AdventureArena.iconImage[postn]) + ". Your Mystical adventure comes to an end.");
        scanner.close();

    }

    public static void engageFight(Competitor competitor, Asura asura) {

        while (competitor.getStamina() > 0 && asura.getStamina() > 0) {
            System.out.println(competitor.getName() + " Stamina: " + competitor.getStamina() + " | Asura Stamina: " + asura.getStamina());
            int cmpHit = competitor.getHit();
            int asurHit = asura.getHit();
            competitor.takeHit(asurHit);
            asura.takeHit(cmpHit);
        }

        if (competitor.getHit() <= 0) {
            System.out.println("You were defeated by the " + asura.getName() + ". Game Over!");
            System.exit(0);
        } else {
            System.out.println("You defeated Asura *** " + asura.getName() + " *** and have become a mighty warrior.\n");
        }
    }
    private static List<String>[] get_file_data()
    {
        final String[] fnames = {
                "chamber.csv","asura.csv","artifact.csv"
        };
        //Util utx = new Util();
        List<String>[] lst_fl = new List[fnames.length];
        for (int ix=0;ix < fnames.length;ix++) {
            try {
                lst_fl[ix] = Util.readFromFile(fnames[ix]);
                if (ix == 2)
                {
                    lst_fl[ix] = Util.shuffle_data(lst_fl[ix]);
                } else if (ix == 1)
                {
                    lst_fl[ix] = Util.shuffle_data_ignore_endpoints(lst_fl[ix]);
                } else
                {
                    lst_fl[ix] = Util.shuffle_data_ignore_first_last_endpoints(lst_fl[ix]);
                }
            } catch (IOException ex) {
                System.out.println("Error reading file");
            }
        }
        return lst_fl;
    }

    private static void disp_data(List<String>[] gx)
    {
        List<String> fc;
        for (int ix=0;ix < gx.length;ix++)
        {
            fc = gx[ix];
            //System.out.println("Disp " + ix);
            for (String mx:fc)
            {
                //System.out.println(mx);
            }
            //System.out.println("End Disp " + ix);
        }

    }
    private static List<Integer> get_uniq_num(int rng)
    {
        List<Integer> numx = new ArrayList<>();
        for (int i = 0; i < rng; i++) {
            numx.add(i);
        }
        Collections.shuffle(numx);
        return numx;
    }
    public static Chamber createArenaPalace() {
        List<String>[] lst_fl = AdventureArena.get_file_data();
        //AdventureArena.disp_data(lst_fl);
        List<String> hx = null;
        hx = lst_fl[0];
        String[] lst_chmb = null;
        Chamber[] chm_ary = new Chamber[AdventureArena.NUM_HALLS];
        for(int ix = 0 ; ix < AdventureArena.NUM_HALLS;ix++)
        {
            lst_chmb = hx.get(ix).split("#");
            //Chamber grh = new Chamber("GreatHall", "You are in the safe GreatHall room. There are paths leading in all directions.");
            chm_ary[ix] = new Chamber(lst_chmb[0],lst_chmb[1]);
        }
        //Ensure Treasure hall is the last chamber
        lst_chmb = hx.get(hx.size()-1).split("#");
        chm_ary[chm_ary.length-1] = new Chamber(lst_chmb[0],lst_chmb[1]);

        Asura[] asu_ary = new Asura[AdventureArena.NUM_HALLS];
        asu_ary[0] = null; //Nothing placed in the 1st Hall
        hx = lst_fl[1];
        String[] lst_asu = null;
        for(int ix = 1 ; ix < AdventureArena.NUM_HALLS;ix++)
        {
            lst_asu = hx.get(ix).split("#");
            asu_ary[ix] = new Asura(lst_asu[0]);
            asu_ary[ix].takeHit(Integer.parseInt(lst_asu[1]));
            chm_ary[ix].setAsura(asu_ary[ix]);
        }
        //Ensure Ravandam is in the last chamber
        lst_asu = hx.get(hx.size()-1).split("#");
        asu_ary[chm_ary.length-1] = new Asura(lst_asu[0]);
        asu_ary[chm_ary.length-1].takeHit(Integer.parseInt(lst_asu[1]));
        chm_ary[chm_ary.length-1].setAsura(asu_ary[chm_ary.length-1]);

        Artifact[] art_ary = new Artifact[AdventureArena.NUM_HALLS];
        art_ary[0] = null; //Nothing placed in the 1st Hall
        hx = lst_fl[2];
        String[] lst_art = null;
        for(int ix = 1 ; ix < AdventureArena.NUM_HALLS;ix++)
        {
            lst_art = hx.get(ix).split("#");
            art_ary[ix] = new Artifact(lst_art[0],Integer.parseInt(lst_art[1]),Integer.parseInt(lst_art[2]));
            chm_ary[ix].setArtifact(art_ary[ix]);
        }
        /*
        Chamber.createExitPoints(chm_ary[0], null, chm_ary[1], null, null);
        Chamber.createExitPoints(chm_ary[1], chm_ary[0], chm_ary[2], null, null);
        Chamber.createExitPoints(chm_ary[2], null, chm_ary[1], null, chm_ary[3]);
        Chamber.createExitPoints(chm_ary[3], null, null, chm_ary[4], chm_ary[2]);
        Chamber.createExitPoints(chm_ary[4], null, null, null, null);
         */
        List<Integer> unq_num = null;
        for (int ix=0;ix<AdventureArena.NUM_HALLS - 1;ix++)
        {
            unq_num = AdventureArena.get_uniq_num(4);
            System.out.println("Shuffled number: " + unq_num.get(0));
            if (ix != 0) {
                switch (unq_num.get(0))
                {
                    case 0: {
                        Chamber.createExitPoints(chm_ary[ix], chm_ary[ix + 1], chm_ary[ix - 1], null, null);
                        break;
                    }
                    case 1: {
                        Chamber.createExitPoints(chm_ary[ix], null,chm_ary[ix + 1], chm_ary[ix - 1], null);
                        break;
                    }
                    case 2: {
                        Chamber.createExitPoints(chm_ary[ix], null,null,chm_ary[ix + 1], chm_ary[ix - 1]);
                        break;
                    }
                    case 3: {
                        Chamber.createExitPoints(chm_ary[ix], chm_ary[ix - 1],null,null,chm_ary[ix + 1]);
                        break;
                    }
                    default:
                    {
                        Chamber.createExitPoints(chm_ary[ix], chm_ary[ix + 1], chm_ary[ix - 1], null, null);
                    }
                }
            }
            else
            {
                switch (unq_num.get(0))
                {
                    case 0: {
                        Chamber.createExitPoints(chm_ary[ix], chm_ary[ix + 1], null, null, null);
                        break;
                    }
                    case 1: {
                        Chamber.createExitPoints(chm_ary[ix], null,chm_ary[ix + 1], null, null);
                        break;
                    }
                    case 2: {
                        Chamber.createExitPoints(chm_ary[ix], null,null,chm_ary[ix + 1], null);
                        break;
                    }
                    case 3: {
                        Chamber.createExitPoints(chm_ary[ix], null,null,null,chm_ary[ix + 1]);
                        break;
                    }
                    default:
                    {
                        Chamber.createExitPoints(chm_ary[ix], chm_ary[ix + 1], null, null, null);
                    }
                }
            }
        }
        Chamber.createExitPoints(chm_ary[AdventureArena.NUM_HALLS - 1], null, null, null, null);
        return chm_ary[0];
    }
}

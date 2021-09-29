package S191220160.Task2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Scene {

    public static void main(String[] args) throws IOException {



        Line line = new Line(64);
        
        boolean[] used=new boolean[64];
        for(boolean number:used){
            number=false;
        }
        Random r = new Random();
        int random;

        for(int i=0;i<64;i++){
            //generate random number
            random=r.nextInt(64);
            while(used[random])
                random=(random+1)%64;
            used[random]=true;    
            line.put(Monster.theMonsters[i],random);
        }

        Snake theSnake = Snake.getTheSnake();

        Sorter sorter = new SelectSorter();

        theSnake.setSorter(sorter);

        String log = theSnake.lineUp(line);

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("result.txt"));
        writer.write(log);
        writer.flush();
        writer.close();

    }

}

package S191220160.Task3;

import S191220160.Task3.Line.Position;

public class  Monster implements Linable{
    private final int r;
    private final int g;
    private final int b;
    private final int rank;

    private Position position;
    public static Monster[] theMonsters; 

    static{
        
        int red,green,blue;
        if(theMonsters==null){
            theMonsters= new Monster[64];
            for(int i=0;i<64;i++){
                red=114;
                green=i;
                blue=100;
                theMonsters[i]=new Monster(red,green,blue,i);           
            }
        }
    } 


    Monster(int r, int g, int b,int rank) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.rank=rank;
    }

    public static Monster getMonsterByRank(int rank){
        for(int i=0;i<64;i++)
            if(theMonsters[i].rank==rank)
                return theMonsters[i];
        return null;
    }


    @Override
    public String toString() {
        return "\033[48;2;" + this.r + ";" + this.g + ";" + this.b + ";38;2;0;0;0m    " + this.rank + "  \033[0m";
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    public void swapPosition(Monster another) {
        Position p = another.position;
        this.position.setLinable(another);
        p.setLinable(this);
    }

    @Override
    public int getValue() {
        return this.rank;
    }
}

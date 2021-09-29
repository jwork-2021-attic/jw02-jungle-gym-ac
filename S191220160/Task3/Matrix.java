package S191220160.Task3;

public class Matrix {
    private Line[] matrix;
    private int lineLength;

    public Matrix(int length,int width){
        matrix=new Line[length];
        lineLength=width;
        //this one doesn't work,because line is null
        /*for (Line line:matrix){
            line=new Line(width);
        }*/
        for(int i=0;i<length;i++)
            matrix[i]=new Line(lineLength);
    }

    public void put(Linable linable, int i) {
        if ((i < 0) || (i >= matrix.length*lineLength)) {
            return ;
        } 
        else {
            matrix[i/lineLength].put(linable,i%lineLength);
        }
    }

    public Linable get(int i) {
        if ((i < 0) || (i >= matrix.length*lineLength)) {
            return null;
        } 
        else {
            return matrix[i/lineLength].get(i%lineLength);
        }
    }



    public Linable[] toArray() {
        Linable[] linables = new Linable[matrix.length*lineLength];
        Linable[] line_linables=new Linable[lineLength];
        for (int i = 0; i < matrix.length; i++) {
            line_linables=matrix[i].toArray();
            for(int j=0;j<lineLength;j++){
                linables[i*lineLength+j]=line_linables[j];
            }
        }
        return linables;
    }

    @Override
    public String toString() {
        String matrixString = "";
        for (Line line : matrix) {
            matrixString += line.toString();
            matrixString +="\n";
        }
        return matrixString;
    }
}

package S191220160.Task2;

public class SelectSorter implements Sorter{

    private int[] a;
    private String plan = "";
    
    private void swap(int i, int j) {
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    @Override
    public void load(int[] a) {
        this.a = a;
    }
    @Override
    public void sort() {
        int max;
        for (int i = 0; i < a.length - 1; i++) {
            max=0;
            for(int j=1;j<a.length-i;j++){
                if(a[j]>a[max])max=j;
            }
            swap(max,a.length-1-i);
        }
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}

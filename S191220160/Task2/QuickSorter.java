package S191220160.Task2;

public class QuickSorter implements Sorter{

    private int[] a;
    private String plan = "";
    
    private void swap(int i, int j) {
        int temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private void quickSort(int left,int right){
        if(left>=right)return;
        int pivot=a[left];
        int j=left;
        for(int i=left+1;i<=right;i++){
            if(a[i]<pivot){
                j++;
                swap(i,j);  
            }
        }
        if(left!=j)swap(left,j);
        quickSort(left, j-1);
        quickSort(j+1, right);
    }
    

    @Override
    public void load(int[] a) {
        this.a = a;
    }
    @Override
    public void sort() {
       quickSort(0,a.length-1);
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}

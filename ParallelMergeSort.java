import java.lang.*;
import java.util.concurrent.*;
public class ParallelMergeSort{
    
    private int[] input;
    private Executor executor = Executors.newFixedThreadPool(10);
    
    public int[] getSortedArray(){
        executor.execute(new SortInnerClass(input));
        return input;
    }
    
    public ParallelMergeSort(int[] input){
        this.input=input;
    }
    
    
    public class SortInnerClass implements Runnable{
        private int[] input2;
        
        public int[] getSortedArray(){
            executor.execute(new SortInnerClass(input));
            return input2;
        }
        
        public SortInnerClass(int[] input2){
            this.input2=input2;
        }
        
        public void run(){
            input= parallelMergeSort(input);
        }
        /**
        *int first: index of first element
        int last : index of last element
        recursive method
        * */
        public int[] parallelMergeSort(int[] input){
            if(input.length<=1){
                return input;
            }
            
            int[] right;
            int[] left;
            int centre = input.length/2;
            
            //Split array into two
            left = new int[centre];
            right = new int[input.length-centre];
            
            System.arraycopy(input,0,left,0,centre);
            System.arraycopy(input,centre,right,0,input.length-centre);
            
            SortInnerClass x = new SortInnerClass(left);
            SortInnerClass y = new SortInnerClass(right);
            executor.execute(x);
            executor.execute(y);
            
            left = x.getSortedArray();
            right= y.getSortedArray();
            
//             ParallelMergeSort x= new ParallelMergeSort(left);
//             new Thread(x).start();
//             left=x.getSortedArray();
//             
//             ParallelMergeSort y = new ParallelMergeSort(right);
//             new Thread(y).start();
//             right=y.getSortedArray();
            
    //         left=new ParallelMergeSort.parallelMergeSort(left);
    //         right=new ParallelMergeSort.parallelMergeSort(right);
            
            return merge(left,right);
        }

        /**
        * returns a merged array
        * */
        private int[] merge(int[] left, int[]right){
            int[] result = new int[left.length+right.length];

            //counters
            int i=0;//left
            int j=0;//right
            int k=0;//resutl

            while(i < left.length && j < right.length){
                if(left[i]>right[j]){
                    result[k] = right[j];
                    j++;
                }
                else{
                    result[k] = left[i];
                    i++;
                }
                k++;
            }

            while( i < left.length ) {
                result[ k ] = left[ i ];
                i++;
                k++; 
            }

            while(j<right.length){
                result[k] = right[j];
                j++;
                k++;
            }
            return result;
        }
    }
}

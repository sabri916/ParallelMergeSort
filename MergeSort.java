public class MergeSort{
    
    /**
     *int first: index of first element
     int last : index of last element
     recursive method
     * */
    public static int[] parallelMergeSort(int[] input){
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
        
        left=parallelMergeSort(left);
        right=parallelMergeSort(right);
        
        return merge(left,right);
    }

    /**
     * returns a merged array
     * */
    private static int[] merge(int[] left, int[]right){
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

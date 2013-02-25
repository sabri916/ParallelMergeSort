import java.io.*;

public class MergeTest{
    public static void main(String[] args){        
        final String FILE_NAME=args[0];
        final int N = Integer.parseInt(args[1]);
        String num;
        int randList[]= new int[N];
        int i=0;
        
        
        try{
            BufferedReader file =  new BufferedReader(new FileReader(FILE_NAME));
            while((num = file.readLine())!=null){
                randList[i]=Integer.parseInt(num);
                i++;
            }
        }catch(IOException e){
            System.out.println(e);
        }
        
        //randList=ParallelMerge.parallelMergeSort(randList);
        ParallelMergeSort x= new ParallelMergeSort(randList);
        randList=x.getSortedArray();
        
//         for(i=0; i<randList.length;i++){
//             System.out.println(randList[i]);
//         }
    }
}

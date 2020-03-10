package aop;

public class fast {

    public static void main(String[] args) {
        int[] arr = {};
        sort( arr);
    }


    public static void sort(int[] arr){
        int p = 0;
        int t = arr.length-1;
        int mid = arr.length / 2;
        while(p+1 != t){
            if(arr[p] > arr[mid] && arr[t] < arr[mid]){
                int temp = arr[p];
                arr[p] = arr[t];
                arr[t] = temp;
            }else if(arr[p] <= arr[mid]){
                p++;
            }else if(arr[t] >= arr[mid]){
                t--;
            }else {
                p++;
                t--;
            }

        }

    }

}

package com.test.sheldon;

public class MergeSort {
    public void sort(int arr[]){
        if(arr.length <=1){
            return;
        }
        mergeSort(arr,0, arr.length-1);
    }

    public void mergeSort(int arr[], int s, int e){
        if(s>=e){
            return;
        }
        int q = (s+e)/2;
        mergeSort(arr,s, q);
        mergeSort(arr,q+1, e);
        merge(arr, s,q,e);
    }

    public void merge(int arr[],int s, int q, int e){
        int i = s;
        int j = q+1;
        int k = 0;
        int [] tmp = new int[arr.length];
        while(i<=q&&j<=e){
            if(arr[i]<arr[j]){
                tmp[k++]=arr[i++];
            }else{
                tmp[k++]=arr[j++];
            }
        }
        if(i<=q){
            while(i<=q){
                tmp[k++]=arr[i++];
            }
        }else{
            while(j<=e){
                tmp[k++]=arr[j++];
            }
        }
        for(k =0; k<=e-s; k++){
            arr[s+k]=tmp[k];
        }
    }
}

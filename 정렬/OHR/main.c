#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include <time.h>
#define Swap(A, B) { int Temp = A; A = B ; B = Temp; }

void InsertionSort(int A[], int Left, int Right);
int QS_Partition(int A[], int Left, int Right);
void QuickSort(int A[], int Left, int Right);
void MergeSort(int A[], int Left, int Right);
void Merge(int A[], int Left, int Mid, int Right);
int QS_Partition_MedianOf3(int A[], int Left, int Right);
void BuildHeap(int A[], int Left, int Right);
void ArrangeHeap(int A[], int HeapLen, int P_Index);
void HeapSort(int A[], int Left, int Right);
void Alg4_IntroSpectiveSort(int A[], int Left, int Right, int DepthLimit);

int main (int argc, char *argv[])
{
    char * InputFile = argv[1];
    int AlgIndex = atoi(argv[2]);
    FILE * ArrayInfo = fopen(InputFile, "r");

    int ArrayNum, Index;
    fscanf(ArrayInfo, "%d", &ArrayNum);
    int * TargetArr = (int *)malloc(ArrayNum * sizeof(int));

    for (Index = 0 ; Index < ArrayNum ; Index++) {
        fscanf(ArrayInfo, "%d", &TargetArr[Index]);
    } fclose(ArrayInfo);

    int Begin = 0;
    int End = ArrayNum - 1;
    int DepthLimit = round(2 * log(End - Begin));

    clock_t StartTime = clock();
    if (AlgIndex == 1)
        InsertionSort(TargetArr, 0, ArrayNum - 1);
    else if (AlgIndex == 2)
        QuickSort(TargetArr, 0, ArrayNum - 1);
    else if (AlgIndex == 3)
        MergeSort(TargetArr, 0, ArrayNum - 1);
    else if (AlgIndex == 4)
        Alg4_IntroSpectiveSort(TargetArr, 0, ArrayNum - 1, DepthLimit);

    clock_t EndTime = clock();
    double RunTime = (double)(EndTime - StartTime) / CLOCKS_PER_SEC;

    char OutputFile[100];
    snprintf(OutputFile, sizeof(OutputFile), "result_%s", InputFile);
    FILE * ResultFile = fopen(OutputFile, "w");

    fprintf(ResultFile, "%s\n", InputFile);
    fprintf(ResultFile, "%d\n", AlgIndex);
    fprintf(ResultFile, "%d\n", ArrayNum);
    fprintf(ResultFile, "%.5lf\n", RunTime);
    for (Index = 0 ; Index < ArrayNum ; Index++) {
        fprintf(ResultFile, "%d ", TargetArr[Index]);
    } fclose(ResultFile);

    free(TargetArr);

    return 0;
}

void InsertionSort(int A[], int Left, int Right) {
    int Arr_Index, Key_Index, Key_Num;

    for (Key_Index = Left + 1; Key_Index <= Right; Key_Index++)
    {
        Key_Num = A[Key_Index];
        Arr_Index = Key_Index - 1;

        while (Arr_Index >= Left && A[Arr_Index] > Key_Num)
        {
            A[Arr_Index + 1] = A[Arr_Index];
            Arr_Index--;
        }
        A[Arr_Index + 1] = Key_Num;
    }
}

int QS_Partition(int A[], int Left, int Right) {

    int Pivot = Left;
    int Arr_Index;
    for (Arr_Index = Left ; Arr_Index < Right ; Arr_Index++)
    {
        if (A[Arr_Index] < A[Right])
        {
            Swap(A[Pivot], A[Arr_Index]);
            Pivot++;
        }
    }
    Swap(A[Pivot], A[Right]);
    return Pivot;
}

void QuickSort(int A[], int Left, int Right) {

    int Pivot;
    if(Right > Left) {
        Pivot = QS_Partition(A, Left, Right);

        QuickSort(A, Left, Pivot - 1);
        QuickSort(A, Pivot + 1, Right);
    }
}

void MergeSort(int A[], int Left, int Right) {

    if (Left >= Right)
        return ;

    int Mid = floor((Left + Right) / 2);
    MergeSort(A, Left, Mid);
    MergeSort(A, Mid + 1, Right);
    Merge(A, Left, Mid, Right);
}

void Merge(int A[], int Left, int Mid, int Right){

    int Left_Index, Right_Index;
    int Arr_Index = Left;
    int Len_L = Mid - Left + 1;
    int Len_R = Right - Mid;

    int * ArrLeft = (int*)malloc(Len_L * sizeof(int));
    int * ArrRight = (int*)malloc(Len_R * sizeof(int));
    memset(ArrLeft, 0, Len_L * sizeof(int));
    memset(ArrRight, 0, Len_R * sizeof(int));

    for (Left_Index = 0 ; Left_Index < Len_L ; Left_Index++ )
        ArrLeft[Left_Index] = A[Left + Left_Index];

    for (Right_Index = 0 ; Right_Index < Len_R ; Right_Index++ )
        ArrRight[Right_Index] = A[Mid + Right_Index + 1];

    Left_Index = Right_Index = 0;
    while (Left_Index < Len_L && Right_Index < Len_R )
    {
        if (ArrLeft[Left_Index] <= ArrRight[Right_Index])
        {
            A[Arr_Index] = ArrLeft[Left_Index];
            Left_Index++;
        }
        else
        {
            A[Arr_Index] = ArrRight[Right_Index];
            Right_Index++;
        }
        Arr_Index++;
    }

    while (Left_Index < Len_L){
        A[Arr_Index] = ArrLeft[Left_Index];
        Left_Index++;
        Arr_Index++;
    }

    while (Right_Index < Len_R){
        A[Arr_Index] = ArrRight[Right_Index];
        Right_Index++;
        Arr_Index++;
    }
    free(ArrLeft); free(ArrRight);
}

int QS_Partition_MedianOf3(int A[], int Left, int Right){
    int Pivot = floor((Left + Right) / 2);

    if (A[Pivot] < A[Left] && A[Left] < A[Right])
        Pivot = Left;
    if (A[Left] < A[Right] && A[Right] < A[Pivot])
        Pivot = Right;
    if (A[Right] < A[Left] && A[Left] < A[Pivot])
        Pivot = Left;
    if (A[Pivot] < A[Right] && A[Right] < A[Left])
        Pivot = Right;
    Swap(A[Pivot], A[Right]);

    Pivot = Left;
    int Arr_Index;

    for (Arr_Index = Left ; Arr_Index < Right ; Arr_Index++)
    {
        if (A[Arr_Index] < A[Right])
        {
            Swap(A[Pivot], A[Arr_Index]);
            Pivot++;
        }
    }
    Swap(A[Pivot], A[Right]);

    return Pivot;
}

void BuildHeap(int A[], int Left, int Right){
    int ArrLen = Right - Left + 1;

    for (int P_Index = Left + (ArrLen / 2 - 1) ; P_Index >= Left ; P_Index--)
        ArrangeHeap(A, ArrLen, P_Index);
}

void ArrangeHeap(int A[], int HeapLen, int P_Index){
    int Largest = P_Index;
    int Left = 2 * P_Index + 1;
    int Right = 2 * P_Index + 2;

    if ((Left < HeapLen) && (A[Left] > A[Largest]))
        Largest = Left;

    if ((Right < HeapLen) && (A[Right] > A[Largest]))
        Largest = Right;

    if (Largest != P_Index)
    {
        Swap(A[P_Index], A[Largest]);
        ArrangeHeap(A, HeapLen, Largest);
    }
}

void HeapSort(int A[], int Left, int Right){
    BuildHeap(A, Left, Right);
    int Leaf_Index;

    for (Leaf_Index = Right ; Leaf_Index > Left ; Leaf_Index--)
    {
        Swap(A[Left], A[Leaf_Index]);
        ArrangeHeap(A, Leaf_Index, Left);
    }

}

void Alg4_IntroSpectiveSort(int A[], int Left, int Right, int DepthLimit){
    int ArrLen = Right - Left + 1;
    int Pivot;

    if (ArrLen < 16) {
        InsertionSort(A, Left, Right);
        return;
    }

    if (DepthLimit == 0) {
        HeapSort(A, Left, Right);
        return;
    }

    if (Right > Left) {
        Pivot = QS_Partition_MedianOf3(A, Left, Right);
        Alg4_IntroSpectiveSort(A, Left, Pivot - 1, DepthLimit - 1);
        Alg4_IntroSpectiveSort(A, Pivot + 1, Right, DepthLimit - 1);
    }
}

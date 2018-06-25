class Sorts {

    static double[] quickSort(double[] array, int left, int right) {
        int i = left, j = right;
        double pivot = array[left + (right - left) / 2];
        while (i <= j) {
            while (array[i] < pivot) {
                i++;
            }
            while (pivot < array[j]) {
                j--;
            }
            if (i <= j) {
                swapInArray(array, i, j);
                i++;
                j--;
            }
        }
        if (left < j)
            quickSort(array, left, j);
        if (i < right)
            quickSort(array, i, right);

        return array;
    }

    static double[] quickSortReverse(double[] array, int left, int right) {
        int i = left, j = right;
        double pivot = array[(left + right) / 2];
        while (i <= j) {
            while (array[i] > pivot) {
                i++;
            }
            while (pivot > array[j]) {
                j--;
            }
            if (i <= j) {
                swapInArray(array, i, j);
                i++;
                j--;
            }
        }
        if (left < j)
            quickSortReverse(array, left, j);
        if (i < right)
            quickSortReverse(array, i, right);
        return array;
    }

    private static void swapInArray(double[] array, int position_a, int position_b) {
        double temp_c = array[position_a];
        array[position_a] = array[position_b];
        array[position_b] = temp_c;
    }

    static void mergeSort(double[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);
            merge(array, left, middle, right);
        }
    }

    private static void merge(double[] array, int left, int middle, int right) {
        int indexLeft = 0, indexRight = 0, indexInitial = left;
        int sizeLeft = middle - left + 1;
        int sizeRight = right - middle;

        double[] leftArray = new double[sizeLeft];
        double[] rightArray = new double[sizeRight];
        System.arraycopy(array, left, leftArray, 0, sizeLeft);
        System.arraycopy(array, middle + 1, rightArray, 0, sizeRight);
        for (int index = 0; index < sizeRight; index++) {
            rightArray[index] = array[middle + index + 1];
        }
        while (indexLeft < sizeLeft && indexRight < sizeRight) {
            if (leftArray[indexLeft] <= rightArray[indexRight]) {
                array[indexInitial] = leftArray[indexLeft];
                indexLeft++;
            } else {
                array[indexInitial] = rightArray[indexRight];
                indexRight++;
            }
            indexInitial++;
        }
        while (indexLeft < sizeLeft) {
            array[indexInitial] = leftArray[indexLeft];
            indexLeft++;
            indexInitial++;
        }
        while (indexRight < sizeRight) {
            array[indexInitial] = rightArray[indexRight];
            indexRight++;
            indexInitial++;
        }
    }

    static void shellSort(double[] array, int intervalMultiplier) {
        int inner, outer;
        int interval = 1;
        double temp;

        while (interval <= array.length / intervalMultiplier) {
            interval = interval * intervalMultiplier + 1;
        }
        while (interval > 0) {
            for (outer = interval; outer < array.length; outer++) {
                temp = array[outer];
                inner = outer;
                while (inner > interval - 1 && array[inner - interval] >= temp) {
                    array[inner] = array[inner - interval];
                    inner -= interval;
                }
                array[inner] = temp;
            }
            interval = (interval - 1) / intervalMultiplier;
        }
    }

    // Uzywam do generowania tablicy odwrotnie posortowanej

    static double[] shellSortReverse(double[] array) {
        int inner, outer;
        int interval = 1;
        double temp;

        while (interval <= array.length / 3) {
            interval = interval * 3 + 1;
        }
        while (interval > 0) {
            for (outer = interval; outer < array.length; outer++) {
                temp = array[outer];
                inner = outer;
                while (inner > interval - 1 && array[inner - interval] <= temp) {
                    array[inner] = array[inner - interval];
                    inner -= interval;
                }
                array[inner] = temp;
            }
            interval = (interval - 1) / 3;
        }
        return array;
    }

//  Nie dziala do konca, 1 z elementow zostaje nadpisywany przez ostatni element

//    public static double[] librarySort(double[] doubleArray) {
//        int epsilon = 1;
//        int i;
//        int j;
//        int expandedArraySize = (1+epsilon)*doubleArray.length;
//        double[] expandedDoubleArray = new double[expandedArraySize];
//        makeExpandedArray(expandedDoubleArray, expandedArraySize);
//        auxiliaryLibrarySort(doubleArray.length, epsilon, doubleArray, expandedDoubleArray);
//        for(i = 0, j = 0; i < expandedArraySize && j < doubleArray.length; i++)
//            if(!isEmpty(expandedDoubleArray[i]))
//                doubleArray[j++] = expandedDoubleArray[i];
//        return doubleArray;
//    }
//
//    private static void makeExpandedArray(double[] expandedDoubleArray, int expandedDoubleSize) {
//        for(int i = 0; i < expandedDoubleSize; i++) {
//            expandedDoubleArray[i] = -1;
//        }
//    }
//
//    private static void auxiliaryLibrarySort(int length, double epsilon, double[] doubleArray, double[] expandedDoubleArray) {
//        if(length == 0) {
//            return;
//        }
//        int goal = 1;
//        int position = 1;
//        int index;
//        int expandedArraySize = (int)Math.max((1+epsilon),goal+1);
//        expandedDoubleArray[0] = doubleArray[0];
//        while(position < length) {
//            for(index = 0; index < goal; index++) {
//                int insertPosition = binarySearch(doubleArray[position], expandedDoubleArray, expandedArraySize - 1);
//                insertPosition++;
//                if(!isEmpty(expandedDoubleArray[insertPosition])) {
//                    int nextFreePosition = insertPosition + 1;
//                while(!isEmpty(expandedDoubleArray[nextFreePosition]))
//                    nextFreePosition++;
//                if(nextFreePosition >= expandedArraySize) {
//                    insertPosition --;
//                    if(!isEmpty(expandedDoubleArray[insertPosition])) {
//                        nextFreePosition = insertPosition - 1;
//                        while (!isEmpty(expandedDoubleArray[nextFreePosition]))
//                            nextFreePosition--;
//                        while (nextFreePosition < insertPosition) {
//                            expandedDoubleArray[nextFreePosition] = expandedDoubleArray[nextFreePosition + 1];
//                            nextFreePosition++;
//                        }
//                    }
//                } else {
//                    while(nextFreePosition > insertPosition) {
//                        expandedDoubleArray[nextFreePosition] = expandedDoubleArray[nextFreePosition -1];
//                        nextFreePosition--;
//                        }
//                    }
//                } else if(insertPosition >= expandedArraySize) {
//                    insertPosition--;
//                    int nextFreePosition = insertPosition - 1;
//                    while(!isEmpty(expandedDoubleArray[nextFreePosition]))
//                        nextFreePosition--;
//                    while(nextFreePosition < insertPosition) {
//                        expandedDoubleArray[nextFreePosition] = expandedDoubleArray[nextFreePosition + 1];
//                        nextFreePosition++;
//                    }
//                }
//                expandedDoubleArray[insertPosition] = doubleArray[position++];
//            if(position >= length)
//                    return;
//            }
//
//            int k = (int)Math.min(goal*(2+2*epsilon),(1 + epsilon)*length);
//            int step = (k + 1)/(index + 1);
//            for(index = expandedArraySize - 1; index >= 0 && k > 0; index--, k -= step) {
//                expandedDoubleArray[k] = expandedDoubleArray[index];
//                expandedDoubleArray[index] = - 1;
//            }
//            expandedArraySize = (int)Math.min(goal*(2+2*epsilon),length*(1+epsilon));
//            goal<<=1;
//        }
//    }
//
//    private static int binarySearch(double element, double[] expandedDoubleArray, int last) {
//        int first = 0;
//        int middle;
//        while(0 <= last && isEmpty(expandedDoubleArray[last]))
//            last--;
//        while(first <= last && isEmpty(expandedDoubleArray[first]))
//            first++;
//        while(first <= last) {
//            middle = (first + last)/2;
//            if(isEmpty(expandedDoubleArray[middle])) {
//                int auxilliary = middle + 1;
//                while(auxilliary < last && isEmpty(expandedDoubleArray[auxilliary]))
//                    auxilliary++;
//                if(expandedDoubleArray[auxilliary] > element) {
//                    auxilliary = middle - 1;
//                    while(middle > first && isEmpty(expandedDoubleArray[middle]))
//                        middle --;
//                    if(expandedDoubleArray[middle] < element)
//                        return middle;
//                    last = middle - 1;
//                } else first = auxilliary + 1;
//            } else if(expandedDoubleArray[middle] < element) {
//                first = middle + 1;
//            } else {
//                last = middle - 1;
//            }
//        }
//        if(0 <= last && isEmpty(expandedDoubleArray[last]))
//            last--;
//        return last;
//    }
//
//    public static boolean isEmpty(double element) {
//        return element < 0;
//    }

}





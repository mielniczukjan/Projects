import java.io.*;
import java.util.Scanner;

class TextInput {
    private static final int[] SIZE_ARRAY = {0, 50000, 100000, 250000, 500000, 1000000, 2000000};

    static void textInput() {
        int sizeIndex;
        int generatorIndex;
        int sortIndex;
        int againInput;
        int ownInput;
        while (true) {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Would you like to generate your own array?\n1 - Yes\n2 - No");
            ownInput = userInput(2, userInput);
            if (ownInput == 2) {
                System.out.println("\n\nHow do you want to have your array generated ?\n1 - Sorted\n2 - ReverseSorted\n3 - HalfSorted\n4 - Random");
                generatorIndex = userInput(4, userInput);
                System.out.println("\n\nHow big do you want your array to be?");
                System.out.println("1 - 50000\n2 - 100000\n3 - 250000\n4 - 500000\n5 - 1000000\n6 - 2000000");
                sizeIndex = userInput(6, userInput);
                System.out.println("\n\nWhat sort method would you like to use?");
                System.out.println("1 - QuickSort\n2 - MergeSort\n3 - ShellSort");
                sortIndex = userInput(3, userInput);
                printLine();
                System.out.println("Generating arrays\n");
                printLine();
                File fileArrays = Generator.generateToFile(SIZE_ARRAY[sizeIndex], generatorIndex);
                System.out.println("Arrays generated successfully\n");
                printLine();
                sortingUserInput(fileArrays, sortIndex, SIZE_ARRAY[sizeIndex]);
            } else
                ownInput(userInput);
            System.out.println("Would you like to sort again?\n1 - Yes 2 - No");
            againInput = userInput(2, userInput);
            if (againInput == 2)
                break;
        }
    }

    private static void printLine() {
        for (int i = 0; i < 50; i++) {
            System.out.print("=");
        }
        System.out.println("\n");
    }

    private static void sortingUserInput(File fileArrays, int sortIndex, int size) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileArrays));
            double[] arrayTemporary = new double[size];
            String[] stringArray;
            int fullTime = 0;
            long start;
            long end;
            String line;
            int index = 1;
            try {
                switch (sortIndex) {
                    case 1:
                        line = bufferedReader.readLine();
                        while (line != null) {
                            line = line.replace(",", ".");
                            stringArray = line.split(";");
                            for (int i = 0; i < stringArray.length; i++) {
                                arrayTemporary[i] = Double.parseDouble(stringArray[i]);
                            }
                            start = System.currentTimeMillis();
                            Sorts.quickSort(arrayTemporary, 0, arrayTemporary.length - 1);
                            end = System.currentTimeMillis() - start;
                            fullTime += end;
                            line = bufferedReader.readLine();
                            System.out.println("Time of sorting for Test" + index + " = " + end + "ms");
                            index++;
                        }
                        break;
                    case 2:
                        line = bufferedReader.readLine();
                        while (line != null) {
                            line = line.replace(",", ".");
                            stringArray = line.split(";");
                            for (int i = 0; i < stringArray.length; i++) {
                                arrayTemporary[i] = Double.parseDouble(stringArray[i]);
                            }
                            start = System.currentTimeMillis();
                            Sorts.mergeSort(arrayTemporary, 0, arrayTemporary.length - 1);
                            end = System.currentTimeMillis() - start;
                            fullTime += end;
                            line = bufferedReader.readLine();
                            System.out.println("Time of sorting for Test" + index + " = " + end + "ms");
                            index++;
                        }
                        break;
                    case 3:
                        System.out.println("Choose your shellShort interval multiplier");
                        Scanner userInput = new Scanner(System.in);
                        int shellShortIntervalMultiplier = userInput(Integer.MAX_VALUE, userInput);
                        line = bufferedReader.readLine();
                        while (line != null) {
                            line = line.replace(",", ".");
                            stringArray = line.split(";");
                            for (int i = 0; i < stringArray.length; i++) {
                                arrayTemporary[i] = Double.parseDouble(stringArray[i]);
                            }
                            start = System.currentTimeMillis();
                            Sorts.shellSort(arrayTemporary, shellShortIntervalMultiplier);
                            end = System.currentTimeMillis() - start;
                            fullTime += end;
                            line = bufferedReader.readLine();
                            System.out.println("Time of sorting for Test" + index + " = " + end + "ms");
                            index++;
                        }
                        break;
                }
                System.out.printf("Full time of sorting = %dms%n", fullTime);
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void ownInput(Scanner userInput) {
        int size;
        int sortIndex;
        long start;
        long end = 0;
        System.out.println("Type the length of your desired array");
        size = userInput(Integer.MAX_VALUE, userInput);
        double[] arrayDouble;
        System.out.println("Type an array you want to sort");
        arrayDouble = makingArray(size, userInput);
        System.out.println("\n\nWhat sort method would you like to use?");
        System.out.println("1 - QuickSort\n2 - MergeSort\n3 - ShellSort");
        sortIndex = userInput(3, userInput);
        switch (sortIndex) {
            case 1:
                start = System.currentTimeMillis();
                Sorts.quickSort(arrayDouble, 0, arrayDouble.length - 1);
                end = System.currentTimeMillis() - start;
                break;
            case 2:
                start = System.currentTimeMillis();
                Sorts.mergeSort(arrayDouble, 0, arrayDouble.length - 1);
                end = System.currentTimeMillis() - start;
                break;
            case 3:
                int shellShortIntervalMultiplier = userInput(Integer.MAX_VALUE, userInput);
                start = System.currentTimeMillis();
                Sorts.shellSort(arrayDouble, shellShortIntervalMultiplier);
                end = System.currentTimeMillis() - start;
                break;
        }
        System.out.println("Time of sorting : " + end + "ms");
    }

    private static int userInput(int left, Scanner userInput) {
        int value = 0;
        boolean inputChecker = false;
        while (value > left || value <= 0) {
            if (inputChecker)
                System.out.println("Type a correct number");
            while (!userInput.hasNextInt()) {
                userInput.next();
                System.out.println("Type a correct number");
            }
            value = userInput.nextInt();
            inputChecker = true;
        }
        return value;
    }

    private static double[] makingArray(int size, Scanner userInput) {
        double[] doubleArray = new double[size];
        for (int index = 0; index < size; index++) {
            while (!userInput.hasNextDouble()) {
                userInput.next();
                System.out.println("Type correct number");
            }
            doubleArray[index] = userInput.nextDouble();
        }
        return doubleArray;
    }
}


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Generator {

    private static final String[] GENERATOR_NAME_ARRAYS = {"generateNothing", "generateSorted", "generateReverseSorted", "generateHalfSorted", "generateRandom"};

    private static double[] generateSorted(int size) {
        double[] arraySorted = new double[size];
        for (int index = 0; index < size; index++) {
            arraySorted[index] = (Math.random() * 100000) + 1;
        }
        Sorts.shellSort(arraySorted, 3);
        return arraySorted;
    }

    private static double[] generateReverseSorted(int size) {
        double[] arrayReverseSorted = new double[size];
        for (int index = 0; index < size; index++) {
            arrayReverseSorted[index] = (Math.random() * size) + 1;
        }
        arrayReverseSorted = Sorts.shellSortReverse(arrayReverseSorted);
        return arrayReverseSorted;
    }

    private static double[] generateHalfSorted(int size) {
        double[] arrayHalfSorted = new double[size];
        for (int index = 0; index < size; index++) {
            arrayHalfSorted[index] = (Math.random() * size);
        }
        Sorts.quickSort(arrayHalfSorted, 0, size / 2);
        return arrayHalfSorted;
    }

    private static double[] generateRandom(int size) {
        double[] arrayRandom = new double[size];
        for (int index = 0; index < size; index++) {
            arrayRandom[index] = Math.random() * (size) + 1;
        }
        return arrayRandom;
    }

    static File generateToFile(int size, int generatorIndex) {
        try {
            File generateRandomFile = new File(GENERATOR_NAME_ARRAYS[generatorIndex] + size + ".csv");
            BufferedWriter writer = new BufferedWriter(new FileWriter(generateRandomFile));
            double[] arrayToFile;
            switch (generatorIndex) {
                case 1:
                    for (int j = 0; j < 100; j++) {
                        StringBuilder sb = new StringBuilder();
                        arrayToFile = generateSorted(size);
                        for (double element : arrayToFile) {
                            sb.append(String.format("%.2f", element));
                            sb.append(";");
                        }
                        writer.write(sb.toString());
                        writer.write("\n");
                    }
                    break;
                case 2:
                    for (int j = 0; j < 100; j++) {
                        StringBuilder sb = new StringBuilder();
                        arrayToFile = generateReverseSorted(size);
                        for (double element : arrayToFile) {
                            sb.append(String.format("%.2f", element));
                            sb.append(";");
                        }
                        writer.write(sb.toString());
                        writer.write("\n");
                    }
                    break;
                case 3:
                    for (int j = 0; j < 100; j++) {
                        StringBuilder sb = new StringBuilder();
                        arrayToFile = generateHalfSorted(size);
                        for (double element : arrayToFile) {
                            sb.append(String.format("%.2f", element));
                            sb.append(";");
                        }
                        writer.write(sb.toString());
                        writer.write("\n");
                    }
                    break;
                case 4:
                    for (int j = 0; j < 100; j++) {
                        StringBuilder sb = new StringBuilder();
                        arrayToFile = generateRandom(size);
                        for (double element : arrayToFile) {
                            sb.append(String.format("%.2f", element));
                            sb.append(";");
                        }
                        writer.write(sb.toString());
                        writer.write("\n");
                    }
                    break;
            }
            writer.close();
            return generateRandomFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class KPCoreAlgorithm {
    public static void main(String[] args) {
        try {
            // Read input from the layers file
            ArrayList<int[][]> M = readLayers("layers.txt");

            // Read input from the k_values file
            int[] k = readArray("k_values.txt");

            // Read input from the p_values file
            double[] p = readArrayDouble("p_values.txt");

            // Print the inputs
            System.out.println("Number of Layers: " + M.size());
            System.out.println("Array Sizes:");
            for (int layer = 0; layer < M.size(); layer++) {
                System.out.println("Layer " + layer + ": " + M.get(layer).length);
            }

            System.out.println("k Values: " + Arrays.toString(k));
            System.out.println("p Values: " + Arrays.toString(p));

            // Initialize the vertex set Ql
            Set<Integer> Ql = new HashSet<>();
            for (int vertex = 0; vertex < M.get(M.size() - 1).length; vertex++) {
                Ql.add(vertex);
            }

            // Repeat the vertex peeling process
            while (true) {
                // Save a copy of Ql
                Set<Integer> Qbefore = new HashSet<>(Ql);

                // Peel the vertices with degrees less than k[l]
                Ql = peelDegree(Ql, M.get(M.size() - 1), k[k.length - 1]);

                // For each other layer, peel the vertices with neighbor coverage fractions less than p[i]
                for (int i = k.length - 2; i >= 0; i--) {
                    Ql = peelNeighborCoverage(Ql, M.get(i), p[i]);
                }

                // Check if Ql has changed
                if (Ql.equals(Qbefore)) {
                    break;
                }
            }

            // Output the (k, p)-core
            System.out.println("The (k, p)-core in the graph is: " + Ql);

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            e.printStackTrace();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.err.println("Input error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static ArrayList<int[][]> readLayers(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));

        // Check if there is at least one layer
        if (!scanner.hasNextInt()) {
            throw new NoSuchElementException("The layers file is empty or does not contain the expected data.");
        }

        int numLayers = scanner.nextInt();
        ArrayList<int[][]> layers = new ArrayList<>();

        for (int layer = 0; layer < numLayers; layer++) {
            // Check if there are enough values for the current layer
            if (!scanner.hasNextInt() || !scanner.hasNextInt()) {
                throw new NoSuchElementException("Invalid format in the layers file.");
            }

            int numVertices = scanner.nextInt();
            int numEdges = scanner.nextInt();
            int[][] currentLayer = new int[numVertices][numEdges];

            // Check if there are enough values for the edges
            for (int vertex = 0; vertex < numVertices; vertex++) {
                for (int edge = 0; edge < numEdges; edge++) {
                    // Check if there are enough values for the edge
                    if (!scanner.hasNextInt()) {
                        throw new NoSuchElementException("Invalid format in the layers file.");
                    }
                    currentLayer[vertex][edge] = scanner.nextInt();
                }
            }

            layers.add(currentLayer);
        }
        scanner.close();
        return layers;
    }

    private static int[] readArray(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));

        // Check if there is at least one element
        if (!scanner.hasNextInt()) {
            throw new NoSuchElementException("The array file is empty or does not contain the expected data.");
        }

        int numElements = scanner.nextInt();
        int[] array = new int[numElements];

        // Check if there are enough values for the array
        for (int i = 0; i < numElements; i++) {
            // Check if there are enough values for the array
            if (!scanner.hasNextInt()) {
                throw new NoSuchElementException("Invalid format in the array file.");
            }
            array[i] = scanner.nextInt();
        }

        scanner.close();
        return array;
    }

    private static double[] readArrayDouble(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));

        // Check if there is at least one element
        if (!scanner.hasNextInt()) {
            throw new NoSuchElementException("The array file is empty or does not contain the expected data.");
        }

        int numElements = scanner.nextInt();
        double[] array = new double[numElements];

        // Check if there are enough values for the array
        for (int i = 0; i < numElements; i++) {
            // Check if there are enough values for the array
            if (!scanner.hasNextDouble()) {
                throw new NoSuchElementException("Invalid format in the array file.");
            }
            array[i] = scanner.nextDouble();
        }

        scanner.close();
        return array;
    }

    private static Set<Integer> peelDegree(Set<Integer> Ql, int[][] M, int k) {
        Set<Integer> toRemove = new HashSet<>();

        for (int vertex : Ql) {
            if (getDegree(vertex, M) < k) {
                toRemove.add(vertex);
            }
        }

        Ql.removeAll(toRemove);
        return Ql;
    }

    private static Set<Integer> peelNeighborCoverage(Set<Integer> Ql, int[][] M, double p) {
        Set<Integer> toRemove = new HashSet<>();

        for (int vertex : Ql) {
            double neighborCoverageFraction = computeNeighborCoverageFraction(vertex, M);
            if (neighborCoverageFraction < p) {
                toRemove.add(vertex);
            }
        }

        Ql.removeAll(toRemove);
        return Ql;
    }

    private static int getDegree(int vertex, int[][] M) {
        return M[vertex].length; // Assumes undirected graph
    }

    private static double computeNeighborCoverageFraction(int vertex, int[][] M) {
        int neighborCount = M[vertex].length;
        double neighborCoverageFraction = (double) neighborCount / (M.length - 1); // -1 to exclude self-loop
        return neighborCoverageFraction;
    }
}

package Question2;

import java.util.ArrayList;
import java.util.List;

public class IndivSecrets {
    public static void main(String[] args) {
        // Number of individuals
        int n = 5;
        // List of intervals during which the secret can be shared
        List<int[]> intervals = new ArrayList<>();
        intervals.add(new int[]{0, 2});
        intervals.add(new int[]{1, 3});
        intervals.add(new int[]{2, 4});
        // The first person who possesses the secret
        int firstPerson = 0;

        // Find the list of individuals who will eventually know the secret
        List<Integer> knownIndividuals = findKnownIndividuals(n, intervals, firstPerson);

        // Print the list of known individuals
        System.out.print("[");
        for (int i = 0; i < knownIndividuals.size(); i++) {
            System.out.print(knownIndividuals.get(i));
            if (i < knownIndividuals.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    // Method to find the list of individuals who will eventually know the secret
    public static List<Integer> findKnownIndividuals(int n, List<int[]> intervals, int firstPerson) {
        List<Integer> knownIndividuals = new ArrayList<>();
        // Array to keep track of whether an individual is known or not
        boolean[] isKnown = new boolean[n];
        // Mark the first person as known
        isKnown[firstPerson] = true;

        // Add the first person to the list of known individuals
        knownIndividuals.add(firstPerson);

        // Iterate through each interval
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            // Share the secret with individuals in the interval
            for (int i = start; i <= end; i++) {
                if (!isKnown[i]) {
                    knownIndividuals.add(i);
                    isKnown[i] = true;
                }
            }
        }

        return knownIndividuals;
    }
}

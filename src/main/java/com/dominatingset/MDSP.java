package com.dominatingset;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MDSP {

    Map<String, List<String>> configuration;
    static List<String> results = new ArrayList<>();

    public MDSP(String configurationFile) {
        Map<String, List<String>> sections = new HashMap<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader("./src/main/java/com/dominatingset/configuration/" + configurationFile))) {
            String line;
            String currentSection = null;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("-")) {
                    currentSection = line.substring(1, line.length() - 2).trim();
                    sections.put(currentSection, new ArrayList<>());
                } else {
                    if (!line.isBlank()) {
                        sections.get(currentSection).add(line);
                    }
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.configuration = sections;
    }

    public static void runIG(String file, double REMOVE_VERTICES_PERCENTAGE, int MAX_ITERATIONS_WITHOUT_IMPROVEMENT,
            String InitialSolutionMethod, String LocalImprovementMethod, String DestructionMethod,
            String ReconstructionMethod) {

        IG ig = new IG(file, MAX_ITERATIONS_WITHOUT_IMPROVEMENT, REMOVE_VERTICES_PERCENTAGE, InitialSolutionMethod,
                LocalImprovementMethod, DestructionMethod, ReconstructionMethod);

        // Calling the Iterated Greedy algorithm
        ig.run();

        // Print results
        System.out.println(ig.getResults());
        results.add(ig.getResults());

        System.out.println(" --------------------------------\n");
    }

    public Map<String, List<String>> getConfiguration() {
        return configuration;
    }

    public static void main(String[] args) {
        // Instace new configuration
        MDSP mdsp = new MDSP("configuration.txt");

        // Get configuration
        Map<String, List<String>> configuration = mdsp.getConfiguration();

        for (String file : configuration.get("Files")) {
            for (String REMOVE_VERTICES_PERCENTAGE : configuration.get("RVP")) {
                for (String MAX_ITERATIONS_WITHOUT_IMPROVEMENT : configuration.get("MIWI")) {
                    for (String INITIAL_SOLUTION : configuration.get("ISMethods")) {
                        for (String LOCAL_IMPROVEMENT : configuration.get("LIMethods")) {
                            for (String DELETION : configuration.get("DMethods")) {
                                for (String RECONSTRUCTION : configuration.get("RMethods")) {
                                    // Run IG
                                    runIG(file, Double.parseDouble(REMOVE_VERTICES_PERCENTAGE),
                                            Integer.parseInt(MAX_ITERATIONS_WITHOUT_IMPROVEMENT), INITIAL_SOLUTION,
                                            LOCAL_IMPROVEMENT, DELETION, RECONSTRUCTION);
                                }
                            }
                        }
                    }
                }
            }
        }

        // Print results
        System.out.println(" --------------------------------\n");
        System.out.println("RESULTS");
        System.out.println(" --------------------------------\n");

        for (String result : results) {
            System.out.println(result);
        }
    }
}

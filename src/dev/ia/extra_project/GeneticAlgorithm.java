package dev.ia.extra_project;

import java.util.Arrays;
import java.util.Random;

public class GeneticAlgorithm {

    // Algoritmo genético
    private final int POPULATION_SIZE = 15;
    private final int NUM_GENERATIONS = 25;
    private final double MUTATION_VARIATION = 0.1D;

    // Intervalo entre os pontos
    private final double MAX = 2.0D;
    private final double MIN = -2.0D;

    // Função (Disponível para Mudança)
    private double function(double x) {
        return x * x;
    }

    public void init() {

        Random random = new Random();

        double[] population = new double[POPULATION_SIZE];

        for (int i = 0; i < POPULATION_SIZE; i++)
            population[i] = MIN + (MAX - MIN) * random.nextDouble();

        for (int generation = 0; generation < NUM_GENERATIONS; generation++) {

            double[] fitness = new double[POPULATION_SIZE];

            for (int i = 0; i < POPULATION_SIZE; i++)
                fitness[i] = function(population[i]);

            double[] new_population = new double[POPULATION_SIZE];

            for (int i = 0; i < POPULATION_SIZE; i++) {

                int next_gen = random.nextInt(POPULATION_SIZE);
                int next_gen_2 = random.nextInt(POPULATION_SIZE);

                new_population[i] = (fitness[next_gen] < fitness[next_gen_2]) ? population[next_gen] : population[next_gen_2];
            }

            for (int i = 0; i < POPULATION_SIZE; i++) {

                double variation = random.nextDouble();

                if (variation < MUTATION_VARIATION)
                    new_population[i] = MIN + (MAX - MIN) * variation;

            }

            population = new_population;
        }

        double best_gene = population[0];
        double best_fitness = function(best_gene);

        for (int i = 1; i < POPULATION_SIZE; i++) {

            double actual_fitness = function(population[i]);

            if (actual_fitness < best_fitness) {
                best_gene = population[i];
                best_fitness = actual_fitness;
            }

        }

        System.out.println(Arrays.toString(population));
        System.out.println("Best gene find in population: x = " + best_gene + ", f(x) = " + best_fitness);

    }

}

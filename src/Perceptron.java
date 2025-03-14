import java.util.Arrays;

public class Perceptron {
    private double[] weights; // Weights for the perceptron
    private double bias;      // Bias term
    private double learningRate;

    // Constructor to initialize the perceptron
    public Perceptron(int numFeatures, double learningRate) {
        this.weights = new double[numFeatures];
        this.bias = 0.0;
        this.learningRate = learningRate;

        // Initialize weights to small random values
        for (int i = 0; i < numFeatures; i++) {
            weights[i] = Math.random() * 0.01; // Small random values
        }
    }

    // Activation function (Step function)
    private int activate(double sum) {
        return sum >= 0 ? 1 : 0; // Returns 1 if sum >= 0, otherwise 0
    }

    // Predict function to classify a feature set
    public int predict(double[] features) {
        double sum = bias;

        // Weighted sum of inputs and weights
        for (int i = 0; i < features.length; i++) {
            sum += features[i] * weights[i];
        }

        // Apply activation function
        return activate(sum);
    }

    // Train the perceptron using one training sample
    public void train(double[] features, int targetLabel) {
        int prediction = predict(features);
        int error = targetLabel - prediction;

        // Update weights and bias if there is an error
        if (error != 0) {
            for (int i = 0; i < weights.length; i++) {
                weights[i] += learningRate * error * features[i];
            }
            bias += learningRate * error;
        }
    }

    // Train the perceptron with multiple samples
    public void trainMultiple(double[][] featureSet, int[] labels, int epochs) {
        for (int epoch = 0; epoch < epochs; epoch++) {
            int errors = 0;

            for (int i = 0; i < featureSet.length; i++) {
                int targetLabel = labels[i]; // Target label (1 or 0)
                int prediction = predict(featureSet[i]); // Current prediction
                int error = targetLabel - prediction;

                // Update weights if there is an error
                if (error != 0) {
                    errors++;
                    train(featureSet[i], targetLabel);
                }
            }

            // Optional: Print the number of errors per epoch for debugging
            System.out.println("Epoch " + (epoch + 1) + ": Errors = " + errors);
        }
    }
    // Debugging utility: Print the weights and bias
    public void printModel() {
        System.out.println("Weights: " + Arrays.toString(weights));
        System.out.println("Bias: " + bias);
                }
}

import utils.TraceGenerator;

public class TestGenerator {
    public static void main(String[] args) {
        int n = 5; // Set the desired value for n

        int[] randomTrace = TraceGenerator.createRandom(n);
        int[] sequencedTrace = TraceGenerator.createSequenced(n);
        int[] orderedTrace = TraceGenerator.createOrdered(n);
        int[] strictOrderedTrace = TraceGenerator.createStrictOrdered(n);
        int[] fairTrace = TraceGenerator.createFair(n);
        System.out.print("Random Trace: ");

        // Print the traces
        System.out.print("Random Trace: ");
        printTrace(randomTrace);
        System.out.print("Sequenced Trace: ");
        printTrace(sequencedTrace);
        System.out.print("Ordered Trace: ");
        printTrace(orderedTrace);
        System.out.print("Strict Ordered Trace: ");
        printTrace(strictOrderedTrace);
        System.out.print("Fair Trace: ");
        printTrace(fairTrace);
    }

    public static void printTrace(int[] trace) {
        for (int i = 0; i < trace.length; i++) {
            System.out.print(trace[i] + (i < trace.length - 1 ? ", " : ""));
        }
        System.out.println();
    }
}
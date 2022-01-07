import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        runTests();
    }

    private static void runTests() throws IOException {
        ArrayList<String> words13 = readDataFromFile("src/TestFiles/words-13-data.txt");
        Digraph digraph13 = new Digraph(words13);
        System.out.println("Test 1. 13 words:");
        readFromTestFile(digraph13, "src/TestFiles/words-13-test.txt");

        System.out.println("Test 2: 250 words:");
        ArrayList<String> words250 = readDataFromFile("src/TestFiles/words-250-data.txt");
        Digraph digraph250 = new Digraph(words250);
        readFromTestFile(digraph250, "src/TestFiles/words-250-test.txt");

        System.out.println("Test 3: 5757 words");
        ArrayList<String> words5757 = readDataFromFile("src/TestFiles/words-5757-data.txt");
        Digraph digraph5757 = new Digraph(words5757);
        readFromTestFile(digraph5757, "src/TestFiles/words-5757-test.txt");
    }

    public static ArrayList<String> readDataFromFile(String filename) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        ArrayList<String> words = new ArrayList<String>();
        while (true) {
            String word = r.readLine();
            if (word == null) {
                break;
            }
            assert word.length() == 5;  // indatakoll, om man kör med assertions på
            words.add(word);
        }
        return words;
    }

    public static void readFromTestFile(Digraph digraph, String path) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        while (true) {
            String line = r.readLine();
            if (line == null) {
                break;
            }
            assert line.length() == 11; // indatakoll, om man kör med assertions på
            String start = line.substring(0, 5);
            String goal = line.substring(6, 11);

            int startNode = digraph.getNodePosition(start);
            int goalNode = digraph.getNodePosition(goal);
            BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(digraph, startNode);
            System.out.println(bfs.distTo(goalNode));
        }
    }
}

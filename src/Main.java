import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
Author: Benny Pettersoson, Frida Jacobsson
DA29DA
 */
public class Main {

    public static void main(String[] args) throws IOException {
        runTests();
    }

    private static void runTests() throws IOException {
        ArrayList<String> words13 = readDataFromFile("src/words-13-data.txt");
        Digraph digraph13 = new Digraph(words13);
        System.out.println("Test 1. 13 words:");
        if(readFromTestFile(digraph13, "src/words-13-test.txt", "src/words-13-output-bfs.txt")){
            System.out.println("Test 1 OK");
        }

        System.out.println("Test 2: 250 words:");
        ArrayList<String> words250 = readDataFromFile("src/words-250-data.txt");
        Digraph digraph250 = new Digraph(words250);
        if(readFromTestFile(digraph250, "src/words-250-test.txt", "src/words-250-output-bfs.txt")){
            System.out.println("Test 2 OK");
        }

        System.out.println("Test 3: 5757 words");
        ArrayList<String> words5757 = readDataFromFile("src/words-5757-data.txt");
        Digraph digraph5757 = new Digraph(words5757);
        if(readFromTestFile(digraph5757, "src/words-5757-test.txt", "src/words-5757-output-bfs.txt")){
            System.out.println("Test 3 OK");
        }
    }

    private static ArrayList<String> readDataFromFile(String filename) throws IOException {
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

    private static boolean readFromTestFile(Digraph digraph, String path, String outputPath) throws IOException {
        boolean testOK = true;
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
        BufferedReader outputReader = new BufferedReader(new InputStreamReader(new FileInputStream(outputPath)));
        while (true) {
            String line = fileReader.readLine();
            String answer = outputReader.readLine();
            if (line == null) {
                break;
            }
            assert line.length() == 11; // indatakoll, om man kör med assertions på
            String start = line.substring(0, 5);
            String goal = line.substring(6, 11);

            int startNode = digraph.getNodePosition(start);
            int goalNode = digraph.getNodePosition(goal);
            BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(digraph, startNode);
            int distTo = bfs.distTo(goalNode);
            System.out.println(distTo);
            if (distTo != Integer.parseInt(answer)) {
                testOK = false;
            }
        }
        return testOK;
    }
}

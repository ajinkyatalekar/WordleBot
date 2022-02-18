import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Wordle {

    public ArrayList<String> words;

    public String[] conf;
    public ArrayList<Letter> confHalf;
    public ArrayList<String> confNon;

    public Wordle() throws IOException {
        words = new ArrayList<>();
        conf = new String[5];
        confHalf = new ArrayList<>();
        confNon = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        readWords("words.txt");
        // newWord();
        while (true) {
            String word = newWord();

            System.out.println();
            System.out.println(word);
            System.out.println();
            System.out.println("0: Not in word, 1: Wrong location, 2: Correct");

            for (int i = 0; i < 5; i++) {
                System.out.print("Char " + i + ": ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                String letter = Character.toString(word.charAt(i));
                if (choice == 0) {
                    if (!confNon.contains(letter))
                        confNon.add(letter);
                } else
                if (choice == 1) {
                    int contains = -1;
                    for (int j = 0; j < confHalf.size(); j++) {
                        if (confHalf.get(j).letter.equals(letter)) {
                            contains = j;
                            break;
                        }
                    }

                    if (contains == -1) {
                        Letter l = new Letter(letter);
                        l.addIndex(i);
                        confHalf.add(l);
                    } else {
                        confHalf.get(contains).addIndex(i);
                    }

                } else
                if (choice == 2) {
                    conf[i] = Character.toString(word.charAt(i));
                }
            }
        }
    }

    public boolean validateWord(String cw) {
                   
        for (int i = 0; i < 5; i++) {
            String letter = Character.toString(cw.charAt(i));

            // Confirmed with position
            if (conf[i] != null) {
                if (!conf[i].equals(letter)) {
                    return false;
                } else {
                    continue;
                }
            }
            
            // Confirmed without position
            int contains = -1;
            for (int j = 0; j < confHalf.size(); j++) {
                if (confHalf.get(j).letter.equals(letter)) {
                    contains = j;
                    break;
                }
            }

            if (contains != -1) {
                if (confHalf.get(contains).indexes.contains(i)) {
                    return false;
                }
            } else

                // Confirmed not there
                if (confNon.contains(letter)) {
                    return false;
                }
            }
            
            for (int j = 0; j < confHalf.size(); j++) {
                if (!cw.contains(confHalf.get(j).letter)) {
                    return false;
                }
            }
            
            // System.out.println(cw);
            return true;

    }

    public String newWord() {
        String favourableWord = "";
        double favourableFreq = 0;

        for (int i = 0; i < words.size(); i++) {
            String cw = words.get(i);
            if (validateWord(cw)) {
                double tempFreq = 0;
                for (int j = 0; j < 5; j++) {

                    boolean add = true;
                    for (int k = j-1; k >= 0; k--) {
                        if (cw.charAt(j) == cw.charAt(k)) {
                            add = false;
                        }
                    }
                    if (add) {
                        tempFreq += getFreq(cw.charAt(j));
                    }
                }
                
                if (tempFreq > favourableFreq) {
                    favourableFreq = tempFreq;
                    favourableWord = cw;
                }
            }
        }

        return favourableWord;
    }

    // Takes words from txt and puts them in the array
    private void readWords(String path) throws IOException {
        File file = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while((st = br.readLine()) != null) {
            words.add(st);
        }
    }

    // Frequency of all letters in the alphabet
    private double getFreq(char c) {
        double letterFreq = 0;

        switch(c) {
            case 'a': letterFreq = 8.4966; break;
            case 'b': letterFreq = 2.0720; break;
            case 'c': letterFreq = 4.5388; break;
            case 'd': letterFreq = 3.3844; break;
            case 'e': letterFreq = 11.1607; break;
            case 'f': letterFreq = 1.8121; break;
            case 'g': letterFreq = 2.4705; break;
            case 'h': letterFreq = 3.0034; break;
            case 'i': letterFreq = 7.5448; break;
            case 'j': letterFreq = 0.1965; break;
            case 'k': letterFreq = 1.1016; break;
            case 'l': letterFreq = 5.4893; break;
            case 'm': letterFreq = 3.0129; break;
            case 'n': letterFreq = 6.6544; break;
            case 'o': letterFreq = 7.1635; break;
            case 'p': letterFreq = 3.1671; break;
            case 'q': letterFreq = 0.1962; break;
            case 'r': letterFreq = 7.5809; break;
            case 's': letterFreq = 5.7351; break;
            case 't': letterFreq = 6.9509; break;
            case 'u': letterFreq = 3.6308; break;
            case 'v': letterFreq = 1.0074; break;
            case 'w': letterFreq = 1.2899; break;
            case 'x': letterFreq = 0.2902; break;
            case 'y': letterFreq = 1.7779; break;
            case 'z': letterFreq = 0.2722; break;
        }

        return letterFreq;
    }
}

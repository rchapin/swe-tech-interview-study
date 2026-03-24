import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;

public class SubstituteSynonyms {

    public static List<String> substituteSynonyms(String sentence, Map<String, List<String>> synonyms) {
        List<String> retVal = new ArrayList<>();

        if (sentence.isEmpty()) {
            retVal.add("");
            return retVal;
        }

        // Split our string by whitespace.
        String[] tokens = sentence.split("\\s");

        // Otherwise, "remove" the first word from the sentence recurse with
        // this subset of the input data.
        String removed = tokens[0];
        String newSentence = sentence.substring(removed.length()).strip();
        List<String> result = substituteSynonyms(newSentence, synonyms);

        // For each String returned from our recursive call we will either add
        // just the current token, or one of each of the synonmys provided
        for (String r : result) {
            if (!r.isEmpty()) {
                r = " " + r;
            }
            if (synonyms.containsKey(removed)) {
                List<String> syns = synonyms.get(removed);
                for (String s : syns) {
                    retVal.add(s  +  r);
                }
            } else {
                retVal.add(removed + r);
            }
        }

        return retVal;
    }

    public static void printResult(String sentence, List<String> result) {
        System.out.println(sentence);
        Collections.sort(result);
        for (String r : result) {
            System.out.printf("  %s%n", r);
        }
    }

    public static void main(String[] args) {
        List<Input> input = new ArrayList<>();

        Input i1 = new Input("follow the yellow brick road");
        i1.addSynonym("follow", "chase pursue");
        i1.addSynonym("yellow", "gold amber lemon");
        input.add(i1);

        Input i2 = new Input("I think it's gonna be a long long time");
        i2.addSynonym("think", "believe reckon");
        i2.addSynonym("long", "lengthy prolonged");
        input.add(i2);

        Input i3 = new Input("palms sweaty knees weak arms heavy");
        i3.addSynonym("palms", "hands fists");
        i3.addSynonym("heavy", "weighty hefty burdensome");
        i3.addSynonym("weak", "fragile feeble frail sickly");
        input.add(i3);

        for (Input i : input) {
            printResult(i.sentence, substituteSynonyms(i.sentence, i.synonyms));
        }
    }

    public static class Input {
        String sentence;
        Map<String, List<String>> synonyms;

        public Input(String sentence) {
            this.sentence = sentence;
            this.synonyms = new HashMap<>();
        }

        public void addSynonym(String key, String synonyms) {
            List<String> syns = Arrays.asList(synonyms.split("\\s"));
            this.synonyms.put(key, syns);
        }
    }
}

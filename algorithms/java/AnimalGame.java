import java.nio.file.attribute.DosFileAttributeView;
import java.util.Scanner;

import javax.xml.catalog.Catalog;

public class AnimalGame {

  // Node root = new Node("Is it on land");
  // Node dolphin = new Node("dolphin");
  // Node elephant = new Node("elephant");root.no=dolphin;root.yes=elephant;

  static void play(Node startNode) {
    // If this node does not have any children we need to ask if this
    // is the animal in question.

//    if (!ask(startNode.value)) {
//      // Ask for what animal that they were thinking of and a question
//      // that would uniquely identify the animal.
//
//    }

  }

  // // At the leaf and
  // if (!hasChildren) {
  // ask("is is a " + root.value);
  // boolean resp = getResponse();

  // // root => elephant

  // // If true, we guessed it and the computer wins.
  // if (!resp) {
  // // We need to ask them a question and then update the tree with
  // // the additional knowledge.
  // // Magical java thing that I know this can't do.
  // // new? = does it slither
  // // animal = snake

  // String newQuestion, newAnimal = askDefinitiveQuestion();
  // String oldAnimal = root.val;
  // root.val = newQuestion;

  // // Sort this out so we have some nice terniary logic here and
  // // can have a single reference and clean up all sorts of messy
  // // if/then/else.
  // Node newNode = new Node(oldAnimal);
  // root.no = newNode;

  // Node newYes = new Node(newAnimal);
  // root.yes = newYes;
  // }
  // }

  // ask(node.value);
  // String answer = getResponse();

  // // Check out children and see if either yes or no matches
  // Node resolvedNode = null;
  // if (!answer) {
  // resolvedNode = root.no;
  // } else {
  // resolvedNode = root.yes;
  // }

  // play(resolvedNode);
  // }

  static Node rootNode;
  {
    rootNode = new Node("Is a land animal");
    Node dolphin = new Node("dolphin");
    Node elephant = new Node("elephant");
    rootNode.no = dolphin;
    rootNode.yes = elephant;
  }

  static boolean askYesNo(String question) {
    boolean retVal = false;
    String answer = askQuestion(String.format("Enter 'yes' or 'no'; %s? ", question));
    retVal = Boolean.parseBoolean(answer);
    return retVal;
  }

  static DefinitiveAnswer askWhatAnimal() {
    String animal = askQuestion("What animal is it");
    String question= askQuestion("What is a question specific to this animal");
    return new DefinitiveAnswer(animal, question);
  }

  static String askQuestion(String question) {
    String retVal = null;
    try (Scanner scanner = new Scanner(System.in)) {
      System.out.printf("%s? ", question);
      retVal = scanner.nextLine();
      scanner.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return retVal;
  }

  public static void main(String[] args) {

  }

  public static class DefinitiveAnswer {
    String animal;
    String question;

    public DefinitiveAnswer(String animal, String question) {
      this.animal = animal;
      this.question = question;
    }
  }

  public static class Node {
    String value;
    Node no;
    Node yes;

    public boolean hasChildren() {
      if (no != null && yes != null) {
        return true;
      }
      return false;
    }

    public Node(String value) {
      this.value = value;
    }
  }
}

import java.util.Arrays;
import java.util.Scanner;
import java.util.Deque;
import java.util.ArrayDeque;

public class Trie {

  public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

  Node root;

  public Trie() {
    root = new Node();
  }

  public String show() {
    StringBuilder buf = new StringBuilder();
    Deque<Node> q = new ArrayDeque<>();
    q.add(root);

    while (q.size() > 0) {
      Node node = q.remove();
      buf.append(String.format("%s%n", node.toString()));
      for (Node child : node.children) {
        if (child != null) {
          q.add(child);
        }
      }
    }

    return buf.toString();
  }

  private void delete (Node node) {
    // Check to see if this node has children.  If so, we cannot delete it.
    if (node.hasChildren()) {
      return;
    }

    // We have determined that this is indeed a node for this word in the
    // trie and that it does not have any children, so we can delete it.
    // We also have to removing it from its parents array of children.  We
    // need to figure out the value of the parent, and what the character
    // is for the index of this child in the parent.
    String parentVal = node.value.substring(0,  node.value.length()-1);
    char parentIdxChar = node.value.charAt(node.value.length()-1);
    Node parent = search(parentVal);
    if (parent == null) {
      return;
    }
    int childIdx = ALPHABET.indexOf(parentIdxChar);
    parent.children[childIdx] = null;
    System.out.printf("Node for word=[%s] deleted, parent now=%s", node.value, parent.toString());
    // Now we need to recursively delete the parent nodes until we find another
    // word/leaf.
    delete(parent);
  }

  private void delete(String word) {
    if (word == null) {
      return;
    }
    System.out.printf("Deleting word=[%s]%n", word);
    Node node = search(word);
    delete(node);
  }

  private boolean add(String[] words) {
    System.out.printf("Adding words=");
    for (String word : words) {
      System.out.printf("[%s] ", word);
    }
    System.out.println();

    boolean retVal = false;

    words:
    for (String word : words) {
      // Each time we start with a new word, we need to start with the root
      // node as the current node.
      Node node = root;

      // Now we loop through all of the characters in the word that we are going
      // to add, looking for the correct place to insert it and/or places to
      // create nodes in the trie.
      for (int i = 0; i < word.length(); i++) {
        // Get the next character and figure out what its index should be in the
        // node and then see if we already have a child node for this character.
        char c = word.charAt(i);
        int childIdx = ALPHABET.indexOf(c);
        Node child = node.children[childIdx];
        if (child == null) {
          // We do not yet have a child node for this character.  Create one and
          // add it to our current node.  First we need to figure out what the
          // value for the new node should be taking into account the value of
          // what will be its parent node.
          String value = node.value == null ? String.valueOf(c) : node.value + String.valueOf(c);
          child = new Node(value);
          node.children[childIdx] = child;

          // Check to see if we are now at a leaf/finshed adding a word.
          if (value.equals(word)) {
            child.isLeaf = true;
            // Indicate that we added at least one word
            retVal = true;
            continue words;
          }
        }
        // If we get here it means that we are not yet finished processing the
        // word.
        node = child;
      }
    }

    return retVal;
  }

  private Node search(String word) {
    System.out.printf("Searching for word=[%s]%n", word);
    if (word.isEmpty()) {
      return root;
    }
    Node node = root;
    for (int i = 0; i < word.length(); i++) {
      // Get the current character and then the index into the child array that it
      // should be in the current node if it exists.
      char c = word.charAt(i);
      int childIdx = ALPHABET.indexOf(c);
      Node child = node.children[childIdx];
      if (child != null) {
        if (child.value.equals(word)) {
          // We have a cache hit
          return child;
        }
      } else {
        // If there are no children for the path that we are currently following
        // we can stop searching right now.
        return null;
      }

      // If we get here, there could still be further child nodes that contain
      // the word for which we are searching.  Update our node pointer and
      // continue searching.
      node = child;
    }

    return null;
  }

  public static void main(String[] args) {
    Trie t = new Trie();
    System.out.printf("Following commands = %s%n", Arrays.asList(CMD.values()));
    System.out.println("Enter a command, followed by a \":\" and the required argument and press ENTER");
    Scanner scanner = new Scanner(System.in);
    t.add(new String[] {"blorch", "blah", "and", "an", "ant", "arm"});
    try {
      while (true) {
        String line = scanner.nextLine();
        String[] tokens = null;
        CMD cmd = null;
        if (line.contains(":")) {
            tokens = line.split(":");
            cmd = CMD.valueOf(tokens[0]);
        } else {
          cmd = CMD.valueOf(line.strip());
        }

        switch (cmd) {
          case ADD:
            String[] words = tokens[1].strip().split("\\s");
            System.out.println(t.add(words));
            break;
          case DELETE:
            System.out.println("del");
            t.delete(tokens[1].strip());
            break;
          case SEARCH:
            String word = tokens[1].strip();
            Node n = t.search(word);
            String response = n != null ? String.format("Found %s%n", n) : String.format("Did not find [%s]%n", word);
            System.out.println(response);
            break;
          case SHOW:
            System.out.println(t.show());
          default:
            break;
        }
      }
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      scanner.close();
    }
  }

  public static class Node {
    Node[] children = new Node[26];
    boolean isLeaf;
    String value;

    public Node() {}
    public Node(String value) {
      this.value = value;
    }

    public boolean hasChildren() {
      for (Node child : children) {
        if (child != null) {
          return true;
        }
      }
      return false;
    }

    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder();
      buf.append("|");
      for (int i = 0; i < children.length; i++) {
        Node child = children[i];
        if (child != null) {
          buf.append(String.format("%c:%s|", ALPHABET.charAt(i), child.value));
        } else {
          buf.append(" |");
        }
      }
      buf.append("");

      return String.format("Node=[value=%s, isLeaf=%b, children=%s]", value, isLeaf, buf.toString());
    }
  }

  public enum CMD {

    ADD("Enter a CSV of lowercase words"),
    DELETE("Enter a single lowercase word to delete"),
    SEARCH("Enter a single lowercase word to search for in the trie"),
    SHOW("Prints out the current trie");

    String desc;

    private CMD(String desc) {
      this.desc = desc;
    }

    @Override
    public String toString() {
      return String.format("[%s]: %s", name(), desc);
    }
  }
}

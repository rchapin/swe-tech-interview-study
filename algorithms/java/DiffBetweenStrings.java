import java.io.*;
import java.util.*;

class DiffBetweenStrings {

  static List<String> diff(String source, String target, Map<String, List<String>> memo) {
    String key = String.format("%s:%s", source, target);
    if (memo.containsKey(key)) {
      return memo.get(key);
    }

    List<String> retVal = new ArrayList<>();
    
    if (target.length() == 1 && source.length() == 1 && source.equals(target)) {
      retVal.add(source);
      memo.put(key, retVal);
      return retVal;
    }

    if (source.isEmpty() || target.isEmpty()) {
      String s = null;
      String prefix = null;
      if (source.isEmpty() && !target.isEmpty()) {
        s = target;
        prefix = "+";
      }
      if (!source.isEmpty() && target.isEmpty()) {
        s = source;
        prefix = "-";
      }
      for (char c : s.toCharArray()) {
        retVal.add(prefix + Character.toString(c));
      }
      memo.put(key, retVal);
      return retVal;
    }
    
    if (source.charAt(0) == target.charAt(0)) {
      // Remove that first character and add it to the smallest array that
      // we get back from our recursive call.
      retVal.add(Character.toString(source.charAt(0)));
    
      // Recurse with the source and target with the first character removed
      retVal.addAll(diff(source.substring(1, source.length()), target.substring(1, target.length()), memo));
      memo.put(key, retVal);
      return retVal;
    } 
    
    // If the first characters are not the same, remove a character from each and recurse.
    // We prefix the character from the source with a '-' char and from the target with
    // a "+" to indicate the operation for the given string
    List<String> sourceResult = new ArrayList<>();
    sourceResult.add("-" + source.charAt(0));
    sourceResult.addAll(diff(source.substring(1, source.length()), target, memo));
    
    List<String> targetResult = new ArrayList<>();
    targetResult.add("+" + target.charAt(0));
    targetResult.addAll(diff(source, target.substring(1, target.length()), memo));
    
    // Favor removing from the source first if their is a "tie"
    if (sourceResult.size() == targetResult.size() || sourceResult.size() < targetResult.size()) {
      memo.put(key, sourceResult);
      return sourceResult;
    } else {
      memo.put(key, targetResult);
      return targetResult; 
    }

  }
 
	static String[] diffBetweenTwoStrings(String source, String target) {
    Map<String, List<String>> memo = new HashMap<>();
    List<String> result = diff(source, target, memo);
    String[] retVal = new String[result.size()];
    for (int i = 0; i < result.size(); i++) {
      retVal[i] = result.get(i);
    }
    return retVal;
	}

  public static void printArr(String[] arr) {
    for (String s : arr) {
      System.out.printf("%s ", s);
    }
    System.out.println();
  }
  
	public static void main(String[] args) {
	  printArr(diffBetweenTwoStrings("ADC", "ABC"));
	  printArr(diffBetweenTwoStrings("ABCDEFG", "ABDFFGH"));
	  printArr(diffBetweenTwoStrings("CCBC", "CCBC"));
	}
}

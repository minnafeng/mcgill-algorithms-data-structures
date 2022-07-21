/*

Q3.
The teaching staff in Comp251 is really happy of how our discussion board (Ed) is working;
however, we believe there is one function missing. This function will allow us to identify important
topics (discussed in Ed) by filtering key words. In particular, given a list of messages posted in
Ed, we want a function that reports the words used by every single user on the discussion board.
This list must be sorted from most to least used word (i.e., the word with the highest frequency
must be the first one). In case of a frequency tie, the word needs to be sorted in alphabetical
order.

Let’s see now some features of the discussion board posts. The list of post will be provided to
you as an array of strings (String[]), where every slot in the array will contain a message. All
messages will have the following characteristics.

• Each message is represented in Java as a String.
• Each message begins with a user’s name of no more than 20 characters.
• After the name, each message continues with the content of that user’s post all in lower case.
• The total number of characters across all messages, including spaces, will not exceed 2*10^6.

Given the following list of posts:
David no no no no nobody never
Jennifer why ever not
Parham no not never nobody
Shishir no never know nobody
Alvin why no nobody
Alvin nobody never know why nobody
David never no nobody
Jennifer never never nobody no

Your algorithm must return the array [no, nobody, never] (exactly in that order). Those
three words were used by every single user of our discussion board and they are reported in order
of frequency (i.e., “no” is the most frequent used word). In case of a tie, the order is decided
lexicographically.

Now, if the following list of post is given to you:
David comp
Maria music

Your algorithm must return an empty array [] given that none of the words in the post was
used by every single user.

Implemented entire class.

*/

import java.util.*;

public class DiscussionBoard {

    public static ArrayList<String> Discussion_Board(String[] posts){

        // building userWords HashMap
        HashMap<String,ArrayList<String>> userWords = new HashMap<>(); // maps users to words used

        for (String msg : posts) {
            String[] msgArr = msg.split(" "); // split msg by space

            String name = msgArr[0];
            String[] words = Arrays.copyOfRange(msgArr, 1, msgArr.length);

            if (userWords.containsKey(name)){ // user already in map
                for (String word : words){
                    userWords.get(name).add(word); // add words into existing ArrayList
                }
            } else { // new user
                ArrayList<String> list = new ArrayList<>(); // initialize list to add into userWords

                for (String word : words){
                    list.add(word); // add words into ArrayList
                }
                userWords.put(name,list); //
            }
        }

        // finding words used by every user
        HashSet<String> hs1 = new HashSet<>(); // contains common elements

        for (ArrayList<String> list : userWords.values()){
            if (hs1.size() == 0){ // first iteration
                hs1.addAll(list);
            } else {
                HashSet<String> hs2 = new HashSet<>(list);
                if (hs1.size()>=hs2.size()){
                    hs1.retainAll(hs2); // keep only words in common
                } else {
                    hs2.retainAll(hs1);
                    hs1.clear();
                    hs1.addAll(hs2); // maintain hs1 as main HashSet
                }
            }
            if (hs1.size() == 0){ // reached a point where no words in common
                break;
            }
        }


        // building a grand array of all words used
        ArrayList<String> allWords = new ArrayList<>();

        for (ArrayList<String> list : userWords.values()){
            allWords.addAll(list);
        }

        // building wordFrequency HashMap
        HashMap<String,Integer> wordFrequency = new HashMap<>(); // maps word to frequency

        for (String word : allWords){
            if (!hs1.contains(word)){ // filter out words not in HashSet
                continue;
            }
            if (wordFrequency.containsKey(word)){
                wordFrequency.replace(word, wordFrequency.get(word)+1);
            } else {
                wordFrequency.put(word,1);
            }
        }

        // sorting wordFrequency HashMap by value using Comparator
        List<Map.Entry<String,Integer>> sortedList = new LinkedList<>(wordFrequency.entrySet()); // convert map to list
        Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (!o1.getValue().equals(o2.getValue())){ // if different values
                    return -(o1.getValue().compareTo(o2.getValue())); // negative sign to reverse list
                }
                return o1.getKey().compareTo(o2.getKey()); // compare keys if same value
            }
        });

        // building final ArrayList of sortedWords
        ArrayList<String> sortedWords = new ArrayList<>();

        for (Map.Entry<String,Integer> entry : sortedList){
                sortedWords.add(entry.getKey());
        }

        return sortedWords;
    }


    // testing
    public static void main(String[] args) {
        String[] discussion = {"David no no no no",
                "Jennifer why ever not",
                "Parham no not never nobody",
                "Shishir no never know nobody",
                "Alvin why no nobody",
                "Alvin nobody never know why nobody",
                "David no",
                "Jennifer never never nobody "};
        ArrayList<String> board = Discussion_Board(discussion);
        //System.out.println(board);

    }

}

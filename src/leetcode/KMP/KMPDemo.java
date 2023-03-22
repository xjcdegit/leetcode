package leetcode.KMP;/*
 *
 * @Param
 */

public class KMPDemo {
    public static void main(String[] args) {
        String sequence = "ABAABABABCA";
        String word = "ABABC";
        int match = new KMPDemo().match(sequence, word);
        System.out.println(match);
    }

    public int match(String sequence,String word){
        int[] next = utils.getNext(word);
        int i = 0;//主串中的指针
        int j = 0;//副串中的指针
        int length = word.length();
        while(j < length){
            if(sequence.charAt(i) == word.charAt(j)){
                i++;
                j++;
            }else if(j > 0){
                j = next[j - 1];
            }else{
                j++;
            }
            if(j == length){
                return i - j;
            }
        }
        return -1;
    }
}

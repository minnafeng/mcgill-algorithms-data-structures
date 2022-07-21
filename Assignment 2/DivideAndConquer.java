import java.math.BigInteger;

/*

Q4. 
For this question, you will need to implement a divide and conquer algorithm than given N and K as parameters, 
your function returns the K-th letter in the N-th string in the fibonacci sequence. For example, if N = 7 and 
K = 7, your algorithm must return X (please notice that the N=7 string in the sequence is “XYYXYYXYXYYXY”, and 
the K=7 letter in that string is “X”, which is bold). You can safely assume that K is at most the length of the Nth string.

*/

public class DivideAndConquer {

    public static String mod_fibonacci(int N, BigInteger K) {
        BigInteger[] seq = getSequence(N); // get Fibonacci sequence up to Nth term
        String result = helper(seq, N,K);

        return result;
    }

    public static BigInteger[] getSequence(int N){
        BigInteger[] seq = new BigInteger[N];
        BigInteger first = BigInteger.ONE, next = BigInteger.ONE;
        seq[0] = first;
        if (N == 1){
            return seq;
        }
        seq[1] = next;
        if (N == 2){
            return seq;
        }

        int i;
        for (i=2; i<N; i++){
            seq[i] = first.add(next);
            first = next;
            next = seq[i];
        }
        return seq;
    }

    public static String helper(BigInteger[] seq, int N, BigInteger K){
        if (N == 2){
            return "Y";
        } else if (N <= 3){
            if (K.equals(BigInteger.ONE)){
                return "X";
            }
            return "Y";
        }

        BigInteger lengthFirst = seq[N-3];
        if (K.compareTo(lengthFirst) > 0){ // K is in second term
            K = K.subtract(lengthFirst); // update K in new term
            return helper(seq,N-1,K);
        }
        return helper(seq,N-2, K); // else K is in first term
    }

    // testing
    public static void main(String[] args) {

    }

}

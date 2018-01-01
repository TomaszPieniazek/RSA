import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

public class Decryption {
    private static Integer e;
    private static Integer n;
    private static Integer p;
    private static Integer q;
    private static Integer phi;
    private static BigInteger d;
    private static String link="http://xor.math.uni.lodz.pl/~frydrych/crypto/exercises/Ex_0000013_RSA.txt";

    public static Integer powm( Integer a, Integer n, Integer p ) {
        Integer	sq, y;
        String binary;

        for( y=1, sq=a%p; n>0; n>>=1, sq*=sq, sq%=p ) {
            binary=Integer.toBinaryString(n);
            char last=binary.charAt(binary.length()-1);
            int lastValue=Character.getNumericValue(last);

            if (lastValue ==1) {
                y *= sq;
                y %= p;
            }
        }
        return y;
    }

public static ArrayList<Integer> primeFactors(int number){
     ArrayList<Integer> factors= new ArrayList<>();
    for(int i=2; i<(number/i); i++) {
        if (number % i == 0) {
            number = number / i;
            factors.add(number);
            factors.add(i);
        }
    }
            return factors;
}

public static Integer phiEuler(Integer p, Integer q){
    return ((p-1)*(q-1));
}

public static BigInteger privateKey(Integer e, Integer phi){
    BigInteger key;
    BigInteger b= BigInteger.valueOf(e);
    key= b.modInverse(BigInteger.valueOf(phi)) ;
    return key;
}

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect(link).get();
        String textContents;
        textContents= doc.text();
        textContents=textContents.substring(textContents.lastIndexOf('(')+2);

        n=Integer.parseInt( textContents.substring(0,textContents.indexOf(',')));
        e=Integer.parseInt(textContents.substring(textContents.indexOf(',')+2,textContents.indexOf(')')-1));

        textContents=textContents.substring(textContents.indexOf(':')+1,textContents.indexOf('/'));
        textContents=textContents.trim();

    ArrayList<Integer> a=primeFactors(n);
    p=a.remove(1);
    q=a.remove(0);
    phi=phiEuler(p,q);
    d=privateKey(e,phi);
    Alphabete alfa=new Alphabete();

    StringBuilder decodedText=new StringBuilder();
    String decodeTextString;
    String codedFragment;
    int codedFragmentInt;
    Integer alphabeteIndex;
        for(int i=0;i<textContents.length();i=i+7){
            codedFragment=textContents.substring(i,i+6);
            codedFragmentInt=Integer.parseInt(codedFragment);
            alphabeteIndex=powm(codedFragmentInt,d.intValue(),n);
            decodedText.append(alfa.mapa.get(alphabeteIndex));
        }
        decodeTextString=decodedText.toString();

System.out.print(decodeTextString.replace("   ","\n"));

System.out.print("\n"+textContents);
    }
}



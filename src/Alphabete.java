import java.util.HashMap;

public class Alphabete {
    private static String alp= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz~!@#$%^&*()_+`1234567890-=<>?:\"{}|,./;'[]\\ ĄĆĘŁŃÓŚŹŻąćęłńóśźż";
    public HashMap<Integer, Character> mapa=new HashMap<>();

    public Alphabete(){
        for(int i=2;i<115;i++){
            mapa.put(i,alp.charAt(i-2));
        }
    }
}

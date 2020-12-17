public class Main {
    static DataAccess dataAccess;

    public static void main(String[] args) {


int n=0;
do {
    inputUSERNameAndPassword in = new inputUSERNameAndPassword();
    in.showInputUSERNameAndPassword();
}while (n==0);
    }

    static public void init(){
        try{
            dataAccess = new DataAccess();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    static public void stop(){
        try{
            dataAccess.closeDB();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
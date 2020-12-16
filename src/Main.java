public class Main {
    static DataAccess dataAccess;

    public static void main(String[] args) {
        inputUSERNameAndPassword n=new  inputUSERNameAndPassword();
        n.showInputUSERNameAndPassword();
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
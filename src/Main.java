import java.sql.*;
import java.util.*;

public class Main {
    static DataAccess dataAccess;

    public static void main(String[] args) {
        init();
        new Menu(dataAccess);
        stop();
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
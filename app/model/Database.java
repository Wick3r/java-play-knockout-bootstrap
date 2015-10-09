package model;

import model.dtos.Meal;
import model.dtos.Reservation;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Daniel on 09.10.2015.
 */
public class Database {

    private static Database instance = null;
    private static final String FILE_PATH = "./reservations.obj";
    private HashMap<Integer, List<Reservation>> reservations;
    private int counter = 1000;

    private Database() throws IOException, ClassNotFoundException {
        File db = new File(FILE_PATH);
        if(!db.exists()) reservations = new HashMap<Integer, List<Reservation>>();
        else {
            reservations = (HashMap<Integer, List<Reservation>>) new ObjectInputStream(new FileInputStream(db)).readObject();
            counter += reservations.size();
        }
    }

    public static Database getInstance() throws IOException, ClassNotFoundException {
        return (instance == null) ? new Database() : instance;
    }

    public List<Meal> getAvailableMeals(){
        //Just Mock Data
        ArrayList<Meal> list = new ArrayList<Meal>(3);
        list.add(new Meal("-Economy- Laugengebäck" , 0.00));
        list.add(new Meal("-Business- Schweinskopf" , 5.85));
        list.add(new Meal("-First Class- Rindersteak" , 15.50));

        return list;
    }

    public List<Reservation> getReservations(int code){
        return reservations.get(code);
    }

    public int saveReservations(List<Reservation> newReservations) throws IOException {
        reservations.put(counter++, newReservations);
        File db = new File(FILE_PATH);
        new ObjectOutputStream(new FileOutputStream(db)).writeObject(reservations);
        return counter-1; //to get the 'old' one
    }

}

package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.Database;
import model.dtos.Reservation;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

/**
 * Created by Daniel on 09.10.2015.
 */
public class JsonController extends Controller {

    public static Result getAvailableMeals(){
        JsonNode result = null;
        try {
            result = Json.toJson(Database.getInstance().getAvailableMeals());
        } catch (Exception e) {
            e.printStackTrace();
            return internalServerError();
        }
        return ok(result);
    }

    public static Result saveReservation(){
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        } else {
            List<Reservation> input = Json.fromJson(json, List.class);
            int resCode = 0;
            try {
                resCode = Database.getInstance().saveReservations(input);
            } catch (Exception e) {
                e.printStackTrace();
                return internalServerError();
            }
            return ok("Your Reservation-Code ist: "+resCode);
        }
    }

    public static Result loadReservation(){
        JsonNode result = null;
        System.out.println("RawJson: " + request().body().asJson().toString());
        try {
            result = Json.toJson(Database.getInstance().getReservations(Integer.parseInt(request().body().asJson().get("reservationCode").asText())));
        } catch (Exception e) {
            e.printStackTrace();
            return badRequest();
        }

        if(result == null) return badRequest("Invalid Code");
        System.out.println(result.toString());
        return ok(result);
    }

}

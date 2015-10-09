/**
 * Created by Daniel on 08.10.2015.
 */
// Class to represent a row in the seat reservations grid
function SeatReservation(name, initialMeal) {
    var self = this;
    self.name = name;
    self.meal = ko.observable(initialMeal);

    self.formattedPrice = ko.computed(function() {
        var price = self.meal().price;
        return price ? "$" + price.toFixed(2) : "None";
    });
}

// Overall viewmodel for this screen, along with initial state
function ReservationsViewModel() {
    var self = this;

    // Non-editable catalog data - would come from the server
    /*self.availableMeals = [
        { mealName: "Standard (sandwich)", price: 0 },
        { mealName: "Premium (lobster)", price: 34.95 },
        { mealName: "Ultimate (whole zebra)", price: 290 }
    ]; */

    // Load initial state from server, convert it to Task instances, then populate self.tasks
    $.getJSON("/json/availableMeals", function(result){
        self.availableMeals = result;
    });

    // Editable data
    self.seats = ko.observableArray([]);

    self.addSeat = function(){
        self.seats.push(new SeatReservation("", self.availableMeals[0]));
    };

    self.removeSeat = function(seat) { self.seats.remove(seat) }

    self.totalSurcharge = ko.computed(function() {
        var total = 0;
        for(var i = 0; i < self.seats().length; i++){
            total += self.seats()[i].meal().price;
        }
        return total //return 5;
    });

    self.loadReservation = function() {
        $.ajax("/json/loadReservation", {
            data: JSON.stringify({reservationCode: self.reservationCode()}),
            type: "post", contentType: "application/json",
            success: function(data) {
                //self.seats(data);
                //alert(data);
                for(var i = 0; i < data.length; i++){
                    self.seats.push(new SeatReservation(data[i].name, data[i].meal));
                }

            },
            error: function (err)
            { alert(err.responseText)}
        });
    };

    self.saveReservation = function() {
        $.ajax("/json/saveReservation", {
            data: ko.toJSON(self.seats()),
            type: "post", contentType: "application/json",
            success: function(result) { alert(result) }
        });
    };

    self.reservationCode = ko.observable("");
}

ko.applyBindings(new ReservationsViewModel());



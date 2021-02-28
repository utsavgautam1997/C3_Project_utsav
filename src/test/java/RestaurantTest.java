import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    RestaurantService service= new RestaurantService();
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void set()
    {
        restaurant= new Restaurant("madras cafe","sahibabad",LocalTime.parse("10:00:00"),LocalTime.parse("22:00:00"));
        restaurant.addToMenu("chilly potato",100);
        restaurant.addToMenu("paneer pasanda",200);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");

        LocalTime current = LocalTime.parse("16:30:00");
       restaurant= new Restaurant("madras cafe","sahibabad",openingTime,closingTime);
       restaurant.addToMenu("chilly potato",100);
       restaurant.addToMenu("chicken rice",180);


        assertTrue(current.isAfter(restaurant.openingTime)&& current.isBefore(restaurant.closingTime));
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");

        LocalTime current = LocalTime.parse("16:30:00");
        restaurant= new Restaurant("madras cafe","sahibabad",openingTime,closingTime);
        restaurant.addToMenu("chilly potato",100);
        restaurant.addToMenu("chicken rice",180);


        assertFalse(current.isAfter(restaurant.closingTime)&& current.isBefore(restaurant.openingTime));

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>



    @Test

    public void select_item_from_menu_of_restaruant_and_return_total_cost_of_items()
    {


        List<String> myOrders= new ArrayList<>();
        myOrders.add("chilly potato");
        myOrders.add("paneer pasanda");
        Assertions.assertEquals(300,restaurant.getTotalValue(myOrders));


    }
    @Test
    public void selecting_non_existing_item_from_restaurant_should_throw_null_point_nullpointexception()
    {
        List<String> myOrders= new ArrayList<>();
        myOrders.add("pastry");
        myOrders.add("icecream");
        Assertions.assertThrows(NullPointerException.class,()->restaurant.getTotalValue(myOrders));
    }
}
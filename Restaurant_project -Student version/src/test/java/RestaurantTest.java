import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        boolean result=restaurant.isRestaurantOpen();
        Assertions.assertEquals(true, result);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        Restaurant spyRestaurant= Mockito.spy(restaurant);
        LocalTime currentTime=LocalTime.parse("22:30:00");
        Mockito.when(spyRestaurant.getCurrentTime()).thenReturn(currentTime);
        boolean result=spyRestaurant.isRestaurantOpen();
        assertEquals(false,result);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>CART TOTAL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test //pass case test for total cart value
    public void calculating_total_price_of_items_added_in_cart(){
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToCart("Sweet corn soup",119);
        restaurant.addToCart("Vegetable lasagne", 269);
        restaurant.addToCart("Sizzling brownie",319);
        int totalCartValue = restaurant.totalPrice();
        assertEquals(totalCartValue,restaurant.totalPrice());
    }
    @Test //Failed test case for cartItem , if no items are added in cart
    public void cart_items_should_return_false_if_no_items_are_added_in_cart(){
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        boolean result=restaurant.cartItems();
        assertEquals(false,result);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<CART TOTAL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}
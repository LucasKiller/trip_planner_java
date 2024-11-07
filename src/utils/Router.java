package utils;

import java.sql.Connection;
import java.sql.SQLException;

import classes.ManageUserLogin;
import classes.Request;
import classes.Response;
import entities.Carro;
import entities.Hotel;
import entities.User;
import entities.Viagem;
import enums.RequestType;
import enums.ResponseType;

public class Router {

    private static ManageUserLogin manager = new ManageUserLogin();

    public static Response getResponse(Request request, Connection conn) {

        RequestType type = request.getType();
        Response res = null;
        User user;
        Hotel hotel;
        Carro carro;
        Viagem trip;
        int result;
        
        switch(type) {

            case GET_USER:

                user = manager.getUser();

                res = new Response(ResponseType.SUCCESS, user);

                break;
        
            case LOG_USER:

                user = (User) request.getParameters()[0];

                result = manager.logUser(conn, user);

                if(result == -1) {
                    res = new Response(ResponseType.USER_NOT_LOGGED, new Object[0]);
                } else {
                    res = new Response(ResponseType.USER_LOGGED, new Object[0]);
                }

                break;
            
            case CREATE_USER:
            
                user = (User) request.getParameters()[0];

                result = manager.registerUser(conn, user);

                if(result == -1) {
                    res = new Response(ResponseType.USER_NOT_CREATED, new Object[0]);
                } else {
                    res = new Response(ResponseType.USER_CREATED, new Object[0]);
                }

                break;

            case GET_TRIPS:
                
                Object[] viagens = GetTrips.getTrips(conn, manager);

                res = new Response(ResponseType.RETURN_TRIPS, viagens);

                break;

            case CREATE_TRIP:
                
                hotel = (Hotel) request.getParameters()[0];
                carro = (Carro) request.getParameters()[1];
                trip = (Viagem) request.getParameters()[2];

                try {
                    hotel.inserir(conn);
                    conn.commit();
                    carro.inserir(conn);
                    conn.commit();
                    trip.inserir(conn);
                    conn.commit();
                } catch (SQLException sql_ex) {
                    sql_ex.printStackTrace();
                } 

                res = new Response(ResponseType.TRIP_CREATED, new Object[0]);

        }

        return res;

    }

}

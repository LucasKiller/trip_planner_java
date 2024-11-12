package utils;

import java.sql.Connection;
import java.sql.SQLException;

import classes.LoginAndRegisterUser;
import classes.Request;
import classes.Response;
import entities.*;
import enums.*;

public class Router {

    private static LoginAndRegisterUser logAndRegister = new LoginAndRegisterUser();

    public static Response getResponse(Request request, Connection conn) {

        RequestType type = request.getType();
        Response res = null;
        User user;
        Hotel hotel;
        Carro carro;
        Viagem trip;
        int result;
        
        switch(type) {

            case LOG_USER:

                user = (User) request.getParameters()[0];

                result = logAndRegister.logUser(conn, user);

                if(result == -1) {
                    res = new Response(ResponseType.USER_NOT_LOGGED, new Object[0]);
                } else {
                    res = new Response(ResponseType.USER_LOGGED, logAndRegister.getUser());
                }

                break;
            
            case CREATE_USER:
            
                user = (User) request.getParameters()[0];

                result = logAndRegister.registerUser(conn, user);

                if(result == -1) {
                    res = new Response(ResponseType.USER_NOT_CREATED, new Object[0]);
                } else {
                    res = new Response(ResponseType.USER_CREATED, new Object[0]);
                }

                break;

            case GET_TRIPS:
                
                Object[] viagens = GetTrips.getTrips(conn, logAndRegister);

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

                break;

            case UPDATE_TRIP:
                
                hotel = (Hotel) request.getParameters()[0];
                carro = (Carro) request.getParameters()[1];
                trip = (Viagem) request.getParameters()[2];

                try {
                    hotel.atualiza(conn);
                    conn.commit();
                    carro.atualiza(conn);
                    conn.commit();
                    trip.atualiza(conn);
                    conn.commit();
                } catch (SQLException sql_ex) {
                    sql_ex.printStackTrace();
                } 

                res = new Response(ResponseType.TRIP_UPDATED, new Object[0]);

                break;

            case DELETE_TRIP:

                trip = (Viagem) request.getParameters()[0];

                try {
                    trip.excluir(conn);
                    conn.commit();
                } catch (SQLException sql_ex) {
                    sql_ex.printStackTrace();
                }

                res = new Response(ResponseType.TRIP_DELETED, new Object[0]);

                break;

            default:

                res = new Response(ResponseType.NON_EXISTENT, new Object[0]);

        }

        return res;

    }

}

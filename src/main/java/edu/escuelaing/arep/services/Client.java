package edu.escuelaing.arep.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        
        try{
            URL herokuURL = new URL("https://gentle-atoll-84797.herokuapp.com/inputdata");

            System.out.println("Ponga los datos a procesar separados por comas");

            Scanner input = new Scanner(System.in);
            String numbers = input.nextLine();
            System.out.println("Datos a procesar: "+numbers);
            String[] list = numbers.split(",");
            String value2 = "";

            for(String i :list){
                if(i!=list[list.length-1]){
                    value2=value2+i+"%2C";
                }else{
                    value2=value2+i;
                }
            }
            streamURL(value2);
        } catch (IOException e) {
            System.out.println("Error");
        }

    }

    public static void streamURL(String value) throws Exception {
        URL herokuURL = new URL("https://gentle-atoll-84797.herokuapp.com/processdata?value="+value);

        try (BufferedReader reader = 
            new BufferedReader(new InputStreamReader(herokuURL.openStream()))){
                String inputLine = null;
                while ((inputLine = reader.readLine()) != null){
                    System.out.println(inputLine);
                }
            
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

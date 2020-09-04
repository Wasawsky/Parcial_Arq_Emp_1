package edu.escuelaing.arep.Spark;

import static spark.Spark.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.escuelaing.arep.logic.App;

import spark.*;

/**
 * Spark 
 * Usa metodos lambda y opera los datos para ofrecer el servicio web
 */
public class SparkWebApp {

    private static App app;
    /**
     * Metodo Principal del programa
     * @param args Argumento
     */
    public static void main(String[] args) {

        //int portSkt = getPort();
        //System.out.println("Listening on Port: " + portSkt);
        //port(portSkt);
        
        port(getPort());
        app = new App();
        //get("/hello", (req, res) -> "Hello Heroku");
        get("/inputdata", (req, res) -> inputDataPage(req, res));
        get("/processdata", (req, res) -> processDataPage(req, res));
    }

    /**
     * Metodo para procesar los datos que lleguen de la pagina web
     * @param req Peticion a la pagina
     * @param res Respuesta
     * @return
     */
    private static String processDataPage(Request req, Response res) {

        String[] list = req.queryParams("value").split(",");
        prepareData(list);

        app.bubbleSortNumbers();
        app.calculateMean();

        ObjectMapper mapper = new ObjectMapper();
        try {
            String jsonApp = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(app);
            System.out.println(jsonApp);
            return jsonApp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void prepareData(String[] list){
        //String to Double
        Double[] array = new Double[list.length];
        for (int i = 0; i<list.length;i++){
            array[i] = Double.parseDouble(list[i]);
        }
        app.setArray(array);
    }

    /**
     * Entrada inicial a la pagina de datos
     * @param req
     * @param res
     * @return
     */
    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>BubbleSort</h2>"
                + "<p> Ingrese los valores separados por comas</p>"
                + "<form action=\"/processdata\">"
                + "  Datos<br>"
                + "  <input type=\"text\" name=\"value\">"
                + "  <br>"
                + "  <input type=\"submit\" value=\"Enviar\">"
                + "</form>"
                + "<p> Presione enviar para procesar los datos</p>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    /**
     * Determina el puerto donde corre el proceso de la maquina
     * @return puerto
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
        return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set(i.e. on localhost)
        }
}
package edu.escuelaing.arep;

import static spark.Spark.*;

import spark.*;

/**
 * Spark 
 * Usa metodos lambda y opera los datos para ofrecer el servicio web
 */
public class SparkWebApp {

    /**
     * Metodo Principal del programa
     * @param args Argumento
     */
    public static void main(String[] args) {
        port(getPort());
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
        Double[] array = new Double[6];
        String value = "";
        for (int i = 0; i<6;i++){
            value = "number"+(i+1);
            array[i] = Double.parseDouble(req.queryParams(value));
        }

        Calculator calc = new Calculator();
        LinkedList list = new LinkedList(array);

        String result = "Promedio:" +calc.calculateMean(list.toArray())+
                        " Desviacion estandar: "+calc.calculateDeviation(list.toArray());
        return result;
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
                + "<h2>CALCULADORA</h2>"
                + "<p> Esta calculadora permite calcular Promedio y la desviacion estandar de los datos</p>"
                + "<form action=\"/processdata\">"
                + "  Datos<br>"
                + "  <input type=\"text\" name=\"number1\">"
                + "  <br>"
                + "  <input type=\"text\" name=\"number2\">"
                + "  <br>"
                + "  <input type=\"text\" name=\"number3\">"
                + "  <br>"
                + "  <input type=\"text\" name=\"number4\">"
                + "  <br>"
                + "  <input type=\"text\" name=\"number5\">"
                + "  <br>"
                + "  <input type=\"text\" name=\"number6\">"
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
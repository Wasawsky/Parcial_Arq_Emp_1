package edu.escuelaing.arep.logic;

public class App {

    private Double[] array;
    private Double sumax;
    private Double prom;
    
    public App(Double[] array) {
        this.array = array;
    }

    public App() {
	}

	public void bubbleSortNumbers() {
        int n = array.length;
        Double temp = 0d;
        for (int i=0; i<n; i++) {
            for (int j=1; j<(n-i); j++) {
                if (array[j] < array[j-1]) {
                    temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public void calculateMean() {
        Double sum = 0d;
        int n = array.length;
        for (Double x : array) {
            sum += x;
        }
        this.sumax=sum;
        this.prom=sum/n;
    }



    public static void main(String[] args) {

        //Example
        Double[] array = { 3.0, 60.1, 35.2, 2.2, 45., 320., 5. };
        App app = new App(array);
        app.bubbleSortNumbers();
        System.out.println("BubbleSort");
        for (Double i :array) {
            System.out.print(i+ ",");
        }

    }

    public Double[] getArray() {
        return array;
    }

    public void setArray(Double[] array) {
        this.array = array;
    }

    public Double getSumax() {
        return sumax;
    }

    public void setSumax(Double sumax) {
        this.sumax = sumax;
    }

    public Double getProm() {
        return prom;
    }

    public void setProm(Double prom) {
        this.prom = prom;
    }

    @Override
    public String toString(){
        return ""+sumax+prom+array;
    }
}
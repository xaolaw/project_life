package agh.ics.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class world {
    public static void  main(String[] args) throws FileNotFoundException {
        //IEngine engine = new SimulationEngine(18,8,2,15,0.7,40,30);
        //engine.run();
        //Application.launch(App.class);
        File csvFile = new File("test.csv");
        PrintWriter out = new PrintWriter(csvFile);

        out.printf("%s,%d\n","ala",2);
        out.close();
    }
}

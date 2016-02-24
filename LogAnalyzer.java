/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael KÃ¶lling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    /**
     * Método que crea un objeto de la clase LogAnalyzer
     */
    public LogAnalyzer(String archivo)
    {
        hourCounts = new int[24];
        reader = new LogfileReader(archivo);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        int hour = 0;
        System.out.println("Hr: Count");
        while (hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Método que devuelve el número total de accesos al servidor web registrados en el archivo a analizar
     */
    public int numberOfAccesses()
    {
        int numeroDeAccesos = 0;
        if (!reader.hasNext()) {          
            for (int index = 0;index<hourCounts.length; index++) {
                numeroDeAccesos = numeroDeAccesos + hourCounts[index];
            }
        }
        else {
            System.out.println("Este método no puede funcionar aún (invocar antes método analyzeHourlyData)");
        }
        return numeroDeAccesos;
    }
    
    /**
     * Método que devuelve en que hora el servidor ha registrado más accesos (en caso de empate el último con mas registros).Si no hay accesos
     */
    public int busiesHour()
    {
        int horaMasTransitada = -1;
        if (!reader.hasNext()) {
            int mayorRegistro = 0;
            for (int index = 0;index<hourCounts.length; index++) {
                if (hourCounts[index]>= mayorRegistro) {
                    horaMasTransitada = index;
                    mayorRegistro = hourCounts[index];
                }
            }
            if (mayorRegistro==0) {
                horaMasTransitada = -1;
            }
        }
        else {
            System.out.println("Este método no puede funcionar aún (invocar antes método analyzeHourlyData)");
        }
        return horaMasTransitada;
    }
}

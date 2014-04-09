import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Utils {

	public static StringBuilder fileToLines(String filename) {

		StringBuilder lines = new StringBuilder();
		String line = "";
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream(new File(filename)), "UTF-8"));

			while ((line = in.readLine()) != null) {
				lines.append(line);
			}

			in.close();

		} catch (IOException e) {
			e.printStackTrace();
		} 

		return lines;
	}

	public static Date stringToDate(String stringDate){

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	

		try {

			Date date = formatter.parse(stringDate);

			return date;			

			//System.out.println(formatter.format(date));

		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}	

	public static String dateToString(Date data){
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	
		
		return formatter.format(data);
	}

	public static Double formataStringToDouble(String numero){

		if(numero.contains(",") && numero.contains(".")){			
			numero = numero.replace(".", "");
			numero = numero.replace(",",".");			
		}
		else if(numero.contains(",")){
			numero = numero.replace(",", ".");
		}

		return Double.parseDouble(numero);	

	}

	public static String formataDoubleMoeda(Double valor){

		NumberFormat format = NumberFormat.getCurrencyInstance();
		return format.format(valor);

	}
	
}

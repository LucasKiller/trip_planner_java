import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Fix_Date_String {
    
    public static String fix_date(String date) {

        SimpleDateFormat formatoInicial = new SimpleDateFormat("dd/MM/yyyy");

        SimpleDateFormat formatoNovo = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date dataObjeto = formatoInicial.parse(date);

            return formatoNovo.format(dataObjeto); 
        } catch(ParseException ex) {
            ex.printStackTrace();
            return null;
        }

    }

}

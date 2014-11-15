package labs.three;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatConverter implements Converter<Date> {
	private SimpleDateFormat parsing;
	private SimpleDateFormat formatting;
	
	public SimpleDateFormatConverter() {
		this.parsing = new SimpleDateFormat("MM/dd/yyyy");
		this.formatting = new SimpleDateFormat("MM/dd/yyyy");
	}
	
	public SimpleDateFormatConverter(SimpleDateFormat sdf) {
		if(sdf != null) {
			this.parsing = sdf;
			this.formatting = sdf;
		}
		else {
			this.parsing = new SimpleDateFormat();
			this.formatting = new SimpleDateFormat();
		}	
	}
	
	public SimpleDateFormatConverter(SimpleDateFormat forParsing, SimpleDateFormat forFormatting) {
		if(forParsing != null) {
			this.parsing = forParsing;
		}
		else {
			this.parsing = new SimpleDateFormat();
		}
		
		if(forFormatting != null) {
			this.formatting = forFormatting;
		}
		else {
			this.formatting = new SimpleDateFormat();
		}
	}

	@Override
	public Date parse(String fromString) {
		try {
			return parsing.parse(fromString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String format(Date fromT) {
		return formatting.format(fromT);
	}

}

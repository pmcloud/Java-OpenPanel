/**
 * 
 */
/**
 *
 * Copyright (c) 2012 <copyright Aruba spa>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE.
 *
 */
package whitelabel.cloud.adapter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.datetime.DateFormatter;

/**
 * @author Inspect.it
 *
 */
public class DateFormatterExt extends DateFormatter {
	
	public static String DATE_PATTERN = "dd/MM/yyyy";
	public static String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";
	public static Locale LOCALE = Locale.ITALIAN;
	
	public DateFormatterExt() {
        super();
    }

    public DateFormatterExt(String pattern) {
        super(pattern);
    }

    
    public String print(Date data) {
        return (data ==null ? "" : this.print(data, LOCALE));        
    }

    @Override
    public String print(Date data, Locale locale) {
        return (data ==null ? "" : super.print(data, locale));        
    }
    
    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        if(text == null || text.trim().length() == 0){
            return null;
        }
        else {
            return super.parse(text, locale);
        }
    }
}

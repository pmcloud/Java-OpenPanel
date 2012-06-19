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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.format.number.NumberFormatter;

/**
 * @author luca
 * 
 */
public class CurrencyFormatterExt extends NumberFormatter  {

	public static Locale LOCALE = Locale.ITALIAN;
	public static String CURRENCY_PATTERN = "#,##0.00";

	public CurrencyFormatterExt() {
		super();
	}

	public NumberFormat getNumberFormat() {
		return getNumberFormat(LOCALE, CURRENCY_PATTERN);		
	}
	
	public NumberFormat getNumberFormat(Locale locale, String pattern) {

		NumberFormat df = NumberFormat.getNumberInstance(locale);
		DecimalFormat dec = (DecimalFormat) df;
		dec.applyPattern(pattern);
		dec.setMaximumFractionDigits(2);
		dec.setGroupingUsed(false);
		return dec;
	}

	public final String format(Object n) {
		return getNumberFormat().format(n);		
	}
	

}

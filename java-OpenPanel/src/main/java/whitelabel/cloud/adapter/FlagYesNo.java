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

import java.io.Serializable;

/**
 * @author luca
 *
 */
public class FlagYesNo implements Serializable {

        
	private static final long serialVersionUID = 5014493942595656399L;
	private Number value= null;


    public FlagYesNo() {
    }

    public FlagYesNo(Number value) {
        this.value = value;
    }

    public FlagYesNo(Integer value) {
        this.value = value;
    }

    public FlagYesNo(Long value) {
        this.value = value;
    }

    public FlagYesNo(Short value) {
        this.value = value;
    }

    public FlagYesNo(String sN) {
    	if (sN != null && sN.trim().length() == 1) {
    		if ("1".equalsIgnoreCase(sN) || "S".equalsIgnoreCase(sN)) {
    			this.value = Integer.valueOf("1");
    		}
    	}
    }

    public String getDescription() {
       if(value==null || value.intValue()==0){
           return "key.no";
       } 
       else{
           return "key.yes";
       }
    }

    public Serializable getItemId() {
        return value;
    }
}

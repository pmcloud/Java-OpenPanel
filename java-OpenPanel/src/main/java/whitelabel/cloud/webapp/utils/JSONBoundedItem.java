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
package whitelabel.cloud.webapp.utils;

/**
 * @author luca
 *
 */
public class JSONBoundedItem extends JSONItem {

	private String boundKey = "boundKey";
	private Object boundValue= null;
		
	
	public JSONBoundedItem(String boundKey, Object boundValue) {
		if (boundKey != null) {
			this.boundKey = boundKey;
		}
		this.boundValue= boundValue;
		setDescription(boundKey + ": " + (boundValue != null ? boundValue.toString() : "<null>"));
	}

	/**
	 * @return the boundKey
	 */
	public String getBoundKey() {
		return boundKey;
	}

	/**
	 * @param boundKey the boundKey to set
	 */
	public void setBoundKey(String boundKey) {
		this.boundKey = boundKey;
	}

	/**
	 * @return the boundValue
	 */
	public Object getBoundValue() {
		return boundValue;
	}

	/**
	 * @param boundValue the boundValue to set
	 */
	public void setBoundValue(Object boundValue) {
		this.boundValue = boundValue;
	}
	
}

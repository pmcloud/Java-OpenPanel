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
package whitelabel.cloud.webapp.binding;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;

import whitelabel.cloud.adapter.CurrencyFormatterExt;
import whitelabel.cloud.adapter.DateFormatterExt;
import whitelabel.cloud.adapter.StringNullFormatterExt;
/**
 *
 * Used by <mvc:annotation-driven conversion-service="conversionService" />
 *
 * @author Aruba
 *
 */
public class EntityConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {


    @Override
    protected void installFormatters(FormatterRegistry registry) {
        super.installFormatters(registry);
        registry.addFormatterForFieldType(Date.class, new DateFormatterExt());
        registry.addFormatterForFieldType(BigDecimal.class, new CurrencyFormatterExt());
        registry.addFormatterForFieldType(String.class, new StringNullFormatterExt());
    }

}

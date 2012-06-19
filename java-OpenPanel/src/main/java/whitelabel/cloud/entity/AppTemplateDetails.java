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
package whitelabel.cloud.entity;

import java.io.Serializable;
import java.util.List;

import whitelabel.cloud.remotews.jaxb.entity.ArrayOfResourceBound;
import whitelabel.cloud.remotews.jaxb.entity.ResourceBound;
import whitelabel.cloud.remotews.jaxb.entity.TemplateDetails;
import whitelabel.cloud.remotews.jaxb.entity.TemplateTypes;


/**
 * @author luca
 *
 */
public class AppTemplateDetails implements Serializable {

	private static final long serialVersionUID = -7771337828590395662L;

	private String description;
    private Integer id;
    private String name;
    private Integer productId;
    private List<ResourceBound> resourceBounds = null;
    private TemplateTypes templateType;
    private Boolean toolsAvailable;

    public AppTemplateDetails(TemplateDetails templateDetails) {
    	if (templateDetails != null) {
    		setId(templateDetails.getId());
    		setName(templateDetails.getName().getValue());
    		setDescription(templateDetails.getDescription().getValue());
    		setProductId(templateDetails.getProductId());
    		setToolsAvailable(templateDetails.getToolsAvailable());
    		setTemplateType(templateDetails.getTemplateType());
    		setResourceBounds(templateDetails.getResourceBounds().getValue());
    	}
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String value) {
        this.description = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer value) {
        this.productId = value;
    }

    public List<ResourceBound> getResourceBounds() {
        return resourceBounds;
    }

    private void setResourceBounds(ArrayOfResourceBound value) {
    	if (value != null) {
    		resourceBounds = value.getResourceBound();
    	}
    }

    public TemplateTypes getTemplateType() {
        return templateType;
    }

    public void setTemplateType(TemplateTypes value) {
        this.templateType = value;
    }

    public Boolean isToolsAvailable() {
        return toolsAvailable;
    }

    public void setToolsAvailable(Boolean value) {
        this.toolsAvailable = value;
    }

}

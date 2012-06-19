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
package whitelabel.cloud.webapp.impl.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import whitelabel.cloud.entity.AppVLan;
import whitelabel.cloud.remotews.jaxb.entity.HypervisorTypes;
import whitelabel.cloud.remotews.jaxb.entity.ResourceBound;

/**
 * @author luca
 *
 */
public class NewCloudServer extends AppModel {


	private Map<Long, Integer> selectedDiskSize = new HashMap<Long, Integer>();
	//private HypervisorTypes selectedHypervisorType = HypervisorTypes.HYPER_V;
	private String selectedHypervisorType;// = HypervisorTypes.HYPER_V.value();
	private Long selectedTemplateProdId;
	private Map<Long, ResourceBound> appHDxBound=new HashMap<Long, ResourceBound>();
	private List<AppVLan> availableAppVLANs = new ArrayList<AppVLan>();
	//private Map<HypervisorTypes, HypervisorTypes> hypervisorTypes = new HashMap<HypervisorTypes, HypervisorTypes>();
	private Map<String, HypervisorTypes> hypervisorTypes = new HashMap<String, HypervisorTypes>();
	private Map<Long, String> templateList = new HashMap<Long, String>();

	private Long selectedCPUNum;
	private Long selectedRAMNum;
	private ResourceBound appCPUsBound;
	private ResourceBound appRAMsBound;

	@NotEmpty
	@Pattern(regexp="^[a-z,A-Z,0-9,-]*$")
	private String name;

	@NotEmpty
	@Pattern(regexp="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{7,20}$")
	private String password;

	@NotEmpty
	private String passwordChk;

	private Long vlan_eth02;
	@Pattern(regexp="^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")
	private String eth02_IP;
	@Pattern(regexp="^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")
	private String eth02_NM;

	private Long vlan_eth03;
	@Pattern(regexp="^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")
	private String eth03_IP;
	@Pattern(regexp="^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$")
	private String eth03_NM;


	private String createError;


	/**
	 * Constructor
	 */
	public NewCloudServer() {
		super();
		selectedDiskSize.put(0L, 10);
		selectedDiskSize.put(1L, -1);
		selectedDiskSize.put(2L, -1);
		selectedDiskSize.put(3L, -1);
	}

	/**
	 * @return the selectedCPUNum
	 */
	public final Long getSelectedCPUNum() {
		return selectedCPUNum;
	}
	/**
	 * @param selectedCPUNum the selectedCPUNum to set
	 */
	public final void setSelectedCPUNum(Long selectedCPUNum) {
		this.selectedCPUNum = selectedCPUNum;
	}
	/**
	 * @return the selectedRAMNum
	 */
	public final Long getSelectedRAMNum() {
		return selectedRAMNum;
	}
	/**
	 * @param selectedRAMNum the selectedRAMNum to set
	 */
	public final void setSelectedRAMNum(Long selectedRAMNum) {
		this.selectedRAMNum = selectedRAMNum;
	}
	/**
	 * @return the selectedHypervisorType
	 */
	public final String getSelectedHypervisorType() {
		return selectedHypervisorType;
	}
	/**
	 * @param selectedHypervisorType the selectedHypervisorType to set
	 */
	public final void setSelectedHypervisorType(String selectedHypervisorType) {
		this.selectedHypervisorType = selectedHypervisorType;
	}
	/**
	 * @return the selectedTemplateProdId
	 */
	public final Long getSelectedTemplateProdId() {
		return selectedTemplateProdId;
	}
	/**
	 * @param selectedTemplateProdId the selectedTemplateProdId to set
	 */
	public final void setSelectedTemplateProdId(Long selectedTemplateProdId) {
		this.selectedTemplateProdId = selectedTemplateProdId;
	}
	/**
	 * @return the availableAppVLANs
	 */
	public final List<AppVLan> getAvailableAppVLANs() {
		return availableAppVLANs;
	}
	/**
	 * @param availableAppVLANs the availableAppVLANs to set
	 */
	public final void setAvailableAppVLANs(List<AppVLan> availableAppVLANs) {
		this.availableAppVLANs = availableAppVLANs;
	}
	/**
	 * @return the hypervisorTypes
	 */
	public final Map<String, HypervisorTypes> getHypervisorTypes() {
		return hypervisorTypes;
	}
	/**
	 * @param hypervisorTypes the hypervisorTypes to set
	 */
	public final void setHypervisorTypes(Map<String, HypervisorTypes> hypervisorTypes) {
		this.hypervisorTypes = hypervisorTypes;
	}
	/**
	 * @return the templateList
	 */
	public final Map<Long, String> getTemplateList() {
		return templateList;
	}
	/**
	 * @param templateList the templateList to set
	 */
	public final void setTemplateList(Map<Long, String> templateList) {
		this.templateList = templateList;
	}
	/**
	 * @return the selectedDiskSize
	 */
	public final Map<Long, Integer> getSelectedDiskSize() {
		return selectedDiskSize;
	}
	/**
	 * @param selectedDiskSize the selectedDiskSize to set
	 */
	public final void setSelectedDiskSize(Map<Long, Integer> selectedDiskSize) {
		this.selectedDiskSize = selectedDiskSize;
	}
	/**
	 * @return the appHDxBound
	 */
	public final Map<Long, ResourceBound> getAppHDxBound() {
		return appHDxBound;
	}
	/**
	 * @param appHDxBound the appHDxBound to set
	 */
	public final void setAppHDxBound(Map<Long, ResourceBound> appHDxBound) {
		this.appHDxBound = appHDxBound;
	}
	/**
	 * @return the appCPUsBound
	 */
	public final ResourceBound getAppCPUsBound() {
		return appCPUsBound;
	}
	/**
	 * @param appCPUsBound the appCPUsBound to set
	 */
	public final void setAppCPUsBound(ResourceBound appCPUsBound) {
		this.appCPUsBound = appCPUsBound;
	}
	/**
	 * @return the appRAMsBound
	 */
	public final ResourceBound getAppRAMsBound() {
		return appRAMsBound;
	}
	/**
	 * @param appRAMsBound the appRAMsBound to set
	 */
	public final void setAppRAMsBound(ResourceBound appRAMsBound) {
		this.appRAMsBound = appRAMsBound;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the passwordChk
	 */
	public String getPasswordChk() {
		return passwordChk;
	}
	/**
	 * @param passwordChk the passwordChk to set
	 */
	public void setPasswordChk(String passwordChk) {
		this.passwordChk = passwordChk;
	}
	/**
	 * @return the vlan_eth02
	 */
	public Long getVlan_eth02() {
		return vlan_eth02;
	}
	/**
	 * @param vlan_eth02 the vlan_eth02 to set
	 */
	public void setVlan_eth02(Long vlan_eth02) {
		this.vlan_eth02 = vlan_eth02;
	}
	/**
	 * @return the eth02_IP
	 */
	public String getEth02_IP() {
		return eth02_IP;
	}
	/**
	 * @param eth02_IP the eth02_IP to set
	 */
	public void setEth02_IP(String eth02_IP) {
		this.eth02_IP = eth02_IP;
	}
	/**
	 * @return the eth02_NM
	 */
	public String getEth02_NM() {
		return eth02_NM;
	}
	/**
	 * @param eth02_NM the eth02_NM to set
	 */
	public void setEth02_NM(String eth02_NM) {
		this.eth02_NM = eth02_NM;
	}
	/**
	 * @return the vlan_eth03
	 */
	public Long getVlan_eth03() {
		return vlan_eth03;
	}
	/**
	 * @param vlan_eth03 the vlan_eth03 to set
	 */
	public void setVlan_eth03(Long vlan_eth03) {
		this.vlan_eth03 = vlan_eth03;
	}
	/**
	 * @return the eth03_IP
	 */
	public String getEth03_IP() {
		return eth03_IP;
	}
	/**
	 * @param eth03_IP the eth03_IP to set
	 */
	public void setEth03_IP(String eth03_IP) {
		this.eth03_IP = eth03_IP;
	}
	/**
	 * @return the eth03_NM
	 */
	public String getEth03_NM() {
		return eth03_NM;
	}
	/**
	 * @param eth03_NM the eth03_NM to set
	 */
	public void setEth03_NM(String eth03_NM) {
		this.eth03_NM = eth03_NM;
	}

	public String getCreateError() {
		return createError;
	}

	public void setCreateError(String createError) {
		this.createError = createError;
	}

}

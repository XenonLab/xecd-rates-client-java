package com.xe.xecdApiClient.model;

import com.google.gson.annotations.SerializedName;

public class AccountInfoResponse 
{
	private String id;
	private String organization;
	@SerializedName("package")
	private String packageLevel;
	private String service_start_timestamp;
	private String package_limit_duration;
	private Integer package_limit;
	private Integer package_limit_remaining;
	private String package_limit_reset;
	
	public String getId() 
	{
		return id;
	}
	
	public void setId(String id) 
	{
		this.id = id;
	}
	
	public String getOrganization() 
	{
		return organization;
	}
	
	public void setOrganization(String organization)
	{
		this.organization = organization;
	}
	
	public String getService_start_timestamp() 
	{
		return service_start_timestamp;
	}
	
	public void setService_start_timestamp(String service_start_timestamp) 
	{
		this.service_start_timestamp = service_start_timestamp;
	}
	
	public String getPackageLevel()
	{
		return packageLevel;
	}

	public void setPackageLevel(String packageLevel)
	{
		this.packageLevel = packageLevel;
	}

	public String getPackage_limit_duration() 
	{
		return package_limit_duration;
	}
	
	public void setPackage_limit_duration(String package_limit_duration) 
	{
		this.package_limit_duration = package_limit_duration;
	}
	
	public Integer getPackage_limit() 
	{
		return package_limit;
	}
	
	public void setPackage_limit(Integer package_limit) 
	{
		this.package_limit = package_limit;
	}
	
	public Integer getPackage_limit_remaining() 
	{
		return package_limit_remaining;
	}
	
	public void setPackage_limit_remaining(Integer package_limit_remaining) 
	{
		this.package_limit_remaining = package_limit_remaining;
	}
	
	public String getPackage_limit_reset()
	{
		return package_limit_reset;
	}
	
	public void setPackage_limit_reset(String package_limit_reset) 
	{
		this.package_limit_reset = package_limit_reset;
	}
}

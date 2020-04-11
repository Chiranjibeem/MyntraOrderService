package com.covid19.india.Covid19India.model;

public class DistrictData
{
    public String district;

    public Delta delta;

    public String confirmed;

    public String lastupdatedtime;

    public String getDistrict ()
    {
        return district;
    }

    public void setDistrict (String district)
    {
        this.district = district;
    }

    public Delta getDelta ()
    {
        return delta;
    }

    public void setDelta (Delta delta)
    {
        this.delta = delta;
    }

    public String getConfirmed ()
    {
        return confirmed;
    }

    public void setConfirmed (String confirmed)
    {
        this.confirmed = confirmed;
    }

    public String getLastupdatedtime ()
    {
        return lastupdatedtime;
    }

    public void setLastupdatedtime (String lastupdatedtime)
    {
        this.lastupdatedtime = lastupdatedtime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [district = "+district+", delta = "+delta+", confirmed = "+confirmed+", lastupdatedtime = "+lastupdatedtime+"]";
    }
}
			
			
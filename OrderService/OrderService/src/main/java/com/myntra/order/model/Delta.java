package com.myntra.order.model;

public class Delta
{
    private String confirmed;

    public String getConfirmed ()
    {
        return confirmed;
    }

    public void setConfirmed (String confirmed)
    {
        this.confirmed = confirmed;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [confirmed = "+confirmed+"]";
    }
}
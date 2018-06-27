package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */

public class BuisnessData implements Serializable {

    public int businessNumber;
    public String name;
    public String primaryBusiness;
    public String address;
    public String provinceOrTerritory;

    public BuisnessData(String businessNumber, String name, String primaryBusiness, String address, String provinceOrTerritory) {
        try{
            this.businessNumber = Integer.parseInt(businessNumber);
        }
        catch(Exception nonIntEntry)
        {
            //do I throw an exception here, or will firebase parse an int for me?
            this.businessNumber=-1;
        }
        this.name = name;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.provinceOrTerritory = provinceOrTerritory;
    }


    @Override
    public int hashCode() {

        return Objects.hash(businessNumber, name, primaryBusiness, address, provinceOrTerritory);
    }

    public BuisnessData() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    }


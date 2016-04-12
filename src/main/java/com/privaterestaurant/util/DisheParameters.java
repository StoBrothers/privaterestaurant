package com.privaterestaurant.util;

import java.io.Serializable;

/**
 * Dishes wrapper as JSON object.
 * 
 * @author Sergey Stotskiy
 *
 */
@SuppressWarnings("serial")
public class DisheParameters implements Serializable {

    private Long companyId;
    private Long municipalityId;
    private Long companyKindId;
    private Integer[] years;
    private Integer[] quarters;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(Long municipalityId) {
        this.municipalityId = municipalityId;
    }

    public Long getCompanyKindId() {
        return companyKindId;
    }

    public void setCompanyKindId(Long companyKindId) {
        this.companyKindId = companyKindId;
    }

    public Integer[] getYears() {
        return years;
    }

    public void setYears(Integer[] years) {
        this.years = years;
    }

    public Integer[] getQuarters() {
        return quarters;
    }

    public void setQuarters(Integer[] quarters) {
        this.quarters = quarters;
    }

}

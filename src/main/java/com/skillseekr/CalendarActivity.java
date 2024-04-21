package com.skillseekr;

import java.time.ZonedDateTime;

public class CalendarActivity {
    private ZonedDateTime date;
    private String offer;
    private Integer serviceNo;

    public CalendarActivity(ZonedDateTime date, String offer, Integer serviceNo) {
        this.date = date;
        this.offer = offer;
        this.serviceNo = serviceNo;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public Integer getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(Integer serviceNo) {
        this.serviceNo = serviceNo;
    }

    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                ", clientName='" + offer + '\'' +
                ", serviceNo=" + serviceNo +
                '}';
    }
}

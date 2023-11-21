package com.example.farm.model;
public class PlotModel {
    private String user_id;
    private String farm_name;
    private String total_farm_area;
    private String plot_name;
    private String soil_type;
    private String location;
    private String plot_area;

    // Constructors
    public PlotModel() {
        // Default constructor
    }

    public PlotModel(String user_id, String farm_name, String total_farm_area, String plot_name, String soil_type, String location, String plot_area) {
        this.user_id = user_id;
        this.farm_name = farm_name;
        this.total_farm_area = total_farm_area;
        this.plot_name = plot_name;
        this.soil_type = soil_type;
        this.location = location;
        this.plot_area = plot_area;
    }

    // Getters and setters
    public String getUserId() {
        return user_id;
    }

    public void setUserId(String userId) {
        this.user_id = userId;
    }

    public String getFarmName() {
        return farm_name;
    }

    public void setFarmName(String farmName) {
        this.farm_name = farmName;
    }

    public String getTotalFarmArea() {
        return total_farm_area;
    }

    public void setTotalFarmArea(String totalFarmArea) {
        this.total_farm_area = totalFarmArea;
    }

    public String getPlotName() {
        return plot_name;
    }

    public void setPlotName(String plotName) {
        this.plot_name = plotName;
    }

    public String getSoilType() {
        return soil_type;
    }

    public void setSoilType(String soilType) {
        this.soil_type = soilType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPlotArea() {
        return plot_area;
    }

    public void setPlotArea(String plotArea) {
        this.plot_area = plotArea;
    }
}


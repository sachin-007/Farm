package com.example.farm.model;
public class FarmRequest {
    private String user_id;
    private String farm_name;
    private String total_farm_area;
    private String plot_name;
    private String soil_type;
    private String location;
    private String plot_area;

    // Add a constructor to initialize the fields

    public FarmRequest(String user_id, String farm_name, String total_farm_area, String plot_name, String soil_type, String location, String plot_area) {
        this.user_id = user_id;
        this.farm_name = farm_name;
        this.total_farm_area = total_farm_area;
        this.plot_name = plot_name;
        this.soil_type = soil_type;
        this.location = location;
        this.plot_area = plot_area;
    }
}

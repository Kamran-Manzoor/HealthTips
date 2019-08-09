package com.example.salman.healthtips;

/**
 * Created by Salman on 7/16/2019.
 */

public class griddata {


    String titlename;
    String gridimage;
    public griddata(){}

    public String getTitlename() {
        return titlename;
    }

    public void setTitlename(String titlename) {
        this.titlename = titlename;
    }

    public String getGridimage() {
        return gridimage;
    }

    public void setGridimage(String gridimage) {
        this.gridimage = gridimage;
    }

    public griddata(String titlename, String gridimage) {

        this.titlename = titlename;
        this.gridimage = gridimage;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

/**
 *
 * @author intrah1
 */
public class Element {
    
    private String cadre;
    private String activity;
    
    public Element(String _cadre,String _activity){
        this.activity=_activity;
        this.cadre=_cadre;
    }

    /**
     * @return the cadre
     */
    public String getCadre() {
        return cadre;
    }

    /**
     * @param cadre the cadre to set
     */
    public void setCadre(String cadre) {
        this.cadre = cadre;
    }

    /**
     * @return the activity
     */
    public String getActivity() {
        return activity;
    }

    /**
     * @param activity the activity to set
     */
    public void setActivity(String activity) {
        this.activity = activity;
    }
}

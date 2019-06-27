/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SYOM
 */
@XmlRootElement
public class SurecModel
{

    private final SimpleStringProperty stdNos, surecIsimler;

    public SurecModel()
    {
        stdNos = new SimpleStringProperty();
        surecIsimler = new SimpleStringProperty();
    }

    public SurecModel(String stdNos, String surecIsimler)
    {
        this.stdNos = new SimpleStringProperty(stdNos);
        this.surecIsimler = new SimpleStringProperty(surecIsimler);
    }

    @XmlElement
    public String getStdNos()
    {
        return stdNos.get();
    }

    public void setStdNos(String stdNos)
    {
        this.stdNos.set(stdNos);
    }

    @XmlElement
    public String getSurecIsimler()
    {
        return surecIsimler.get();
    }

    public void setSurecIsimler(String surecIsimler)
    {
        this.surecIsimler.set(surecIsimler);
    }
}

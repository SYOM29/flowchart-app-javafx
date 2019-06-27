package xml;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SYOM
 */
@XmlRootElement
public class SurecTableModel
{

    //@XmlElement(name = "standartKodNos")
    private ObservableList<SurecModel> surecler = FXCollections.observableArrayList();

    public SurecTableModel()
    {
    }

    @XmlElement
    public ObservableList<SurecModel> getSurecler()
    {
        return surecler;
    }

    public void setSurecler(ObservableList<SurecModel> surecler)
    {
        this.surecler = surecler;
    }
    
    public void addSurec(SurecModel surec)
    {
        this.surecler.add(surec);
    }
}

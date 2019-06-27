package xml;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author SYOM
 */
public class XMLUnmarshaller
{

    public final String TEMPLATEPATH = "./template.xml";

    private String path;
    private SurecTableModel tablo;
    private Unmarshaller um;

    public XMLUnmarshaller()
    {
        try
        {
            JAXBContext context = JAXBContext.newInstance(SurecTableModel.class);
            um = context.createUnmarshaller();
        }
        catch (JAXBException exception)
        {
            exception.getMessage();
        }
        tablo = null;
    }

    /**
     * reads any file to project structure
     * @param path
     * @return project
     * @throws FileNotFoundException
     * @throws JAXBException
     */
    public SurecTableModel readFile(String path) throws FileNotFoundException, JAXBException
    {
        this.path = path;

        if (SurecTableModel.class.isInstance(um.unmarshal(new FileReader(path))))
        {
            tablo = (SurecTableModel) um.unmarshal(new FileReader(path));
        }
        return tablo;
    }

    /**
     * reads template to the project structure
     * @return project
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public SurecTableModel readTemplate() throws JAXBException, FileNotFoundException
    {
        if (SurecTableModel.class.isInstance(um.unmarshal(new FileReader(TEMPLATEPATH))))
        {
            tablo = (SurecTableModel) um.unmarshal(new FileReader(TEMPLATEPATH));
        }
        return tablo;
    }
}

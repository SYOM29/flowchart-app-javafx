/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author SYOM
 */
public class XMLMarshaller
{

    private static Marshaller marshaller;
    private static File file;
    private static SurecTableModel tablo;

    public XMLMarshaller() throws JAXBException
    {
        configure();
    }

    /**
     * configures and returns marshaler
     *
     * @throws JAXBException
     */
    public void configure() throws JAXBException
    {
        // create JAXB context and instantiate marshaller
        JAXBContext context = JAXBContext.newInstance(SurecTableModel.class);
        marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    /**
     * creates file with the given name and using given project structure, if
     * file with such name already exist in the default directory does nothing
     *
     * @param name
     * @param tablo
     * @throws xml.FileAlreadyExistException
     * @throws javax.xml.bind.JAXBException
     */
    public void createNamedFile(String name, SurecTableModel tablo) throws FileAlreadyExistException, JAXBException
    {
        file = new File("./" + name + ".xml");
        if (file.exists())
        {
            throw new FileAlreadyExistException();
        }
        marshaller.marshal(tablo, new File("./" + name + ".xml"));
    }

    /**
     * re-saves the file with the given name and project
     *
     * @param name
     * @param tablo 
     * @throws JAXBException
     */
    public void resave(String name, SurecTableModel tablo) throws JAXBException
    {
        marshaller.marshal(tablo, new File("./" + name + ".xml"));
    }
}

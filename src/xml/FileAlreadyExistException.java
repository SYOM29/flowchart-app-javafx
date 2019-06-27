/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xml;

/**
 *
 * @author SYOM
 */
public class FileAlreadyExistException extends Exception
{
    private static Exception exception;
    public FileAlreadyExistException()
    {
        exception = new Exception("File already exist in the set directory.");
    }
}

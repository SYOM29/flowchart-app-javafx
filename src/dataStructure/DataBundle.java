/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataStructure;

import java.util.HashMap;

/**
 *
 * @author User
 */
public class DataBundle
{

    private HashMap<String, String> dataMap;
    private final String Ad = "Ad";
    private final String Maliyet = "Maliyet";
    private final String Sure = "Süre";
    private final String Kaynaklar = "Kaynaklar";

    public DataBundle()
    {
        dataMap.put(Ad, "");
        dataMap.put(Maliyet, "");
        dataMap.put(Sure, "");
        dataMap.put(Kaynaklar, "");
    }

    public void replaceAd(String s)
    {
        dataMap.replace(Ad, s);
    }

    public void replaceMaliyet(String s)
    {
        dataMap.replace(Maliyet, s);
    }

    public void replaceSure(String s)
    {
        dataMap.replace(Sure, s);
    }

    public void replaceKaynaklar(String s)
    {
        dataMap.replace(Kaynaklar, s);
    }

    public void replaceValue(String key, String value)
    {
        dataMap.replace(key, value);
    }

    public void addNewEntry(String key, String value)
    {
        dataMap.put(key, value);
    }

    public void removeKey(String key)
    {
        dataMap.remove(key);
    }

    public String getValue(String key)
    {
        return dataMap.get(key);
    }

    public HashMap<String, String> getDataMap()
    {
        return dataMap;
    }
}

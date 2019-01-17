package utilities;

import constants.GlobalVariables;

import java.io.FileNotFoundException;
import java.util.*;

public class Common {
    private HashMapHelper hashMapHelper = new HashMapHelper();

    private String buildUrl(String url, String device, String brand) {
        return url + "&device=" + device.toLowerCase() + "&brand=" + brand.toLowerCase();
    }
    public String[] readUrl(int index) throws NullPointerException {
        LinkedHashMap<List, HashMap> linkedHashMap = (LinkedHashMap<List, HashMap>) hashMapHelper.getMap().get("visualUrl");
       List keys =  Arrays.asList(linkedHashMap.keySet().toArray());
        String testName = keys.get(index).toString();
        List abc = Arrays.asList(linkedHashMap.values().toArray());
        String url = ((LinkedHashMap) abc.get(index)).get("url").toString();
        String brand = ((LinkedHashMap) abc.get(index)).get("brand").toString();
        String device = ((LinkedHashMap) abc.get(index)).get("device").toString();
        return new String[]{buildUrl(url, device, brand), testName};
    }

    public String readUrl(String key) throws FileNotFoundException, NullPointerException {
        final String[] url = {null};
        HashMapHelper hashMapHelper = new HashMapHelper();
        Map<String, Object> visualUrl = new YamlHelper().loadYamlProperties(GlobalVariables.visualUrlFilePath);
        hashMapHelper.getMap().put("visualUrl", visualUrl);
        LinkedHashMap<List, HashMap> linkedHashMap = (LinkedHashMap<List, HashMap>) hashMapHelper.getMap().get("visualUrl");
        linkedHashMap.entrySet().stream().forEach((x) -> {
            if(String.valueOf(x.getKey()).equalsIgnoreCase(key)){
                url[0] = buildUrl(x.getValue().get("url").toString(), x.getValue().get("brand").toString(), x.getValue().get("device").toString());
            }
        });
        return url[0];
    }
    public int readUrlFileSize() throws FileNotFoundException, NullPointerException {
        Map<String, Object> visualUrl = new YamlHelper().loadYamlProperties(GlobalVariables.visualUrlFilePath);
        hashMapHelper.getMap().put("visualUrl", visualUrl);
        return visualUrl.size();
    }
}

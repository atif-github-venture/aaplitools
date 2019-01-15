package utilities;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class YamlHelper {
    public Map<String, Object> loadYamlProperties(String path) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(path);
        return yaml.load(inputStream);
    }
}

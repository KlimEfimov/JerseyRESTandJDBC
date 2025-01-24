package org.klimefimov.restservice.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

//@Getter
public class Util {

    private static final Properties DB_PROPERTIES = new Properties();
    private static final List<String> INITIAL_SCHEMA_LIST = new ArrayList<>();


    static {
        readDBProperties();
        readSchema("schema.sql");
    }

    private static void readSchema(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Util.class.getClassLoader().getResourceAsStream(name))))) {
            reader.lines().forEach(s -> {if (s.length() > 0) INITIAL_SCHEMA_LIST.add(s);});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readDBProperties() {
        try (InputStream input = Util.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                System.exit(1);
            }
            DB_PROPERTIES.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getDbProperties() {
        return DB_PROPERTIES;
    }

    public static List<String> getInitialSchemaList() {
        return INITIAL_SCHEMA_LIST;
    }




}

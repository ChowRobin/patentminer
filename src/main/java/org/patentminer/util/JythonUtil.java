package org.patentminer.util;

import org.python.util.PythonInterpreter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class JythonUtil {

    public static void main(String[] args) throws FileNotFoundException {
        Properties props = new Properties();
//        props.put("python.home", "path to the Lib folder");
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();

        PythonInterpreter.initialize(preprops, props, new String[0]);

        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.exec("print(\"hello Jython\")");
//        InputStream filepy = new FileInputStream("/Users/robin/Workspace/JavaProjects/patentminer/src/main/java/org/patentminer/util/1.py");
//        interpreter.execfile(filepy);
    }


}

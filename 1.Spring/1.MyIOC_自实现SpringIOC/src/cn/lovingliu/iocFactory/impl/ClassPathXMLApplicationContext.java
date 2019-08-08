package cn.lovingliu.iocFactory.impl;
import cn.lovingliu.iocFactory.ApplicationContext;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import org.jdom.xpath.XPath;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ClassPathXMLApplicationContext implements ApplicationContext {
    @Override
    public Object getBean(String name) {
        return map.get(name);
    }

    private File file; //读取文件
    private Map map = new HashMap();// 存放创建的实例

    public ClassPathXMLApplicationContext(String xml_file){
        URL url = this.getClass().getClassLoader().getResource(xml_file);
        try{
            file = new File(url.toURI());
            XMLParsing();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void XMLParsing() throws Exception {
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(file);
        XPath xpath = XPath.newInstance("//bean");
        List beans = xpath.selectNodes(doc);
        Iterator i = beans.iterator();
        while(i.hasNext()){
            Element bean = (Element) i.next();
            String id = bean.getAttributeValue("id");
            String cls = bean.getAttributeValue("class");
            Object obj = Class.forName(cls).newInstance();
            Method[] method = obj.getClass().getDeclaredMethods();
            List<Element> list = bean.getChildren("property");

            for (Element el : list) {
                for (int n = 0; n < method.length; n++) {
                    String name = method[n].getName();
                    String temp = null;
                    if (name.startsWith("set")) {
                        temp = name.substring(3, name.length()).toLowerCase();
                        System.out.println(el.getAttribute("name"));
                        if (el.getAttribute("name") != null) {
                            if (temp.equals(el.getAttribute("name").getValue())) {
                                if( el.getAttribute("value")!=null){
                                    method[n].invoke(obj, el.getAttribute("value").getValue());
                                }else{
                                    method[n].invoke(obj, map.get(el.getAttribute("ref").getValue()));
                                }
                            }
                        } else {
                            method[n].invoke(obj, map.get(el.getAttribute("ref").getValue()));
                        }
                    }
                }
            }
            map.put(id,obj);
        }
    }

}

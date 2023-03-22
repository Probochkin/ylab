package third.task.orgstructure;

import java.io.File;
import java.io.IOException;


public class OrgStructureParserTest {
    public static void main(String[] args) throws IOException {
        OrgStructureParser orgStructureParser = new OrgStructureParserImpl();
        Employee employee = orgStructureParser.parseStructure(new File("src\\main\\resources\\csv\\file3.csv"));
        System.out.println(employee);


    }
}

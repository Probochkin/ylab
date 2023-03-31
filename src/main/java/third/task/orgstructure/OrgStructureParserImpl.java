package third.task.orgstructure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class OrgStructureParserImpl implements OrgStructureParser {
    @Override
    public Employee parseStructure(File csvFile) throws IOException {
        Employee boss = new Employee();

        try (FileInputStream inFile = new FileInputStream(csvFile)) {
            Scanner scanner = new Scanner(inFile);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] stringarray = scanner.nextLine().split(";");
                if (stringarray[1].isBlank()) {
                    boss.setId(Long.valueOf(stringarray[0]));
                    boss.setName(stringarray[2]);
                    boss.setPosition(stringarray[3]);
                    } else {
                    Employee employee = new Employee();
                    employee.setId(Long.valueOf(stringarray[0]));
                    employee.setBossId(Long.valueOf(stringarray[1]));
                    employee.setName(stringarray[2]);
                    employee.setPosition(stringarray[3]);
                    boss.getSubordinate().add(employee);
                }
            }
        }

        for (int size = boss.getSubordinate().size()-1; size >= 0; size--) {
            Employee employee = boss.getSubordinate().get(size);
            if (employee.getBossId().equals(boss.getId())) {
                employee.setBoss(boss);
            } else {
                for (int size2 = boss.getSubordinate().size() - 1; size2 >= 0; size2--) {
                    Employee employee2 = boss.getSubordinate().get(size2);
                    if (employee.getBossId().equals(employee2.getId())) {
                        employee.setBoss(employee2);
                    }
                }
            }
        }

        for (int i = 0; i < boss.getSubordinate().size(); i++) {

            Employee employee = boss.getSubordinate().get(i);
            if (!employee.getBoss().equals(boss)) {
                Employee nachalnic = employee.getBoss();
                nachalnic.getSubordinate().add(employee);
                boss.getSubordinate().remove(employee);
                i--;
            }

        }
            return boss;
        }
    }


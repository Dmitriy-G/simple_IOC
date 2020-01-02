import containers.Container;
import containers.SimpleContainerViaMap;
import core.HeadPhysician;
import core.Nurse;
import core.Registrar;
import rules.ClinicRulesConfiguration;
import rules.Rule;
import rules.RuleTypes;

import java.util.List;

public class JavaClinic {

    public static void startTreating(){
        Container container = new SimpleContainerViaMap();
        new HeadPhysician(container).resolveClinicRules();

        ClinicRulesConfiguration clinicRulesConfiguration = (ClinicRulesConfiguration) container.getDependency(ClinicRulesConfiguration.class.getName());

        List<Rule> rules = clinicRulesConfiguration.getRules();

        Registrar registrar = new Registrar(container);

        Rule pathToPackage = rules.stream().filter( rule -> rule.getType().equals(RuleTypes.PATH_TO_PACKAGE)).findFirst().orElseThrow(IllegalArgumentException::new);

        registrar.register(pathToPackage.getValue());
        Nurse nurse = new Nurse(container);
    }
}

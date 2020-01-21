package pl.project.pk.utils.factory;

import pl.project.pk.utils.benefit.FirstGroup;
import pl.project.pk.utils.benefit.SecondGroup;
import pl.project.pk.utils.benefit.ThirdGroup;

public class BenefitFactoryMethod {

    public BenefitFactoryMethod(){}

    public FirstGroup makeFirstGroup(){
        return new pl.project.pk.utils.benefit.FirstGroup();
    }

    public SecondGroup makeSecondGroup(){
        return new pl.project.pk.utils.benefit.SecondGroup();
    }

    public  ThirdGroup makeThirdGroup(){
        return new pl.project.pk.utils.benefit.ThirdGroup();
    }

}
